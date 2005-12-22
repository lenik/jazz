<?php

# & (BYREF) has a low precedence. 

class C {
	var $a = 'A hello'; 
	var $b = 'B world'; 
	var $next; 
}

$c1 = new C(); 
$c1->a = 'C1::A'; 

$c2 = new C(); 
$c2->a = 'C2::A'; 

$c1->next = $c2; 

$r = &$c1->next; 
	# is  r -> &($c1->next)
	# or  r -> (&$c1)->next  ?

$r->b = 'B Changed!'; 

var_dump($c1); 
var_dump($c2); 	# c2 changed, so r -> &($c1->next)
?>
