<?php

    require_once dirname(__FILE__) . '/http.php'; 
    
    @session_start(); 
    
    $sleep = $_REQUEST['_sleep']; 
    if (isset($sleep))
        usleep($sleep); 
        
    function dump_section($name, $map, $sep = '') {
        echo "<h2>$name" . "</h2>\n"; 
        sort($keys = array_keys($map)); 
        echo "<table border='0'>\n"; 
        foreach ($keys as $k) {
            $v = $map[$k]; 
            $type = gettype($v); 
            echo "<tr><td colspan='2'><strong>$type</strong> <u>$k</u></td></tr>\n"; 
            echo "<tr><td class='indent'><td>"; 
            switch (gettype($v)) {
            case 'boolean': 
            case 'integer': 
            case 'double': 
            case 'string': 
                echo "<pre>" . htmlspecialchars($v) . "</pre>\n"; 
                break; 
            case 'array': 
                echo "<ol>\n"; 
                foreach ($v as $vi) {
                    echo "<li><pre>" . htmlspecialchars($vi) . "</pre></li>\n"; 
                }
                echo "</ol>\n"; 
                break; 
            case 'object': 
                echo "<table border='0'>\n"; 
                foreach ($v as $k1=>$v1) {
                    echo "<tr><td align='right'><u>" . htmlspecialchars($k1) . "</u></td>"; 
                    echo "<td><pre>" . htmlspecialchars($v1) . "</pre></td></tr>"; 
                }
                echo "</table>\n"; 
                break; 
            case 'resource': 
                echo htmlspecialchars($v); 
                break; 
            case 'NULL': 
                echo "<i>NULL</i>"; 
                break; 
            default: 
                echo "<i>" . htmlspecialchars($v) . "</i>"; 
                break; 
            }
		}
		echo "</table>\n"; 
		echo "<hr/>\n"; 
	}
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html id="html">
  <head>
    <title>Common-PHP: Context</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
  </head>

 <body>
    
    <?php
        dump_section('$_GET', $_GET); 
        dump_section('$_POST', $_POST); 
        dump_section('$_REQUEST', $_REQUEST); 
        dump_section('$_REQUEST (noslashes)', phpx_noslashes($_REQUEST)); 
        dump_section('$_SESSION', $_SESSION); 
        dump_section('$_SERVER', $_SERVER);
        dump_section('$_ENV', $_ENV);
        dump_section('$_COOKIE', $_COOKIE);
    ?>
	
	<?php
	    phpinfo(); 
	?>
	
  </body>
</html>