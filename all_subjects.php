<?php
require "db_connect.php";

$sql_query="SELECT DISTINCT subject FROM teaches UNION SELECT DISTINCT subject FROM learns";
$query=mysqli_query($con,$sql_query);
if(!$query)
{
	echo "query error!";
}
else
{
 $result = array();
 $result['result'] = array_values($query);
 echo json_encode($result);
mysqli_close($con);
}
?>