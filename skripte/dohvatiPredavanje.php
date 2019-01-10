<?php

    require("baza.class.php");

    $idDvorana = "";
	
	$odgovor = array();
	
    if(isset($_POST["idDvorana"])){
        $idDvorana = $_POST["idDvorana"];
    }
    else{
		$odgovor["success"] = 0;
		$odgovor["message"] = "Wrong parameters!";
		$odgovor["data"] = [];
        echo json_encode($odgovor);
        exit();
    }

    $veza = new Baza();
    $veza->spojiDB();
    $tz = 'Europe/Zagreb';
    $timestamp = time();
    $dt = new DateTime("now", new DateTimeZone($tz));
    $dt->setTimestamp($timestamp);
    $trenutnoVrijeme = $dt->format('H:i:s');
    $date = $dt->format('d-m-Y');
    $dan = date('D', strtotime($date));
    $rezultat = $veza->selectDB("select zgrada.naziv as 'zgrada', dvorana.naziv as 'dvorana', kolegij.id_kolegij, kolegij.naziv as 'kolegij' 
    from kolegij, zgrada, raspored, dvorana 
    where '$trenutnoVrijeme'>=raspored.pocetak and '$trenutnoVrijeme'<=raspored.kraj 
    and raspored.dan='$dan' 
    and kolegij.id_kolegij=raspored.id_kolegij 
    and zgrada.id_zgrada=dvorana.id_zgrada 
    and raspored.id_dvorana=dvorana.id_dvorana 
    and raspored.id_dvorana=$idDvorana");
    $veza->zatvoriDB();
    if(mysqli_num_rows($rezultat)!=0){
        $odgovor["success"] = 1;
        $odgovor["message"] = "Data arrived!";
        while($red = mysqli_fetch_assoc($rezultat)){
            $odgovor["data"][] = $red;
        }
        echo json_encode($odgovor);
    }
    else{
        $odgovor["success"] = 0;
        $odgovor["message"] = "Query failed!";
        $odgovor["data"] = [];
        echo json_encode($odgovor);
    }

    exit();
?>