<?php
require '_Phpfixes.php'; 
_RequireOnce('http.php'); 

function phpx_af_table($dbi, $name, $sql, $primary_key = 'id', 
                       $methods = 'insert:delete:modify', 
                       $hint = NULL, $page_size = -1, $page = 0) {
    if ($hint == NULL) {
        $hint = phpx_this_url(); 
        if ($pos = strpos($hint, '?')) {
            $hint = substr($hint, 0, $pos); 
        }
    }
    $keys = explode(':', $primary_key); 
    
    echo "<af:table name='$name'>\n"; 
    foreach (explode(':', $methods) as $method) {
        echo "<af:method name='$method' hint='$path'/>\n"; 
    }
    
    $result = $dbi->_query($sql); 
    $nfields = $dbi->_num_fields($result); 
    $fields = array(); 
    for ($i = 0; $i < $nfields; $i++) {
        $field = $dbi->_field_name($result, $i); 
        $fields[] = $field; 
        echo "<af:column name='$field'"; 
        if (in_array($field, $keys)) {
            echo " primary-key='true'"; 
        }
        echo "/>\n"; 
    }
    
    while ($row = $dbi->_fetch_row($result)) {
        echo "<af:row>\n"; 
        for ($i = 0; $i < $nfields; $i++) {
            $field = $fields[$i]; 
            echo "<$field>$row[$i]</$field>\n"; 
        }
        echo "</af:row>\n"; 
    }
    echo "</af:table>\n"; 
}

?>