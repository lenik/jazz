<?
require 'base.php';

function f1($a, $b = "B-Default") {
    echo "f1: a=$a, b=$b\n"; 
}

function f2() {
    $args = func_get_args(); 
    call_user_func_array('f1', $args); 
}

f2('hello'); 

?>
