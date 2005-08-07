<?
    require '_Phpfixes.php'; 
    _RequireOnce('../application.php'); 
    
    phpx_application_start(); 
    
    global $PHPX_APP; 
    echo $PHPX_APP['hello']; 
    
    phpx_application_end(); 
?>