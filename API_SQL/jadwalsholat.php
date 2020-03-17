<h2>Jadwal</h2>
<table border="1">
	 <tr>
	 	<th>ID</th>
	 	<th>Shubuh</th>
	 	<th>Dhuha</th>
	 	<th>Dhuhur</th>
	 	<th>Ashar</th>
	 	<th>Maghrib</th>
	 	<th> Isya </th>
	 </tr>
<?php

include("config.php");



$sql = "SELECT * FROM jadwal_sholat";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    echo"
   <tr>
    <td>".$row['id_jadwal']."</td>
    <td>".$row['shubuh']."</td>
    <td>".$row['dhuha']."</td>
    <td>".$row['dhuhur']."</td>
     <td>".$row['ashar']."</td>
    <td>".$row['maghrib']."</td>
    <td>".$row['isha']."</td>
    </tr>";
}
?>
