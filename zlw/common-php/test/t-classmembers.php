<?php

class C {
	var $x = 'hello'; 
	var $y = 'world'; 
	function f() {
		echo $this->x . $this->y; 
		echo "\n"; 
	}
}

$c = new C(); 

echo "gettype: " . gettype($c) . "\n"; 

foreach ($c as $k=>$v) {
	echo "$k => $v\n"; 
	echo "$k == " . $c->$k . "\n"; 
}
?>
