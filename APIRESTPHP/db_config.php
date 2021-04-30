<?php
/**
 * Database config variables
 */
define("DB_HOST", "localhost");
define("DB_USER", "id16721143_root");
define("DB_PASSWORD", "PorSeguridadNoColocar");
define("DB_DATABASE", "id16721143_apitesting");

$db = mysqli_connect(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
	
	if($db == false){
		echo "No connection";
	}                        
?>