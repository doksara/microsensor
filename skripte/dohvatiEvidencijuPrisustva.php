<?php
 
    class Odgovor{
        public $naziv;
        public $datum;
    }

    require("baza.class.php");

    $idKorisnik = "";
	
	$odgovor = array();
	
    if(isset($_POST["idKorisnik"]) && $_POST["idKorisnik"]!=""){
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
    $rezultat = $veza->selectDB("select kolegij.naziv, prisustvo.datum from kolegij, prisustvo 
    where kolegij.id_kolegij=prisustvo.id_kolegij and prisustvo.id_korisnik=$idKorisnik order by 1");
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
        $odgovor["data"] = array();
        while($red = mysqli_fetch_assoc($rezultat)){
            $ima = false;
            foreach($odgovor["data"] as $odg){
                if($odg->naziv==$red["naziv"]){
                    $ima = true;
                    $odg->datum[] = $red["datum"];
                }
            }
            if($ima==false){
                $novi = new Odgovor();
                $novi->naziv = $red["naziv"];
                $novi->datum = [$red["datum"]];
                $odgovor["data"][] = $novi;
            }
        }
        echo json_encode($odgovor);
    }
    exit();
?>