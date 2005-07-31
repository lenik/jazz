<?
    require "../../dbi.php"; 
    
    $dbi = new DBI; 
    $dbi->_debug = true; 
    
    echo "password: ", $dbi->_password, "\n"; 
    
    $dbi->connect(); 
    $dbi->select_db('test'); 
    $dbi->query('create table hello(name char(100))'); 
    
    $val = "world " . rand(1, 100); 
    $dbi->query("insert into hello values('$val')"); 
    
    $name = $dbi->evaluate('select * from hello'); 
    
    $dbi->query('drop table hello'); 

    $dbi->close(); 
    
    echo "name: $name\n"; 
?>
