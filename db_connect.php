<?php
/*$db_name="a5174088_primary";
$mysql_user="a5174088_primary";
$mysql_pass="teamRocket123";
$server_name="localhost"; */

$host ="mysql4.000webhost.com";
$user ="a5174088_primary";
$password="teamRocket123";
$db="a5174088_primary";

$con=mysqli_connect($host,$user,$password,$db);
if(!$con)
{
	echo "connection error";
	exit();
}
else
{
	echo "connection established";
}
?>