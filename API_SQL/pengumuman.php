<h2>Jadwal</h2>
<table border="1">
	 <tr>
	 	<th>ID Pengumuman</th>
	 	<th>Judul Pengumuman</th>
	 	<th>Isi Pengumuman</th>
	 </tr>
<?php

include("config.php");



$sql = "SELECT * FROM pengumuman_masjid";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    echo"
   <tr>
    <td>".$row['id_pengumuman']."</td>
    <td>".$row['judul_pengumuman']."</td>
    <td>".$row['isi_pengumuman']."</td>
    </tr>";
}
?>
