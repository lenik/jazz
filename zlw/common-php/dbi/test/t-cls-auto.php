<?php

class C {
    var $c1 = 'C::c1 default'; 
    var $c2;            /* default null */
    function mtd1() {
        echo "C.c1 == $this->c1\n";
        echo "C.c2 type: ", gettype($this->c2), "\n"; 
        if ($this->user) {
            echo "C.user is set! \n";       /* ok */
        }
        if ($this->unexisted_member) {      /* no undefined-error */
            echo "C.unexist got!! \n"; 
        }
    }
    function reflect1($str) {
        echo "hello $str! \n"; 
    }
}

class D extends C {
    var $c1 = 'C::c1 -> D::c1'; 
    var $d1 = 'D::d1 default'; 
}

$a = new C; 
$a->user = 'C::user value'; 
$a->mtd1(); 

echo "----------- D \n";
$b = new D; 
$b->mtd1(); 

echo "----------- Reflect C\n"; 
$m = 'reflect1'; 
$a->$m('REFLECT-1'); 
$a->{'reflect1'}('REFLECT-2'); 
echo "Field reflect: ", $a->{'c1'}, "\n"; 
echo "Field reflect: ", $a->{'user'}, "\n"; 

?>