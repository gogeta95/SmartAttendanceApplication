<?php
require_once('config.php');
$json = file_get_contents('php://input');
$conn=mysqli_connect(SERVER,USER,PASS,DB) or die("Connection error");
$response= json_decode($json,true);
date_default_timezone_set("Asia/Kolkata");
$class=mysqli_real_escape_string($conn,$response['class']);
$sem=mysqli_real_escape_string($conn,$response['sem']);
$tablename=$class.'_'.$sem.'_'.date("m_Y");
$data=$response['data'];
// $tablename='tablename';
// $data = array('10313202712' => true,'10413202712' => true,'10513202712' => true,'10613202712' => false,'10713202712' => true,'10813202712' => false );
$query='create table if not exists '.$tablename.' ( day int primary key';
	foreach ($data as $key => $value) {
		$query.=',_'.$key.' boolean not null default false';
	}
	$query.=')';
echo $query.'<br>';
mysqli_query($conn,$query);
$query='insert into '.$tablename. ' values ('.date('d');
foreach ($data as $key => $value) {
	$boolval=$value?1:0;
		$query.=','.$boolval;
	}
	$query.=')';
echo $query;
mysqli_query($conn,$query);
mysqli_close($conn);
?>
