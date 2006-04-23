<pre><?php

include_once '../lang.php';
include_once '../test/t-config.php'; 
include_once '../dbi.php'; 

$a = array('1'=>lenik, '2'=>13, 3=>456);

echo phpx_is_array($a) ? 'true' : 'false';

?></pre>