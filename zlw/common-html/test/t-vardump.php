<pre><?php
include_once dirname(__FILE__) . '/../../common-php/phpfixes.php'; 
include_once 'zlw/common-php/lang.php';
include_once 'zlw/common-html/xs.php'; 

$x = array(1, array('name'=>'lenik', 'age'=>'13', ), 2, "a\\b\\c\\x\n");
echo zlw_xs_vardump('x', $x);
die("DD");
?></pre>