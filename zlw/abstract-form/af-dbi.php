<?php
require '_Phpfixes.php'; 
_RequireOnce('http.php'); 

function phpx_af_error($desc, $code = NULL, $source = NULL) {
    $props = ''; 
    if (! is_null($code)) {
        $props .= " code=\"" . htmlspecialchars($code) . "\""; 
    }
    if (! is_null($source)) {
        $props .= " source=\"" . htmlspecialchars($source) . "\""; 
    }
    echo "<af:error $props>" . htmlspecialchars($desc) . "</af:error>\n"; 
}

function phpx_af_special_hints($hint) {
    if ($hint == '*this*') {
        $hint = phpx_this_url(); 
        if ($pos = strpos($hint, '?')) {
            $hint = substr($hint, 0, $pos); 
        }
    }
    return $hint; 
}

function phpx_af_query_as_input($dbi, $sql, $readonly_fields = '', 
                                $update_method = 'update', $name = '', $hint = '') {
    $result = $dbi->_query($sql); 
    if (! $result) {
        $code = $dbi->_errno(); 
        $desc = $dbi->_error(); 
        phpx_af_error($desc, $code); 
        return false; 
    }
    
    $hint = phpx_af_special_hints($hint); 
    $readonly_fields = explode(':', $readonly_fields); 
    
    echo "<af:form"; 
    if ($name)
        echo " name=\"" . htmlspecialchars($name) . "\""; 
    if ($hint)
        echo " hint=\"" . htmlspecialchars($hint) . "\""; 
    echo ">\n"; 
    
    $nfields = $dbi->_num_fields($result); 
    $row = $dbi->_fetch_row($result); 
    for ($i = 0; $i < $nfields; $i++) {
        $field = $dbi->_field_name($result, $i); 
        $value = $row[$i]; 
        echo "<af:input name=\"$field\" init=\"" . htmlspecialchars($value) . "\""; 
        if (in_array($field, $readonly_fields))
            echo " read-only=\"1\""; 
        echo "/>\n"; 
    }
    echo "<af:method name=\"" . htmlspecialchars($update_method) . "\"/>\n"; 
    echo "</af:form>\n"; 
    $dbi->_free_result($result); 
    return true; 
}

function phpx_af_query_as_table($dbi, $sql, $primary_key = 'id', 
                                $methods = 'delete:modify', $name = '', $hint = '', 
                                $page_size = -1, $page = 0) {
    $result = $dbi->_query($sql); 
    if (! $result) {
        $code = $dbi->_errno(); 
        $desc = $dbi->_error(); 
        phpx_af_error($desc, $code); 
        return false; 
    }
    
    $hint = phpx_af_special_hints($hint); 
    $keys = explode(':', $primary_key); 
    
    echo "<af:table"; 
    if ($name)
        echo " name=\"" . htmlspecialchars($name) . "\""; 
    echo ">\n"; 
    
    foreach (explode(':', $methods) as $method) {
        echo "<af:method name=\"" . htmlspecialchars($method) . "\""; 
        if ($hint)
            echo " hint=\"" . htmlspecialchars($hint) . "\""; 
        echo "/>\n"; 
    }
    
    $nfields = $dbi->_num_fields($result); 
    $fields = array(); 
    for ($i = 0; $i < $nfields; $i++) {
        $field = $dbi->_field_name($result, $i); 
        $fields[] = $field; 
        echo "<af:column name=\"$field\""; 
        if (in_array($field, $keys)) {
            echo " primary-key=\"true\""; 
        }
        echo "/>\n"; 
    }
    
    while ($row = $dbi->_fetch_row($result)) {
        echo "<af:row>\n"; 
        for ($i = 0; $i < $nfields; $i++) {
            $field = $fields[$i]; 
            echo "<$field>" . htmlspecialchars($row[$i]) . "</$field>\n"; 
        }
        echo "</af:row>\n"; 
    }
    echo "</af:table>\n"; 
    $dbi->_free_result($result); 
    return true; 
}

?>