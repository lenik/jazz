<?
    require '_Phpfixes.php'; 
    _RequireOnce('../http.php'); 
    
    $a = 0; 
    $b = 0; 
    
    if (isset($_REQUEST['submit'])) {
        $a = $_REQUEST['a']; 
        $b = $_REQUEST['b']; 
    }
    $args = httpcall_input(); 
    
    if (! is_null($args)) {
        # being called
        $a = $args['a']; 
        $b = $args['b']; 
    }
    
    $ret = $a + $b; 
    
    if (! is_null($args)) {
        httpcall_return($ret); 
    }
?>
<html>
<body>

<h1> Add Function </h1>

A = <?=$a?> <br>
B = <?=$b?> <br>
A + B = <?=$ret?> <br>

<form method='get'>
    A = <input type=text name=a value=100> <br>
    B = <input type=text name=b value=200> <br>
    <input type=submit name=submit value='Calculate'>
</form>

</body>
</html>
