<?php
require '_Phpfixes.php'; 
_RequireOnce('xml.php'); 
_RequireOnce('http.php'); 

function phpx_af_base($user = NULL) {
    $home = dirname(__FILE__); 
    
    # URL-include, always using absolute url. 
    if (strpos($home, '//') !== false)
        return $home;                   # see t-dirname
    
    if (is_null($user))
        $user = realpath($_SERVER['PHP_SELF']); 
    $user = dirname($user); 
    $user_len = strlen($user); 
    
    # starts-with ? 
    if (substr($home, 0, $user_len) == $user)
        return '.' . substr($home, $user_len); 
    return $home; 
}
global $PHPX_AF_BASE; 
$PHPX_AF_BASE = phpx_af_base(); 

# AF-Logger
function phpx_af_logger($dbi, $level, $message) {
    $source = 'AF'; 
    $code = 0; 
    if (! is_null($dbi)) {
        $source = 'AF-DBI'; 
        $code = $dbi->_errno(); 
        $message .= "\nDBI Status (The status may be not reflect the current state): \n"
            . $dbi->_error(); 
    }
    global $PHPX_XML_BEGIN; 
    if ($PHPX_XML_BEGIN) {
        echo phpx_af_error($message, $code, $source); 
    } else {
        global $PHPX_AF_BASE; 
        phpx_af_xml($PHPX_AF_BASE, '&phpx_af_error', 'Error', true, 
            $message, $code, $source); 
    }
}
global $PHPX_DBI_LOGGER; 
$PHPX_DBI_LOGGER = phpx_af_logger; 

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
            echo call_user_func_array($func, array_slice($args, 4)); 
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
    
    $xml = "<af:error"; 
    if (! is_null($code))
        $xml .= " code=\"" . htmlspecialchars($code) . "\""; 
    if (! is_null($source))
        $xml .= " source=\"" . htmlspecialchars($source) . "\""; 
    $xml .= ">"; 
    if (is_null($err))
        $xml .= 'Unnamed error'; 
    else
        $xml .= htmlspecialchars($err); 
    $xml .= "</af:error>\n"; 
    return $xml; 
}

function phpx_af_input($name, $value = NULL, $readonly = false, $hint = NULL) {
    $xml = "<af:input name=\"" . htmlspecialchars($name) . "\""; 
    if (! is_null($value))
        $xml .= " init=\"" . htmlspecialchars($value) . "\""; 
    if ($readonly)
        $xml .= " read-only=\"1\""; 
    if (! is_null($hint))
        $xml .= " hint=\"" . htmlspecialchars($hint) . "\""; 
    $xml .= "/>\n"; 
    return $xml; 
}

function phpx_af_selector($name, $content, $value = NULL, $readonly = false, $hint = NULL) {
    $xml = "<af:selector name=\"" . htmlspecialchars($name) . "\""; 
    if (! is_null($value))
        $xml .= " init=\"" . htmlspecialchars($value) . "\""; 
    if ($readonly)
        $xml .= " read-only=\"1\""; 
    if (! is_null($hint))
        $xml .= " hint=\"" . htmlspecialchars($hint) . "\""; 
    $xml .= ">"; 
    
    if (substr($content, 0, 1) == '<') {
           $xml .= $content; 
    } else {
        $ref_type = 'list-ref'; 
        if (($pos = strpos($content, ':')) !== false) {
            $ref_type = substr($content, 0, $pos) . '-ref'; 
            $ref_name = substr($content, $pos + 1); 
        } else {
            $ref_name = $content; 
        }
        $xml .= "<af:$ref_type>$ref_name</af:$ref_type>"; 
    }
    $xml .= "</af:selector>\n"; 
    return $xml; 
}

function phpx_af_query_edit($dbi, $sql, $key_fields = '', $values = array(), 
                            $name = '', $update_method = 'update', $hint = '') {
    $result = $dbi->_query($sql); 
    if (! $result) {
        $code = $dbi->_errno(); 
        $desc = $dbi->_error(); 
        return phpx_af_error($desc, $code); 
    }
    
    $hint = phpx_af_special_hints($hint); 
    $key_fields = explode(':', $key_fields); 
    
    $xml = "<af:form"; 
    if ($name)
        $xml .= " name=\"" . htmlspecialchars($name) . "\""; 
    if ($hint)
        $xml .= " hint=\"" . htmlspecialchars($hint) . "\""; 
    $xml .= ">\n"; 
    
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
            $xml .= "<af:input name=\"$field\""; 
            if (! is_null($value))
                $xml .= " init=\"" . htmlspecialchars($value) . "\""; 
            if ($row && in_array($field, $key_fields))
                $xml .= " read-only=\"1\""; 
            $xml .= "/>\n"; 
        }
    }
    $xml .= "<af:method name=\"" . htmlspecialchars($update_method) . "\"/>\n"; 
    $xml .= "</af:form>\n"; 
    $dbi->_free_result($result); 
    return $xml; 
}

