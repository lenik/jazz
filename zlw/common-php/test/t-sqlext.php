<pre><?php

$sql = '
  %%
    echo "pre..."; 
    $sql .= " limit 100"; 
  %%
    echo "post..."; 
  %%
    the contents; 
';

$segs = explode('%%', $sql, 4); 
$pre = $segs[1]; 
$post = $segs[2]; 
$sql = $segs[3]; 

eval($pre); 
echo "MIDDLE [$sql]"; 
eval($post);

?></pre>