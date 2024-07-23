public void loadNotes() {
CollectionReference notebook = db.collection("user data");
notebook.get().addOnCompleteListener(task -> {
if (task.isSuccessful()) {
hideProgress();
String data = "";
for (QueryDocumentSnapshot document : task.getResult()) {
Log.d(TAG, document.getId() + " => " + document.getData());//
if (document.contains("name") && document.contains("description") &&
document.contains("user type") && document.contains("userid")) {
String name = (String) document.get("name");
String type = (String) document.get("user type");
String description = (String) document.get("description");
String Userid = (String) document.get("userid");
String userID = fAuth.getCurrentUser().getUid();
Timestamp ts = (Timestamp) document.get("timestamp");
String dateandtime = String.valueOf(ts.toDate());
if (Userid.equals(userID)) {
data += "Name: " + name + "\nUser Type: " + type + "\nDescription:
" + description + "\nDate & Time: " + dateandtime + "\n\n";
} textViewData.setText(data); } }
} else {
hideProgress();
Log.d(TAG, "Error fetching data: ", task.getException()); } }); }