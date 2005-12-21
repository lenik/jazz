<?php

    require_once dirname(__FILE__) . '/../http.php'; 
    
    $a = 3; 
    $b = 4; 
    $args = array(
        'a' => $a, 
        'b' => $b); 
    
    phpx_httpcall_stack(); 
    $ret = phpx_httpcall(phpx_url_relative("t-add.php"), $args); 
    #echo "<pre>T:", var_dump($ret); exit;
?>
<html>
<body>

<h1>Caller</h1>

<?php echo $a?> + <?php echo $b?> = <?php echo $ret['VAL']?>

</body>
</html>
