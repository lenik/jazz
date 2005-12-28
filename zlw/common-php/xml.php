<?php

/* Common-PHP
 *
 * Dynamic XML Support
 */

function phpx_xml_header($encoding = 'utf-8', $version = '1.0') {
    global $PHPX_XML_BEGIN; 
    header('Content-Type: text/xml'); 
    $nargs = func_num_args(); 
    if ($nargs > 2) {
        $args = func_get_args(); 
        for ($i = 2; $i < $nargs; $i++)
            header($args[$i]); 
    }
    echo "<?xml version='$version' encoding='$encoding'?>\n"; 
    $PHPX_XML_BEGIN = true; 
}

function phpx_xml_start_tag($tagname_and_attrs, $xmlns = '', $close = false, 
        $newline = true, $indented = true) {
    if (strpos($xmlns, '=') !== false)
        list($xmlns, $uri) = explode('=', $xmlns, 2); 
    if ($indented) {
        global $PHPX_XML_INDENT; 
        $xml = $PHPX_XML_INDENT; 
        if (! $close)
            $PHPX_XML_INDENT .= '    '; 
    }
    $xml .= '<'; 
    if ($xmlns) $xml .= $xmlns . ':'; 
    $xml .= $tagname_and_attrs; 
    if ($uri) {
        $xml .= " xmlns"; 
        if ($xmlns) $xml .= ":$xmlns"; 
        $xml .= '=' . phpx_xml_attr($uri); 
    }
    if ($close) $xml .= '/'; 
    $xml .= '>'; 
    if ($newline) $xml .= "\n"; 
    return $xml; 
}

function phpx_xml_end_tag($tagname, $xmlns = '', $newline = true, $indented = true) {
    if (strpos($xmlns, '=') !== false)
        list($xmlns, $uri) = explode('=', $xmlns, 2); 
    if ($indented) {
        global $PHPX_XML_INDENT; 
        $PHPX_XML_INDENT = substr($PHPX_XML_INDENT, 4); 
        $xml .= $PHPX_XML_INDENT; 
    }
    $xml = $xmlns ? "</$xmlns:$tagname>" : "</$tagname>"; 
    if ($newline) $xml .= "\n"; 
    return $xml; 
}

function phpx_xml_pi($pi) {
    echo '<?' . htmlspecialchars($pi) . '?>'; 
}

function phpx_xml_text($text) {
    return htmlspecialchars($text, ENT_NOQUOTES); 
}

function phpx_xml_attr($attr) {
    return '"' . htmlspecialchars($attr) . '"'; 
}

function phpx_xml_attrs() {
    $n = func_num_args(); 
    if ($n == 0) return ''; 
    
    $args = func_get_args(); 
    $first = &$args[0]; 
    $first_type = gettype($first); 
    
    if ($n == 1) {
        switch ($first_type) {
        case 'string': 
            return phpx_xml_attr($first); 
        case 'array': 
        case 'object': 
            foreach ($first as $name=>$value) {
                $xml .= " $name=" . phpx_xml_attr($value); 
            }
            return $xml; 
        default: 
            return ''; 
        }
    }
    
    if ($first_type == 'array') {
        for ($i = 1; $i < $n; $i++) {
            if (is_null($args[$i])) continue; 
            foreach (explode(':', $args) as $name) {
                $value = $first[$name]; 
                $xml .= " $name=" . phpx_xml_attr($value); 
            }
        }
    } else if ($first_type == 'object') {
        for ($i = 1; $i < $n; $i++) {
            if (is_null($args[$i])) continue; 
            foreach (explode(':', $args) as $name) {
                $value = $first->$name; 
                $xml .= " $name=" . phpx_xml_attr($value); 
            }
        }
    } else {
        for ($i = 0; $i < $n; $i += 2) {
            $name = $args[$i]; 
            $value = $args[$i + 1]; 
            if (is_null($value)) continue; 
            $xml .= " $name=" . phpx_xml_attr($value); 
        }
    }
    return $xml; 
}

function phpx_xml_xvalue($xvalue, $ns = '') {
    if (method_exists($xvalue, 'xml')) {
        return $xvalue->xml($ns); 
    }
    return '' . $xvalue; 
}

?>