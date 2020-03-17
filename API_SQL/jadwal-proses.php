
<?php

include("config.php");

$shubuh = $_POST['shubuh']; 
$dhuha = $_POST['dhuha']; 
$dhuhur = $_POST['dhuhur']; 
$ashar = $_POST['ashar']; 
$maghrib = $_POST['maghrib']; 
$isha = $_POST['isha']; 


$sql = "UPDATE jadwal_sholat SET shubuh='$shubuh', dhuha='$dhuha', dhuhur='$dhuhur', ashar='$ashar', maghrib='$maghrib', isha='$isha'";
	$query = mysqli_query($db, $sql);

	if( $query ){


	}  else {
		die("Gagal menyimpan perubahan");
	}

?>