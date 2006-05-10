<pre><?php
include_once 'lang.php';
include_once 'test/t-config.php'; 
include_once 'dbi.php'; 

class C {
  var $a='1';
  var $b='hello'; 
  function f() {
        extract(phpx_to_assoc($this)); 
    echo "a=$a, b=$b"; 
  }
}

$x = new C; 
$x->f(); 

?></pre>