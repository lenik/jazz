<?php
    foreach ($_SERVER as $k=>$v) {
        echo "$k = $v\n"; 
    }
    
    foreach ($ARGV as $arg) {
        echo "Argument: $arg\n"; 
    }
    
?>
