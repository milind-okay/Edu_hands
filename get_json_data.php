<?php

$host "mysql.000webhost.com";
$user ="a3895903_primary";
$password="teamRocket123";
$db="a5174088_primary" // get_from the request from android client..

$json = $_SERVER['HTTP_JSON'];
    echo "JSON: \n";
    echo "--------------\n";
    var_dump($json);
    echo "\n\n";
 
    $data = json_decode($json);
    echo "Array: \n";
    echo "--------------\n";
    var_dump($data);
    echo "\n\n";
//	$lat=$data->x_coord;
//	$lon=$data->y_coord;
$R = 6371;
$lat=28.489143;
$lon=77.0737036;
$maxLat = $lat + rad2deg($rad/$R);
$minLat = $lat - rad2deg($rad/$R);
$maxLon = $lon + rad2deg(asin($rad/$R) / cos(deg2rad($lat)));
$minLon = $lon - rad2deg(asin($rad/$R) / cos(deg2rad($lat)));

$sql_query=$sql = "Select *
        From teaches  t
		LEFT JOIN user u
		ON (t.user_id=u.user_id)
        Where Lat Between '$minLat' And :maxLat
          And Lon Between :minLon And :maxLon"; // given by the walia....

$con= mysqli_connect($host,$user,$password,$db);
$result=mysqli_query($con,$sql_query);

$response =array();
while($row=mysqli_fetch_array($result))
{
	array_push($response,array("user_id"=>$row[1],"subject"=>$row[2],"topic"=>$row[3],"learning_level"=>$row[4],"fee"=>$row[5],"date"=>$row[6],"hour"=>$row[7],"name"=>$row[8],"dob"=>$row[9],"date_created"=>$row[10],"x_coord"=>$row[11],"y_coord"=>$row[12]))
}
echo json_encode(array("server_response"=>$response));
mysqli_close($con);
?>