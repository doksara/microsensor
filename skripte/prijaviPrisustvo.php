<?php

    require("baza.class.php");

    $idKolegij = "";
    $idKorisnik = "";
	
	$odgovor = array();
	
    if(isset($_POST["idKolegij"]) && isset($_POST["idKorisnik"]) && $_POST["idKorisnik"]!="" && $_POST["idKolegij"]!=""){
        $idKolegij = $_POST["idKolegij"];
        $idKorisnik = $_POST["idKorisnik"];
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
    $pohadjaKolegij = $veza->selectDB("select * from studenti_na_kolegiju where id_kolegij=$idKolegij and id_korisnik=$idKorisnik");
    if(mysqli_num_rows($pohadjaKolegij)==0){
        $odgovor["success"] = 0;
        $odgovor["message"] = "Korisnik ne pohadja kolegij!";
        $odgovor["data"] = [];
        $veza->zatvoriDB();
        echo json_encode($odgovor);
        exit();
    }
    else{
        $datum = date("Y-m-d");
        $rezultat = $veza->updateDB("insert into prisustvo values ($idKolegij, $idKorisnik, '$datum')");
        if($rezultat){
            $odgovor["success"] = 1;
            $odgovor["message"] = "Prisustvo prijavljeno!";
            $odgovor["data"] = [];
        }
        else{
            $odgovor["success"] = 0;
            $odgovor["message"] = "Prisustvo je vec prijavljeno!";
            $odgovor["data"] = [];
        }
        $veza->zatvoriDB();
        echo json_encode($odgovor);
        exit();
    }
    exit();
?>