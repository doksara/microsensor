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
    $sekunde = time()-(5*24*60*60);
    $datumPocetak = date("Y-m-d", $sekunde);
    $datumKraj = date("Y-m-d");
    $rezultat = $veza->selectDB("select cast(datum as date) as 'dan', avg(temperatura) as 'temperatura', 
    avg(jacina_svjetlosti) as 'jacina_svjetlosti', avg(vlaznost_zraka) as 'vlaznost_zraka' from mjerenje 
    where id_dvorana=$idDvorana and datum>='$datumPocetak' and datum<='$datumKraj' group by 1");
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