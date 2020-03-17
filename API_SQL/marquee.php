<h2>Jadwal</h2>
<table border="1">
	 <tr>
	 	<th>ID Marquee</th>
	 	<th>Isi Marquee</th>
	 </tr>
<?php

include("config.php");



$sql = "SELECT * FROM marquee";
$result = array();
$query = mysqli_query($db, $sql);
 
while($row = mysqli_fetch_array($query)){
    echo"
   <tr>
    <td>".$row['id_marquee']."</td>
    <td>".$row['isi_marquee']."</td>
    </tr>";
}
?>
