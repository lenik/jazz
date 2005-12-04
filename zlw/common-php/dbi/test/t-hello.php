<?php
    require '_Phpfixes.php'; 
    _RequireOnce('t-config.php'); 
    _RequireOnce('../../dbi.php'); 
    
    $d = sql_connect('mysql', 'localhost', 'root', 'l.', 'test'); 
    $d->_debug = true; 
    
    echo "password: ", $d->_password, "\n"; 
    
    $d->_select_db('test'); 
    $d->_query('create table hello(name char(100))'); 
    
    $val = "world " . rand(1, 100); 
    $d->_query("insert into hello values('$val')"); 
    
    $name = $d->_evaluate('select * from hello'); 
    
    $d->_query('drop table hello'); 

    $d->_close(); 
    
    echo "name: $name\n"; 
?>