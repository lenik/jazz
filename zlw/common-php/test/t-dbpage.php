<html><body>

<?php

require_once dirname(__FILE__) . '/t-config.php'; 
require_once 'zlw/common-php/dbi.php'; 
require_once 'zlw/abstract-form/af-quick.php'; 

$page = 0;
$page_size = 3;
$view_size = 5; 

extract($_REQUEST);                     # overwrite

echo '<table>';

error_reporting(E_ERROR | E_PARSE | E_WARNING); 
ini_set("display_errors", 1);

$db = phpx_connect_fast();
if (! $db)
     die('connect fail'); 

echo '<h1>db page</h1>';

$sql = "%% page($page, $page_size, $view_size) % select * from l";

$rs = $db->_query($sql);

while ($row = $db->_fetch_row($rs)) {
    echo '<tr>';
    foreach ($row as $field)
        echo '<td>' . $field . '</td>';
    echo '</tr>'; 
}

echo '</table>';

?>

<div>
<a href='?page=<?php echo $page-1 ?>'>prev</a>
<a href='?page=<?php echo $page+1 ?>'>next</a>

</div>
</body></html>