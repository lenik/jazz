<?php

    require_once dirname(__FILE__) . '/../application.php'; 
    
    phpx_application_start(); 
    
    global $PHPX_APP; 
    $PHPX_APP['hello'] = "world!-2"; 
    echo $PHPX_APP['hello'];
    
    phpx_application_end(); 
?>