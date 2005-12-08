<?php
require '_Phpfixes.php'; 
_RequireOnce('xml.php'); 
_RequireOnce('http.php'); 

function phpx_af_xml($af_dir, $content, $title = 'Abstract Form', $term = false) {
    phpx_xml_header(); 
    echo "<?xml-stylesheet type=\"text/xsl\" href=\"$af_dir/html-view.xsl\"?>\n"; 
    echo "<af:abstract-form"
       . " xmlns=\"http://www.w3.org/1999/xhtml\""
       . " xmlns:af=\"http://www.bodz.net/xml/zlw/abstract-form\""
       . " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
       . " xsi:schemaLocation=\"http://www.bodz.net/xml/zlw/abstract-form"
       . "   $af_dir/abstract-form.xsd\">\n"; 
    echo "<af:section name=\".page\">\n"; 
    echo "<af:scalar name=\"title\">$title</af:scalar>\n"; 
    echo "</af:section>\n"; 
    echo "<af:section>\n"; 
    if (substr($content, 0, 1) == '&') {
        $func = substr($content, 1); 
        if (function_exists($func)) {
            $args = func_get_args(); 
            call_user_func_array($func, array_slice($args, 4)); 
        } else {
            echo $content; 
        }
    } else {
        echo $content; 
    }
    echo "</af:section>\n"; 
    echo "</af:abstract-form>\n"; 
    if ($term)
        exit; 
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

function phpx_af_error($err, $code = NULL, $source = NULL) {
    switch (get_class($err)) {
    case 'phpx_dbi': 
        $source = 'phpx-dbi'; 
        $code = $err->_errno(); 
        $err = $err->_error(); 
        break; 
    }
    
    echo "<af:error"; 
    if (! is_null($code))
        echo " code=\"" . htmlspecialchars($code) . "\""; 
    if (! is_null($source))
        echo " source=\"" . htmlspecialchars($source) . "\""; 
    echo ">"; 
    if (is_null($err))
        echo 'Unnamed error'; 
    else
        echo htmlspecialchars($err); 
    echo "</af:error>\n"; 
}

function phpx_af_input($name, $value = NULL, $readonly = false, $hint = NULL) {
    echo "<af:input name=\"" . htmlspecialchars($name) . "\""; 
    if (! is_null($value))
        echo " init=\"" . htmlspecialchars($value) . "\""; 
    if ($readonly)
        echo " read-only=\"1\""; 
    if (! is_null($hint))
        echo " hint=\"" . htmlspecialchars($hint) . "\""; 
    echo "/>\n"; 
}

function phpx_af_query_edit($dbi, $sql, $key_fields = '', $values = array(), 
                            $update_method = 'update', $name = '', $hint = '') {
    $result = $dbi->_query($sql); 
    if (! $result) {
        $code = $dbi->_errno(); 
        $desc = $dbi->_error(); 
        phpx_af_error($desc, $code); 
        return false; 
    }
    
    $hint = phpx_af_special_hints($hint); 
    $key_fields = explode(':', $key_fields); 
    
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
        if (array_key_exists($field, $values))
            $value = $values[$field]; 
        else if ($row)
            $value = $row[$i]; 
        else
            $value = NULL; 
        
        $flags = explode(' ', $dbi->_field_flags($result, $i)); 
        if ((! $row) && in_array('auto_increment', $flags)) {
            # insert new, skip this input
        } else {
            echo "<af:input name=\"$field\""; 
            if (! is_null($value))
                echo " init=\"" . htmlspecialchars($value) . "\""; 
            if ($row && in_array($field, $key_fields))
                echo " read-only=\"1\""; 
            echo "/>\n"; 
        }
    }
    echo "<af:method name=\"" . htmlspecialchars($update_method) . "\"/>\n"; 
    echo "</af:form>\n"; 
    $dbi->_free_result($result); 
    return true; 
}

function phpx_af_query_view($dbi, $sql, $primary_key = 'id', 
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