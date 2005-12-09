<?php
	$self = $_SERVER['PHP_SELF']; 
	$real = realpath($self); 
	echo "self: $self\n"; 
	echo "real: $real\n"; 
?>

