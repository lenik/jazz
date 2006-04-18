<?php

/* Common-PHP
 *
 * Dynamic XML Support
 */

# phpx_xml_mode(more_http_headers, ...)
function phpx_xml_mode() {
    global $PHPX_XML_MODE; 
    if ($PHPX_XML_MODE) {
        error_log('Already in xml mode'); 
        return; 
    }
    header('Content-Type: text/xml'); 
    $nargs = func_num_args(); 
    if ($nargs > 2) {
        $args = func_get_args(); 
        for ($i = 2; $i < $nargs; $i++)
            header($args[$i]); 
    }
    $PHPX_XML_MODE = true; 
}

function phpx_xml_header($encoding = 'utf-8', $version = '1.0') {
    return phpx_xml_pi('xml', "version='$version' encoding='$encoding'") . "\n"; 
}

function phpx_xml_pi($cmd, $text = null) {
    $xml = '<?' . $cmd; 
    if (! is_null($text)) {
        $xml .= ' ' . htmlspecialchars($text, ENT_NOQUOTES); 
    }
    return $xml . '?>'; 
}

function phpx_xml_stylesheet($href, $type = 'text/xsl') {
    return phpx_xml_pi('xml-stylesheet', phpx_xml_attrs(
                       'type', $type, 'href', $href)) . "\n"; 
}

function phpx_xml_text($text, $indented = false) {
    if ($indented) {
        global $PHPX_XML_INDENT;
        $xml = $PHPX_XML_INDENT; 
    }
    return $xml . htmlspecialchars($text, ENT_NOQUOTES); 
}

function phpx_xml_cdata($cdata, $indented = false) {
    if ($indented) {
        global $PHPX_XML_INDENT; 
        $xml = $PHPX_XML_INDENT;
    }
    return $xml . '<![CDATA[' . $cdata . ']]>'; 
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
    if (strpos($ns, '=') !== false) {
        list($ns, $uri) = explode('=', $ns, 2); 
        $uri;                           # uri is not used in eng-tag
    }
    if ($indented) {
        global $PHPX_XML_INDENT; 
        $PHPX_XML_INDENT = substr($PHPX_XML_INDENT, 4); 
        $xml .= $PHPX_XML_INDENT; 
    }
    $xml .= $ns ? "</$ns:$tagname>" : "</$tagname>"; 
    if ($newline) $xml .= "\n"; 
    return $xml; 
}

function phpx_xml_tag($tagname, $attrs = null, $content = null, $ns = '',
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

function phpx_xml_attr($attr) {
    return '"' . htmlspecialchars($attr) . '"'; 
}

# phpx_xml_attrs('attr-value')
# phpx_xml_attrs(array('name' => 'value'))
# phpx_xml_attrs(object(name->'value'))
# phpx_xml_attrs(array, field_name, field_name, ...)
# phpx_xml_attrs(object, member_name, member_name, ...)
# phpx_xml_attrs(name, value, name, value, ...)
function phpx_xml_attrs($first) {
    if (is_null($first)) return null; 
    
    $n = func_num_args(); 
    $first_type = gettype($first); 
    
    if ($n == 1) {
        switch ($first_type) {
        case 'string': 
            return phpx_xml_attr($first); 
        case 'array': 
        case 'object': 
            foreach ($first as $name=>$value) {
                if ($value == null) continue; 
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

function phpx_xml_value($value, $ns = '') {
    if (method_exists($value, 'xml'))
        return $value->xml($ns); 
    return htmlspecialchars($value); 
}

class phpx_xml_rawpiece {
    public $data; 
    function phpx_xml_rawpiece($data) {
        $this->data = $data; 
    }
    function xml($ns = '') {
        $ns; 
        return $this->data; 
    }
}

function phpx_xml_raw($data) {
    return new phpx_xml_rawpiece($data); 
}

class phpx_xml {
    public $version = '1.0'; 
    public $encoding = 'utf-8'; 
    public $xsl; 
    public $body;                       # may contain DTD declarations.
    
    function phpx_xml($body, $xsl = null, $encoding = null, $version = null) {
        $this->body = $body; 
        # if (! is_null($dtd)) $this->dtd = $dtd; 
        if (! is_null($xsl)) $this->xsl = $xsl; 
        if (! is_null($encoding)) $this->encoding = $encoding; 
        if (! is_null($version)) $this->version = $version; 
    }
    
    function xml_start($ns = '') {
        $ns; 
        $xml = phpx_xml_header($this->encoding, $this->version); 
        # if (isset($this->dtd))
        #     $xml .= '<!DOCTYPE Element SYSTEM "Path">'; 
        # <?xml-stylesheet type="text/xsl" href="testxsl-1.xslt"
        if (isset($this->xsl))
            $xml .= phpx_xml_stylesheet($this->xsl); 
        return $xml; 
    }
    function xml($ns = '') {
        $xml = $this->xml_start($ns); 
        return $xml . phpx_xml_value($this->body, $ns); 
    }
}

?>