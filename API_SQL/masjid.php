<h2>Masjid</h2>
<table border="1">
	 <tr>
	 	<th>ID</th>
	 	<th>MASJID</th>
	 	<th>ALAMAT</th>
	 </tr>

<?php

include("config.php");



$sql = "SELECT * FROM identitas_masjid";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
   echo"
   <tr>
    <td>".$row['id_identitas']."</td>
    <td>".$row['nama_masjid']."</td>
    <td>".$row['alamat_masjid']."</td>
    </tr>";
}
?>
