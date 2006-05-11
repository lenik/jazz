<pre><?php
include_once 't-config.php'; 
include_once 'zlw/common-php/lang.php';
include_once 'zlw/common-php/dbi.php'; 

class C {
  public $a='1';
  public $b='hello'; 
  function f() {
        extract(phpx_to_assoc($this)); 
    echo "a=$a, b=$b"; 
  }
}

$x = new C; 
$x->f(); 

?></pre>