function phpx_af_query_list($dbi, $sql, $field = 0, 
                            $name = '', $methods = 'delete:modify', $hint = '') {
    $result = $dbi->_query($sql); 
    if (! $result) {
        $code = $dbi->_errno(); 
        $desc = $dbi->_error(); 
        return phpx_af_error($desc, $code); 
    }
    
    $hint = phpx_af_special_hints($hint); 
    
    $xml = "<af:list"; 
    if ($name)
        $xml .= " name=\"" . htmlspecialchars($name) . "\""; 
    $xml .= ">\n"; 
    
    foreach (explode(':', $methods) as $method) {
        if ($method == '') continue; 
        $xml .= "<af:method name=\"" . htmlspecialchars($method) . "\""; 
        if ($hint)
            $xml .= " hint=\"" . htmlspecialchars($hint) . "\""; 
        $xml .= "/>\n"; 
    }
    
    while ($row = $dbi->_fetch_array($result)) {
        $value = $row[$field]; 
        $xml .= "<af:item>"; 
        $xml .= htmlspecialchars($value); 
        $xml .= "</af:item>\n"; 
    }
    $xml .= "</af:list>\n"; 
    $dbi->_free_result($result); 
    return $xml; 
}

function phpx_af_query_map($dbi, $sql, $key_field = 0, $value_format = ':', 
                           $name = '', $methods = 'delete:modify', $hint = '') {
    $result = $dbi->_query($sql); 
    if (! $result) {
        $code = $dbi->_errno(); 
        $desc = $dbi->_error(); 
        return phpx_af_error($desc, $code); 
    }
    
    $hint = phpx_af_special_hints($hint); 
    
    $xml = "<af:map"; 
    if ($name)
        $xml .= " name=\"" . htmlspecialchars($name) . "\""; 
    $xml .= ">\n"; 
    
    foreach (explode(':', $methods) as $method) {
        if ($method == '') continue; 
        $xml .= "<af:method name=\"" . htmlspecialchars($method) . "\""; 
        if ($hint)
            $xml .= " hint=\"" . htmlspecialchars($hint) . "\""; 
        $xml .= "/>\n"; 
    }
    
    while ($row = $dbi->_fetch_array($result)) {
        $key = $row[$key_field]; 
        $value = $row[$value_format]; 
        $xml .= "<af:entry key=\"" . htmlspecialchars($key) . "\">"; 
        $xml .= htmlspecialchars($value); 
        $xml .= "</af:entry>\n"; 
    }
    $xml .= "</af:map>\n"; 
    $dbi->_free_result($result); 
    return $xml; 
}

function phpx_af_query_table($dbi, $sql, $primary_key = 'id', 
                             $name = '', $methods = 'delete:modify', $hint = '', 
                             $page_size = -1, $page = 0) {
    $result = $dbi->_query($sql); 
    if (! $result) {
        $code = $dbi->_errno(); 
        $desc = $dbi->_error(); 
        return phpx_af_error($desc, $code); 
    }
    
    $hint = phpx_af_special_hints($hint); 
    $keys = explode(':', $primary_key); 
    
    $xml = "<af:table"; 
    if ($name)
        $xml .= " name=\"" . htmlspecialchars($name) . "\""; 
    $xml .= ">\n"; 
    
    foreach (explode(':', $methods) as $method) {
        if ($method == '') continue; 
        $xml .= "<af:method name=\"" . htmlspecialchars($method) . "\""; 
        if ($hint)
            $xml .= " hint=\"" . htmlspecialchars($hint) . "\""; 
        $xml .= "/>\n"; 
    }
    
    $nfields = $dbi->_num_fields($result); 
    $fields = array(); 
    for ($i = 0; $i < $nfields; $i++) {
        $field = $dbi->_field_name($result, $i); 
        $fields[] = $field; 
        $xml .= "<af:column name=\"$field\""; 
        if (in_array($field, $keys)) {
            $xml .= " primary-key=\"true\""; 
        }
        $xml .= "/>\n"; 
    }
    
    while ($row = $dbi->_fetch_row($result)) {
        $xml .= "<af:row>\n"; 
        for ($i = 0; $i < $nfields; $i++) {
            $field = $fields[$i]; 
            $xml .= "<$field>" . htmlspecialchars($row[$i]) . "</$field>\n"; 
        }
        $xml .= "</af:row>\n"; 
    }
    $xml .= "</af:table>\n"; 
    $dbi->_free_result($result); 
    return $xml; 
}

?>