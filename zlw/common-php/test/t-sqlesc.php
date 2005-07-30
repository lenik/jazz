<?
require 'string.php'; 

$s = "abc\nhello, i'm dansei 1'2''3'''4''''!\ndef"; 

$a1 = sql_escape($s); 
echo "escape: \"$a1\"\n";  

$a2 = sql_descape($a1); 
echo "descape: \"$a2\"\n"; 
?>
