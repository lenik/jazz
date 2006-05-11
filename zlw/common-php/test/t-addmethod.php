<?php

class A {
	public $name = 'hello'; 
	function about() {
		echo "This name: " . $this->name . "\n"; 
	}
}

$a = new A; 
echo "a.name: " . $a->name . "\n"; 
$a->about(); 

$a->newfunc = create_function
?>
