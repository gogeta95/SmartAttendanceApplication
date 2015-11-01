<?php
include('/db.php');
$json = file_get_contents('php://input');
$response= json_decode($json,true);
date_default_timezone_set("Asia/Kolkata");
$class=$response['class'];
$sem=$response['sem'];
$tablename=date("_d_m_Y_h_i_").$sem.$class;
$query="create table ".$tablename." (roll_no varchar(11) primary key,present boolean not null)";
mysqli_query($conn,$query) or die (mysqli_error($conn));
$data=$response['data'];
foreach ($data as $key => $value) {
	$value=$value?1:0;
	$query='insert into '.$tablename.' values (\''.$key.'\','.$value.')';
	mysqli_query($conn,$query);
}
mysqli_close($conn);
?>
