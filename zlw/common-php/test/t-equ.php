<pre>
<?php

$n = array(
    NULL, 
    false, 
    0, 
    0.0, 
    '', 
    ' ', 
    '0', 
    '0.0', 
    array(), 
    ); 
$nn = array(
    'NULL', 
    'false', 
    '0', 
    '0.0', 
    "''", 
    "' '", 
    "'0'", 
    "'0.0'", 
    "{}", 
    ); 
    
$s = sizeof($n); 
$buf = ''; 
for ($i = 0; $i < $s; $i++) {
    if ($i == 0) {
        echo "      "; 
        for ($j = 0; $j < $s; $j++) {
            printf("%6s", $nn[$j]); 
        }
        printf("\n");
    }
    printf("%6s", $nn[$i]); 
    for ($j = 0; $j < $s; $j++) {
        $rel = $n[$i] == $n[$j]; 
        printf("%6s", $n[$i] == $n[$j]); 
        if (! $rel)
            $buf .= $nn[$i] . " <> " . $nn[$j] . "\n"; 
    }
    printf("\n"); 
}
echo $buf; 

?>
</pre>