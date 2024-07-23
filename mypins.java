public void showLocation() {
this.cloudstorage = FirebaseFirestore.getInstance();
cloudstorage.collection("user data")
.get()
.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
@Override
public void onComplete(@NonNull Task<QuerySnapshot> task) {
if (task.isSuccessful()) {
for (QueryDocumentSnapshot document : task.getResult()) {
Log.d(TAG, document.getId() + " => " + document.getData());//
if (document.contains("location") && document.contains("name")
&& document.contains("description") && document.contains("userid")) {
GeoPoint location = (GeoPoint) document.get("location");
String title = (String) document.get("name");
String type = (String) document.get("type");
String description = (String) document.get("description");
String Userid = (String) document.get("userid");
String userID = fAuth.getCurrentUser().getUid();
if(type.equals("Donor") & Userid.equals(userID)) {
Log.d(TAG, userID + " Success " + title);
LatLng latLng = new LatLng(location.getLatitude(),
location.getLongitude());
mMap.addMarker(new
MarkerOptions().position(latLng).title(title+"("+type+")").snippet(description).icon(
BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
}else if(type.equals("Receiver") & Userid.equals(userID)){
Log.d(TAG, location + " Success " + title);
LatLng latLng = new LatLng(location.getLatitude(),
location.getLongitude());
mMap.addMarker(new
MarkerOptions().position(latLng).title(title+"("+type+")").snippet(description).icon(
BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
} } } } else {
Log.d(TAG, "Error fetching data: ", task.getException()); } } }); }