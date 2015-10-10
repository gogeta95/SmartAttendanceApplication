<?php
header('Content-Type: application/json');
include('/db.php');
$sem=$_GET['sem'];
$class=$_GET['class'];
$query='select * from _'.$sem.$class;
$resultset=mysqli_query($conn,$query) or die(mysqli_error());
$roll_list = array();
while ($row=mysqli_fetch_array($resultset,MYSQL_NUM)) {
	$roll_list[$row[0]]=ucwords(strtolower($row[1]));
}
mysqli_free_result($resultset);
mysqli_close($conn);
echo json_encode($roll_list);
?>
