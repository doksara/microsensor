<?php

    require("baza.class.php");

    $temperatura = "";
    $jacinaSvjetlosti = "";
    $vlaznostZraka = "";
    $idDvorana = "";
	
	$odgovor = array();
	
    if(isset($_POST["temperatura"]) && isset($_POST["jacinaSvjetlosti"]) && isset($_POST["vlaznostZraka"]) && isset($_POST["idDvorana"])){
        $temperatura = $_POST["temperatura"];
        $jacinaSvjetlosti = $_POST["jacinaSvjetlosti"];
        $vlaznostZraka = $_POST["vlaznostZraka"];
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
    $datum = date("Y-m-d H:i:s");
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