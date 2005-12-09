<?php

$paths = array(
	'/asdf/sdfasfkfjsa/fs', 
	'c:/fsdk/fs/fs', 
	'http://host:port/fk/fjskj', 
	'http://host:port/fk/fjskj/', 
	'http://host:port/fk', 
	'http://host:port/', 
	'http://host:port', 
	); 

foreach ($paths as $path) {
	echo "dirname of $path is: \n"; 
	echo "    " . dirname($path) . "\n\n"; 
}
?>
