<?php

    require_once dirname(__FILE__) . '/../http.php'; 
    
    $a = 0; 
    $b = 0; 
    
    if (isset($_REQUEST['submit'])) {
        $a = $_REQUEST['a']; 
        $b = $_REQUEST['b']; 
    }
    $args = phpx_httpcall_input(); 
    
    if (! is_null($args)) {
        # being called
        $a = $args['a']; 
        $b = $args['b']; 
    }
    
    $ret = $a + $b; 
    
    if (! is_null($args)) {
        phpx_httpcall_return($ret); 
    }
?>
<html>
<body>

<h1> Add Function </h1>

A = <?php echo $a?> <br>
B = <?php echo $b?> <br>
A + B = <?php echo $ret?> <br>

<form method='get'>
    A = <input type=text name=a value=100> <br>
    B = <input type=text name=b value=200> <br>
    <input type=submit name=submit value='Calculate'>
</form>

</body>
</html>
