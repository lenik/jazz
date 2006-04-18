<?php

require '../lang.php'; 


$m = array(
  'name' => "le;n\n\"i\k", 
  'age' => 13, 
  'foo' => null, 
); 

echo $s = map_format($m), "\n";

$m2 = map_parse($s); 
var_dump($m2);

?>
