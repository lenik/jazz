<?
    require '_Phpfixes.php'; 
    _RequireOnce('../http.php'); 
    
    $a = 3; 
    $b = 4; 
    $args = array(
        'a' => $a, 
        'b' => $b); 
    
    httpcall_stack(); 
    $ret = httpcall(url_relative("t-add.php"), $args); 
    #echo "<pre>T:", var_dump($ret); exit;
?>
<html>
<body>

<h1>Caller</h1>

<?=$a?> + <?=$b?> = <?=$ret['VAL']?>

</body>
</html>
