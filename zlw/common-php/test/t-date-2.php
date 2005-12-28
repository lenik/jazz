<?php

    require_once dirname(__FILE__) . '/../lang.php'; 
    
    $t = time(); 
    $t0 = time_0(); 
    
    $st = time_format($t); 
    $st0 = time_format($t0); 
    
    $s0t = time_format_0($t); 
    $s0t0 = time_format_0($t0); 
    
    echo "Current time (local):                     $st\n"; 
    echo "Current time (remote):                    $st0\n"; 
    echo "Current remote time (offset by local):    $s0t\n"; 
    echo "Current remote time (offset by remote):   $s0t0\n"; 
    echo "\n"; 
    
    $t0 = time_0(); 
    $st0 = time_format($t0); 
    $t0_s = time_0_format($t0); 
    echo "Current time (remote):                    $st0\n"; 
    echo "Current local time (deoffset by remote):  $t0_s\n";
?>