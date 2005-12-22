<?php

function func(&$arg) {
	$arg = 'changed'; 
}

$x = 'hello'; 
func($x); 
echo "$x\n"; 

$y = NULL; 
func($y); 
echo "$y\n"; 

$list = array(); 
$list[] = &$y; 
echo "In list: " . $list[0] . "\n"; 
$y = 'new value'; 
echo "In list: " . $list[0] . "\n"; 

function bar(&$list, &$item) {
    $list['xyz'] = &$item; 
}
$list2 = array(); 
$foo = 'Hello'; 
bar($list2, $foo); 
$foo = 'world'; 
echo 'bar results: ' . $list2['xyz'] . "\n"; 

?>