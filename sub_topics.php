<?php
require "db_connect.php";
$subject= $_GET['subject'];
$code=$_GET['code'];
if($code=1)
{
	$table='teaches';
}
else
{
	$table='learns';
}
echo $subject;

$sql_query="SELECT topic FROM '$table' WHERE subject='$subject'";

$query=mysqli_query($con,$sql_query);
if(!$query)
{
	echo "query error!";
}
else
{
$response = array();
while($row=mysqli_fetch_array($query))
{
	array_push($response,array("topic"=>$row[0]));
}
echo json_encode(array("server_response"=>$response));
mysqli_close($con);
}
?>