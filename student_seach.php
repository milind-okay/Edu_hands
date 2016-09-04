<?php
// student_search...
require "db_connect.php";
$json = $_SERVER['HTTP_JSON'];
    echo "JSON: \n";
    echo "--------------\n";
    var_dump($json);
    echo "\n\n";
 
    $data = json_decode($json);
    echo "Array: \n";
    echo "--------------\n";
    var_dump($data);  // display strctued_information...
    echo "\n\n";
	
	$subject=$data->subject;
	$x_coord=$data->x_coord;
	$y_coord=$data->y_coord;
	$topic=$data->topic;
	
	$sql_query="SELECT name,x_coord,y_coord FROM teaches WHERE exists (SELECT user_id FROM  user WHERE subject='$subject' AND topic='$topic' AND DATEDIFF('2014-11-29','2014-11-30')<=7)";  
	$result=mysqli_query($con,$sql_query);
	$response =array();
	if(!$result)
	{
		echo "query error!";
	}
	else
	{
while($row=mysqli_fetch_array($result))
{
	array_push($response,array("user_id"=>$row[1],"subject"=>$row[2],"topic"=>$row[3],"learning_level"=>$row[4],"fee"=>$row[5],"date"=>$row[6],"hour"=>$row[7],"name"=>$row[8],"dob"=>$row[9],"date_created"=>$row[10],"x_coord"=>$row[11],"y_coord"=>$row[12]))
}
echo json_encode(array("server_response"=>$response));
mysqli_close($con); 
	} 
?>