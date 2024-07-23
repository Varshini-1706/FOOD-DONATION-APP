public void onLocationChanged(@NonNull Location location) {
mLastLocation = location;
LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
MarkerOptions markerOptions = new
MarkerOptions().position(latLng).title("You are here");
mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
mMap.addMarker(markerOptions).showInfoWindow();
mSubmitBtn.setOnClickListener(v -> {
hideKeyboard(this);
String fullname = mFullName.getText().toString().trim();
String description= mDescription.getText().toString().trim();
String type= "Receiver";
if(TextUtils.isEmpty(fullname)) {
mFullName.setError("Name is Required.");
return; }
if(TextUtils.isEmpty(description)) {
mFullName.setError("Description is Required.");
return; }
userID = fAuth.getCurrentUser().getUid();
CollectionReference collectionReference = fStore.collection("user data");
GeoPoint geoPoint = new
GeoPoint(location.getLatitude(),location.getLongitude());
Map<String,Object> user = new HashMap<>();
user.put("timestamp", FieldValue.serverTimestamp());
user.put("name",fullname);
user.put("description",description);
user.put("location",geoPoint);
user.put("userid",userID);
user.put("type",type);
showProgress();
collectionReference.add(user)
.addOnSuccessListener(documentReference -> {
hideProgress();
Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_SHORT).show
();
Log.d(TAG,"Success!");
Intent intent = new Intent(Receive.this, MainActivity.class);
intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(intent); })
.addOnFailureListener(e -> {
hideProgress();
Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
Log.w(TAG, "Error!", e); });
});
}