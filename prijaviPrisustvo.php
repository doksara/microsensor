<?php

    require("baza.class.php");

    $idRaspored = "";
    $idKorisnik = "";
	
	$odgovor = array();
	
    if(isset($_POST["idRaspored"]) && isset($_POST["idKorisnik"]) && $_POST["idKorisnik"]!="" && $_POST["idRaspored"]!=""){
        $idRaspored = $_POST["idRaspored"];
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
    $kolegij = $veza->selectDB("select id_kolegij from raspored where id_raspored=$idRaspored");
    $kolegij = mysqli_fetch_array($kolegij);
    $kolegij = $kolegij["id_kolegij"];
    $pohadjaKolegij = $veza->selectDB("select * from studenti_na_kolegiju where id_kolegij=$kolegij and id_korisnik=$idKorisnik");
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
        $rezultat = $veza->updateDB("insert into prisustvo values ($idRaspored, $idKorisnik, '$datum')");
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