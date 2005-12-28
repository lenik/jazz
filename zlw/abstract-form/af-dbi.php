<?php

require_once dirname(__FILE__) . "/../common-php/dbi.php"; 
require_once dirname(__FILE__) . "/af-model.php"; 

function zlw_af_query_edit($dbi, $sql, $key_fields = '', $values = array(), 
                           $name = '', $update_method = 'update', $hint = '') {
    $result = $dbi->_query($sql); 
    if (! $result) return false; 
    
    $hint = zlw_af_special_hints($hint); 
    $key_fields = explode(':', $key_fields); 
    $form = $update_method; 
    
    $xml = ''; 
    if ($form) {
        $xml .= "<af:form"; 
        if ($name)
            $xml .= " name=" . phpx_xml_attr($name); 
        if ($hint)
            $xml .= " hint=" . phpx_xml_attr($hint); 
        $xml .= ">\n"; 
    }
    
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
                $xml .= " init=" . phpx_xml_attr($value); 
            if ($row && in_array($field, $key_fields))
                $xml .= " read-only=\"1\""; 
            $xml .= "/>\n"; 
        }
    }
    
    if ($form) {
        $xml .= "<af:method name=" . phpx_xml_attr($update_method) . "/>\n"; 
        $xml .= "</af:form>\n"; 
    }
    $dbi->_free_result($result); 
    return $xml; 
}

function zlw_af_query_list($dbi, $sql, $field = 0, 
                           $name = '', $methods = 'delete:modify', $hint = '') {
    $result = $dbi->_query($sql); 
    if (! $result) return false; 
    
    $hint = zlw_af_special_hints($hint); 
    
    $xml = "<af:list"; 
    if ($name)
        $xml .= " name=" . phpx_xml_attr($name); 
    $xml .= ">\n"; 
    
    foreach (explode(':', $methods) as $method) {
        if ($method == '') continue; 
        $xml .= "<af:method name=" . phpx_xml_attr($method); 
        if ($hint)
            $xml .= " hint=" . phpx_xml_attr($hint); 
        $xml .= "/>\n"; 
    }
    
    while ($row = $dbi->_fetch_array($result)) {
        $value = $row[$field]; 
        $xml .= "<af:item>"; 
        $xml .= phpx_xml_text($value); 
        $xml .= "</af:item>\n"; 
    }
    $xml .= "</af:list>\n"; 
    $dbi->_free_result($result); 
    return $xml; 
}

function zlw_af_query_map($dbi, $sql, $key_field = 0, $value_format = ':', 
                          $name = '', $methods = 'delete:modify', $hint = '') {
    $result = $dbi->_query($sql); 
    if (! $result) return false; 
    
    $hint = zlw_af_special_hints($hint); 
    
    $xml = "<af:map"; 
    if ($name)
        $xml .= " name=" . phpx_xml_attr($name); 
    $xml .= ">\n"; 
    
    foreach (explode(':', $methods) as $method) {
        if ($method == '') continue; 
        $xml .= "<af:method name=" . phpx_xml_attr($method); 
        if ($hint)
            $xml .= " hint=" . phpx_xml_attr($hint); 
        $xml .= "/>\n"; 
    }
    
    while ($row = $dbi->_fetch_array($result)) {
        $key = $row[$key_field]; 
        $value = $row[$value_format]; 
        $xml .= "<af:entry key=" . phpx_xml_attr($key) . ">"; 
        $xml .= phpx_xml_text($value); 
        $xml .= "</af:entry>\n"; 
    }
    $xml .= "</af:map>\n"; 
    $dbi->_free_result($result); 
    return $xml; 
}

function zlw_af_query_table($dbi, $sql, $primary_key = 'id', 
                            $name = '', $methods = 'delete:modify', $hint = '', 
                            $page_size = -1, $page = 0) {
    $result = $dbi->_query($sql); 
    if (! $result) return false; 
    
    $hint = zlw_af_special_hints($hint); 
    $keys = explode(':', $primary_key); 
    
    $xml = "<af:table"; 
    if ($name)
        $xml .= " name=" . phpx_xml_attr($name); 
    $xml .= ">\n"; 
    
    foreach (explode(':', $methods) as $method) {
        if ($method == '') continue; 
        $xml .= "<af:method name=" . phpx_xml_attr($method); 
        if ($hint)
            $xml .= " hint=" . phpx_xml_attr($hint); 
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
            $xml .= "<$field>" . phpx_xml_text($row[$i]) . "</$field>\n"; 
        }
        $xml .= "</af:row>\n"; 
    }
    $xml .= "</af:table>\n"; 
    $dbi->_free_result($result); 
    return $xml; 
}

?>