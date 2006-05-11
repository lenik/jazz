<?php

require '../lang.php'; 

class C {
	public $dog; 
	public $cat; 
}

class D {
	public $tiger; 
	public $c; 
	function D() {
		$this->c = new C(); 
	}
}

$d1 = new D(); 
$d1->c->dog = 'Dog 1'; 

$d2 = new D(); 
$d2->tiger = 'Tiger 2'; 
$d2->c->cat = 'Cat 2'; 

phpx_or($d1, $d2); 

var_dump($d1); 

?>