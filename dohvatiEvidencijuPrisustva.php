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
    $rezultat = $veza->selectDB("select kolegij.naziv, raspored.tip, prisustvo.datum from kolegij, prisustvo, raspored  
    where kolegij.id_kolegij=raspored.id_kolegij and prisustvo.id_korisnik=$idKorisnik and 
    prisustvo.id_raspored=raspored.id_raspored order by 1");
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
            $t = "";
            if($red["tip"]=="P")$t="Predavanje";
            if($red["tip"]=="S")$t="Seminari";
            if($red["tip"]=="L")$t="Labosi";
            if($red["tip"]=="A")$t="Auditorne";
            $s = $red["naziv"] . "-" . $t;
            foreach($odgovor["data"] as $odg){
                if(strcmp($odg->naziv,$s)==0){
                    $ima = true;
                    $odg->datum[] = $red["datum"];
                }
            }
            if($ima==false){
                $tip = "";
                if($red["tip"]=="P")$tip="Predavanje";
                if($red["tip"]=="S")$tip="Seminari";
                if($red["tip"]=="L")$tip="Labosi";
                if($red["tip"]=="A")$tip="Auditorne";
                $novi = new Odgovor();
                $novi->naziv = $red["naziv"] . "-" . $tip;
                $novi->datum = [$red["datum"]];
                $odgovor["data"][] = $novi;
            }
        }
        echo json_encode($odgovor);
    }
    exit();
?>