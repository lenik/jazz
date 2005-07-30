<?
    require "../../dbi.php"; 
    
    $dbi = new DBI; 
    
    echo "password: ", $dbi->password, "\n"; 
    
    $dbi->connect(); 
    $dbi->select_db('test'); 
    $dbi->query('create table hello(name char(100))'); 
    
    $val = "world " . rand(1, 100); 
    $dbi->query("insert into hello values('$val')"); 
    
    $name = $dbi->evaluate('select * from hello'); 
    
    $dbi->query('drop table hello'); 

    $dbi->disconnect(); 
    
    echo "name: $name\n"; 
?>
