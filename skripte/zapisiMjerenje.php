<?php

    require("baza.class.php");

    $temperatura = "";
    $jacinaSvjetlosti = "";
    $vlaznostZraka = "";
    $dvorana = "";
    $zgrada = "";
	
	$odgovor = array();
	
    if(isset($_POST["temperatura"]) && isset($_POST["jacinaSvjetlosti"]) && isset($_POST["vlaznostZraka"]) && isset($_POST["dvorana"]) && isset($_POST["zgrada"])){
        $temperatura = $_POST["temperatura"];
        $jacinaSvjetlosti = $_POST["jacinaSvjetlosti"];
        $vlaznostZraka = $_POST["vlaznostZraka"];
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
    $datum = date("Y-m-d H:i:s");
    $idDvorana = $veza->selectDB("select id_dvorana from dvorana, zgrada where zgrada.naziv='$zgrada' and zgrada.id_zgrada=dvorana.id_dvorana and dvorana.naziv='$dvorana'");
    $idDvorana = mysqli_fetch_array($idDvorana);
    $idDvorana = $idDvorana["id_dvorana"];
    $rezultat = $veza->updateDB("insert into mjerenje (temperatura, vlaznost_zraka, jacina_svjetlosti, datum, id_dvorana) 
    values ($temperatura, $vlaznostZraka, $jacinaSvjetlosti, '$datum', $idDvorana)");
    $veza->zatvoriDB();
    if($rezultat){
        $odgovor["success"] = 1;
        $odgovor["message"] = "Query succeeded!";
        $odgovor["data"] = [];
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