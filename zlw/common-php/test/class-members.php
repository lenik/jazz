<?php
class C {
	var $name; 
	function C($name) {
		$this->name = $name; 
	}

	function f1() {
		echo "hello $this->name"; 
	}
	function f2() {
		$this->f1(); 
		echo " from world"; 
	}
}

class D {
    var $def; 
    function func() {
        if ($def == null)
            echo "def: == null\n"; 
        if ($def === null)
            echo "def: === null\n"; 
        if (! defined($def))
            echo "def: ! defined()\n"; 
        if (! $def)
            echo "def: !\n"; 
        if ($def == 0)
            echo "def: == 0\n"; 
        if ($def === 0)
            echo "def: === 0\n"; 
        if ($def == false)
            echo "def: == false\n"; 
        if ($def === false)
            echo "def: === false\n"; 
        if ($def == '')
            echo "def: == ''\n"; 
        if ($def === '')
            echo "def: === ''\n"; 
    }
}

$x = new C('lenik'); 
# $x->f2(); 

$y = new D(); 
$y->func(); 

?>