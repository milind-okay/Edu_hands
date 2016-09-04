<?php
require "db_connect.php";
$user_id= $_GET['user_id'];

$sql_query="SELECT r.student_id,r.rating,r.review,l.name FROM review as r  learns as l  WHERE teacher_id='$user_id' AND l.user_id=r.student_id";
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
	array_push($response,array("student_id"=>$row[0],"rating"=>$row[1],"review"=>$row[2],"name"=>row[3]));
}
echo json_encode(array("server_response"=>$response));
mysqli_close($con);
}
?>