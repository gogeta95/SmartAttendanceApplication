<?php
require_once('config.php');
function calctable($conn,$roll){
	$query='show tables in attendance like \'______\'';
	$rs= mysqli_query($conn,$query);
	while($row=mysqli_fetch_array($rs,MYSQL_NUM)){
	$another_query='select 1 from '.$row[0].' where roll_no=\''.$roll.'\'';
	$rs2=mysqli_query($conn,$another_query);
	if(mysqli_num_rows($rs2)){
		mysqli_free_result($rs);
		mysqli_free_result($rs2);
		return $row[0];
	}
	else echo "not found";
}
}
$conn=mysqli_connect(SERVER,USER,PASS,DB) or die("Connection error");
date_default_timezone_set("Asia/Kolkata");
$roll=mysqli_real_escape_string($conn,$_GET['roll']);
// $roll='02176802712';
if(array_key_exists('month',$_GET)&&$_GET['month']<=12&&$_GET['month']>=1){
	$month=mysqli_real_escape_string($conn,$_GET['month']);
}
else{
	$month=date('m');
}
if(array_key_exists('year',$_GET)){
	$year=mysqli_real_escape_string($conn,$_GET['year']);
}
else{
	$year=date('_Y');
}
$table=calctable($conn,$roll);
$table.='_'.$month.$year;
// echo $table;
$query='select day,_'.$roll.' from '.$table;
// $query='select day,_10313202712 from tablename';
// echo $query;
$rs=mysqli_query($conn,$query);
while($row=mysqli_fetch_array($rs,MYSQL_NUM)){
$response[$row[0]]=$row[1]?true:false;
}
header('Content-Type: application/json');
echo json_encode($response);
mysqli_free_result($rs);
mysqli_close($conn);
?>
