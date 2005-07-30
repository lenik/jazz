<?
    require "application.php"; 
    
    application_start(); 
    
    $_APP["hello"] = "world!"; 
    echo $_APP["hello"]; 
    
    application_end(); 
?>