<?php
require "db_connect.php";
$user_id= $_GET['user_id'];

$sql_query="SELECT subject,AVG(learning_level),SUM(fee),SUM(hour) FROM teaches WHERE user_id='$user_id' GROUP BY subject";
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
	array_push($response,array("subject"=>$row[0],"learning_level"=>$row[1],"fee_gained"=>$row[2],"hour_devoted"=>row[3]));
}
echo json_encode(array("server_response"=>$response));
mysqli_close($con);
}
?>