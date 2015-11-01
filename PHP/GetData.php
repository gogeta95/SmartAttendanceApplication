<?php
require_once('config.php');
$conn=mysqli_connect(SERVER,USER,PASS,DB) or die("Connection error");
$class=mysqli_real_escape_string($conn,$_GET['class']);
$sem=mysqli_real_escape_string($conn,$_GET['sem']);
date_default_timezone_set("Asia/Kolkata");
$tablename=$class.'_'.$sem.'_'.date("m_Y");
// $tablename='tablename';
$query='select * from '.$tablename.' where day=\''.date('d').'\'';
$rs=mysqli_query($conn,$query);
if($rs&&mysqli_num_rows($rs)) {
    die('error!');
 }
$query='select * from '.$class.'_'.$sem;
$rs=mysqli_query($conn,$query);
while($row=mysqli_fetch_array($rs,MYSQL_NUM)){
	$response[$row[0]]=$row[1];
}
mysqli_free_result($rs);
header('Content-Type: application/json');
echo json_encode($response);
mysqli_close($conn);
?>
