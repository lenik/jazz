<?php

/* Common-PHP
 *
 * Dynamic XML Support
 */

function phpx_xml_header($encoding = 'utf-8', $version = '1.0') {
    global $PHPX_XML_BEGUN; 
    header('Content-Type: text/xml'); 
    $nargs = func_num_args(); 
    if ($nargs > 2) {
        $args = func_get_args(); 
        for ($i = 2; $i < $nargs; $i++)
            header($args[$i]); 
    }
    echo "<?xml version='$version' encoding='$encoding'?>\n"; 
    $PHPX_XML_BEGUN = true; 
}

function phpx_xml_start_tag($tagmore, $ns = '', $close = false, $newline = true,
                            $indented = true) {
    if (strpos($ns, '=') !== false)
        list($ns, $uri) = explode('=', $ns, 2); 
    if ($indented) {
        global $PHPX_XML_INDENT; 
        $xml = $PHPX_XML_INDENT; 
        if (! $close)
            $PHPX_XML_INDENT .= '    '; 
    }
    $xml .= '<'; 
    if ($ns) $xml .= $ns . ':'; 
    $xml .= $tagmore; 
    if ($uri) {
        $xml .= " xmlns"; 
        if ($ns) $xml .= ":$ns"; 
        $xml .= '=' . phpx_xml_attr($uri); 
    }
    if ($close) $xml .= '/'; 
    $xml .= '>'; 
    if ($newline) $xml .= "\n"; 
    return $xml; 
}

function phpx_xml_end_tag($tagname, $ns = '', $newline = true, $indented = true) {
    if (strpos($ns, '=') !== false)
        list($ns, $uri) = explode('=', $ns, 2); 
    if ($indented) {
        global $PHPX_XML_INDENT; 
        $PHPX_XML_INDENT = substr($PHPX_XML_INDENT, 4); 
        $xml .= $PHPX_XML_INDENT; 
    }
    $xml .= $ns ? "</$ns:$tagname>" : "</$tagname>"; 
    if ($newline) $xml .= "\n"; 
    return $xml; 
}

function phpx_xml_tag($tagname, $attrs = NULL, $content = NULL, $ns = '',
                      $newline = true, $indented = true) {
    $tagmore = $tagname;
    if (! is_null($attrs))
        $tagmore .= phpx_xml_attrs($attrs); 
    $content = phpx_xml_value($content, $ns); 
    if ($content == '')
        return phpx_xml_start_tag($tagmore, $ns, true, $newline, $indented);
    if ($indented) {
        global $PHPX_XML_INDENT; 
        $xml = $PHPX_XML_INDENT; 
    }
    $xml .= phpx_xml_start_tag($tagmore, $ns, false, false, false)
        . $content
        . phpx_xml_end_tag($tagname, $ns, $newline, false);
    return $xml; 
}

function phpx_xml_value($value, $ns = '') {
    if (method_exists($value, 'xml'))
        return $value->xml($ns); 
    return htmlspecialchars($value); 
}

function phpx_xml_pi($pi) {
    echo '<?' . htmlspecialchars($pi) . '?>'; 
}

function phpx_xml_text($text, $indented = false) {
    if ($indented) {
        global $PHPX_XML_INDENT;
        $xml = $PHPX_XML_INDENT; 
    }
    return $xml . htmlspecialchars($text, ENT_NOQUOTES); 
}

function phpx_xml_attr($attr) {
    return '"' . htmlspecialchars($attr) . '"'; 
}

function phpx_xml_attrs($first) {
    if (is_null($first)) return NULL; 
    
    $n = func_num_args(); 
    $first_type = gettype($first); 
    
    if ($n == 1) {
        switch ($first_type) {
        case 'string': 
            return phpx_xml_attr($first); 
        case 'array': 
        case 'object': 
            foreach ($first as $name=>$value) {
                if ($value == NULL) continue; 
                $xml .= " $name=" . phpx_xml_attr($value); 
            }
            return $xml; 
        default: 
            die("Invalid argument type: $first"); 
        }
    }
    
    $args = func_get_args(); 
    if ($first_type == 'array') {
        # array, field, field, ...
        for ($i = 1; $i < $n; $i++) {
            if (is_null($args[$i])) continue; 
            foreach (explode(':', $args) as $name) {
                $value = $first[$name]; 
                $xml .= " $name=" . phpx_xml_attr($value); 
            }
        }
    } else if ($first_type == 'object') {
        # object, member, member, ...
        for ($i = 1; $i < $n; $i++) {
            if (is_null($args[$i])) continue; 
            foreach (explode(':', $args) as $name) {
                $value = $first->$name; 
                $xml .= " $name=" . phpx_xml_attr($value); 
            }
        }
    } else {
        # name, value, name, value, ...
        for ($i = 0; $i < $n; $i += 2) {
            $name = $args[$i]; 
            $value = $args[$i + 1]; 
            if (is_null($value)) continue; 
            $xml .= " $name=" . phpx_xml_attr($value); 
        }
    }
    return $xml; 
}

?>