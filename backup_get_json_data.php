<?php
$host ="mysql4.000webhost.com";
$user ="a5174088_primary";
$password="teamRocket123";
$db="a5174088_primary";// get_from the request from android client..

$r = 6371;
$rad=30;
$lat=28.489143;
$lon=77.0737036;
$maxLat = $lat + rad2deg($rad/$r)*10;
$minLat = $lat - rad2deg($rad/$r)*10;
$maxLon = $lon + (rad2deg(asin($rad/$r) / cos(deg2rad($lat))))*10;
$minLon = $lon - 10*(rad2deg(asin($rad/$r) / cos(deg2rad($lat))));

$sql_query = "Select *
        From teaches  t
		LEFT JOIN user u
		ON t.user_id=u.user_id
        Where x_coord Between $minLat And $maxLat
          And y_coord Between $minLon And $maxLon"; // given by the walia....

$con= mysqli_connect($host,$user,$password,$db);
if(!$con)
{
echo  "connection error!";
exit();
}
else
{
$result=mysqli_query($con,$sql_query);
if (!$result) {
    printf("Error: %s\n", mysqli_error($con));
    exit();
}
$response =array();
while($row=mysqli_fetch_array($result,MYSQLI_BOTH))
{
	array_push($response,array("user_id"=>$row[1],"subject"=>$row[2],"topic"=>$row[3],"learning_level"=>$row[4],"fee"=>$row[5],"date"=>$row[6],"hour"=>$row[7],"name"=>$row[10],"dob"=>$row[11],"date_created"=>$row[12],"x_coord"=>$row[13],"y_coord"=>$row[14]));
}
echo json_encode(array("server_response"=>$response));
mysqli_close($con);
}
?>