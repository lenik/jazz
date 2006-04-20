<pre><?php
include_once '../lang.php';
include_once '../test/t-config.php'; 
include_once '../dbi.php'; 

$db = phpx_connect_fast(); 

$v = $db->_evaluate("%%page%select * from com_lang", null); 
var_dump($v); 

$db->_close(); 

?></pre>