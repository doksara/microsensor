<?php

    require("baza.class.php");

    $dvorana = "";
    $zgrada = "";
	
	$odgovor = array();
	
    if(isset($_POST["dvorana"]) && isset($_POST["zgrada"])){
        $dvorana = $_POST["dvorana"];
        $zgrada = $_POST["zgrada"];
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
    $sekunde = time()-(5*24*60*60);
    $datumPocetak = date("Y-m-d", $sekunde);
    $datumKraj = date("Y-m-d");
    $idDvorana = $veza->selectDB("select dvorana.id_dvorana from dvorana, zgrada where zgrada.naziv='$zgrada' and zgrada.id_zgrada=dvorana.id_dvorana and dvorana.naziv='$dvorana'");
    $idDvorana = mysqli_fetch_array($idDvorana);
    $idDvorana = $idDvorana["id_dvorana"];
    $rezultat = $veza->selectDB("select cast(datum as date) as 'dan', avg(temperatura) as 'temperatura', 
    avg(jacina_svjetlosti) as 'jacina_svjetlosti', avg(vlaznost_zraka) as 'vlaznost_zraka' from mjerenje 
    where id_dvorana=$idDvorana and datum>='$datumPocetak' and datum<='$datumKraj' group by 1");
    $veza->zatvoriDB();
    if($rezultat){
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