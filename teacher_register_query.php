<?php
require "db_connect.php";
/*$json = $_SERVER['HTTP_JSON'];
    echo "JSON: \n";
    echo "--------------\n";
    var_dump($json);
    echo "\n\n";
 
    $data = json_decode($json);
    echo "Array: \n";
    echo "--------------\n";
    var_dump($data);
    echo "\n\n";
 
    $user_id= $data->user_id;
	$x_coord = $data->x_coord;
	$y_coord = $data->y_coord;
	$subject = $data->subject;
	$topic = $data->topic;
	$date = $data->date;
	$hour = $data->hour;
    $fee = $data->fee; */
    
	$user_id=113;
	$x_coord = 1.00234;
	$y_coord = 3.2123;
	$subject = "testing";
	$topic = "software engineering";
	$date = "12/12/2012";
	$hour = 2;
    $fee = 0353;
    echo "Result: \n";
    echo "--------------\n";
    
/*
variables to store the columns f=or the data..
$name
*/
$sql_query="INSERT INTO teaches (user_id, subject, topic, learning_level, fee, date,hour)
VALUES ($user_id,$subject,$topic,'0',$fee,$date,$hour);";// to further..
$sql_query1='UPDATE user
        SET x_coord=$x_coord, y_coord=$y_coord
        WHERE user_id=$user_id';
$query=mysqli_query($con,$sql_query);
$query1=mysqli_query($con,$sql_query);
if($query1 && $query)
{
	echo "data inserted..!!";
}
else
{
	echo "error connection..".mysqli_query($con,$sql_query);
}
?>