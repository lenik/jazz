<?php

require '../http.php'; 

$hb = new phpx_http_buffer(); 

$hb->output('hello'); 
$hb->buffer_start(); 
$hb->output('world'); 
echo "<BUFFER: " . $hb->buffer_end() . ">"; 
?>
