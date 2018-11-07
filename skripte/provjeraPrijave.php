<?php

    require("baza.class.php");

    $opcija = "";
    $email = "";
    $lozinka = "";
	
	$odgovor = array();
	
    if(isset($_POST["opcija"]) && isset($_POST["email"]) && isset($_POST["lozinka"])){
        $opcija = $_POST["opcija"];
        $email = $_POST["email"];
        $lozinka = $_POST["lozinka"];
    }
    else{
		$odgovor["success"] = 0;
		$odgovor["message"] = "Wrong parameters!";
		$odgovor["data"] = [];
        echo json_encode($odgovor);
        exit();
    }

    if($opcija == "provjeriPrijavu"){
        $veza = new Baza();
        $veza->spojiDB();
        $rezultat = $veza->selectDB("select id_korisnik, ime, prezime, email from korisnik where email='$email' and lozinka='$lozinka'");
        $veza->zatvoriDB();
        if(mysqli_num_rows($rezultat)==0){
            $odgovor["success"] = 0;
			$odgovor["message"] = "No data!";
			$odgovor["data"] = [];
			echo json_encode($odgovor);
        }
        else{
			$odgovor["success"] = 1;
			$odgovor["message"] = "Data arrived!";
            while($red = mysqli_fetch_assoc($rezultat)){
                $odgovor["data"][] = $red;
            }
            echo json_encode($odgovor);
        }
        exit();
    }
?>