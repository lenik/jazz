<?
    require '_Phpfixes.php'; 
    _RequireOnce('../../dbi.php'); 
    
    $d = new phpx_dbi; 
    $d->_debug = true; 
    
    echo "password: ", $d->_password, "\n"; 
    
    $d->_Connect(); 
    $d->_SelectDb('test'); 
    $d->_Query('create table hello(name char(100))'); 
    
    $val = "world " . rand(1, 100); 
    $d->_Query("insert into hello values('$val')"); 
    
    $name = $d->_Evaluate('select * from hello'); 
    
    $d->_Query('drop table hello'); 

    $d->_Close(); 
    
    echo "name: $name\n"; 
?>
