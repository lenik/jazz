<?php

require_once dirname(__FILE__) . "/../common-php/http.php"; 
require_once dirname(__FILE__) . "/../common-php/xml.php"; 
require_once dirname(__FILE__) . "/../common-php/error.php"; 

function phpx_af_base($req = NULL) {
    $inc = dirname(__FILE__); 
    $inc = str_replace('\\', '/', $inc); 
    # URL-include, always using absolute url. 
    if (strpos($inc, '//') !== false)
        return $inc;                   # see t-dirname
    if (is_null($req))
        $req = realpath($_SERVER['SCRIPT_FILENAME']); 
    $req = str_replace('\\', '/', $req); 
    $req = dirname($req); 
    $req_len = strlen($req); 
    # starts-with ? 
    if (substr($inc, 0, $req_len) == $req)
        return '.' . substr($inc, $req_len); 
    return $inc; 
}

global $PHPX_AF_BASE; 
$PHPX_AF_BASE = phpx_af_base(); 

class phpx_af_em extends phpx_error_manager {
    function phpx_af_em($provider = 'ZLW-AF') {
        # this will register self
        $this->phpx_error_manager($provider); 
    }
    function handler(&$error) {
        global $PHPX_XML_BEGIN; 
        if ($PHPX_XML_BEGIN) {
            echo phpx_af_error($error); 
        } else {
            # Early Error.
            phpx_af_xml('&phpx_af_error', 'Fatal Error', true, $error); 
            exit; 
        }
    }
}

global $PHPX_AF_EM; 
$PHPX_AF_EM = new phpx_af_em(); 
$PHPX_AF_EM->register(); 
phpx_error_manager_connect_leaves($PHPX_AF_EM); 

function phpx_af_xml($content, $title = 'Abstract Form', $term = false) {
    phpx_af_xml_start($title); 
    
    echo "<af:section>\n"; 
    if (substr($content, 0, 1) == '&') {
        $func = substr($content, 1); 
        if (function_exists($func)) {
            $args = func_get_args(); 
            echo call_user_func_array($func, array_slice($args, 3)); 
        } else {
            echo $content; 
        }
    } else {
        echo $content; 
    }
    echo "</af:section>\n"; 
    
    phpx_af_xml_end(); 
    
    if ($term) exit; 
}

function phpx_af_xml_start($title = 'Abstract Form') {
    global $PHPX_AF_BASE; 
    
    phpx_xml_header(); 
    echo "<?xml-stylesheet type=\"text/xsl\" href=\"$PHPX_AF_BASE/html-view.xsl\"?>\n"; 
    echo "<af:abstract-form"
       . " xmlns=\"http://www.w3.org/1999/xhtml\""
       . " xmlns:af=\"http://www.bodz.net/xml/zlw/abstract-form\""
       . " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""
       . " xsi:schemaLocation=\"http://www.bodz.net/xml/zlw/abstract-form"
       . "   $PHPX_AF_BASE/abstract-form.xsd\">\n"; 
    echo "<af:section name=\".page\">\n"; 
    echo "<af:scalar name=\"title\">$title</af:scalar>\n"; 
    echo "<af:scalar name=\"af-base\">$PHPX_AF_BASE</af:scalar>\n"; 
    echo "</af:section>\n"; 
}

function phpx_af_xml_end() {
    echo "</af:abstract-form>\n"; 
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

function phpx_af_error($error_or_summary, $methods = NULL, $hint = '') {
    if (gettype($error_or_summary) == 'object')
        $error = $error_or_summary; 
    else
        $error = new phpx_error($error_or_summary); 
    
    $hint = phpx_af_special_hints($hint); 
    
    $xml = "<af:error"; 
    if (! is_null($error->time))
        $xml .= " time=" . phpx_xml_attr($error->time); 
    if (! is_null($error->provider))
        $xml .= " provider=" . phpx_xml_attr($error->provider); 
    if (! is_null($error->type))
        $xml .= " type=" . phpx_xml_attr($error->type); 
    if (! is_null($error->name))
        $xml .= " name=" . phpx_xml_attr($error->name); 
    if (! is_null($error->text))
        $xml .= " text=" . phpx_xml_attr($error->text); 
    if (! is_null($error->detail))
        $xml .= " detail=" . phpx_xml_attr($error->detail); 
    if ($hint)
        $xml .= " hint=" . phpx_xml_attr($hint); 
    $xml .= ">\n"; 
    
    if (! is_null($error->source)) {
        if (is_object($error->source))
            $source_name = get_class($error->source); 
        else
            $source_name = "$error->source"; 
        $xml .= "<af:error-source name=" . phpx_xml_attr($source_name); 
        if (method_exists($error->source, 'source_status')) {
            $status = $error->source->source_status(); 
            $xml .= " status=" . phpx_xml_attr($status); 
        }
        $xml .= "/>\n"; 
    }
    
    if (! is_null($error->cause)) {
        # XXX: may loop. 
        $xml .= phpx_af_error($error->cause); 
    }
    
    if (! is_null($methods)) {
        foreach (explode(':', $methods) as $method) {
            if ($method == '') continue; 
            $xml .= "<af:method name=" . phpx_xml_attr($method); 
            $xml .= "/>\n"; 
        }
    }
    
    $xml .= "</af:error>\n"; 
    return $xml; 
}

function phpx_af_input($name, $value = NULL, $readonly = false, $hint = NULL) {
    $xml = "<af:input name=" . phpx_xml_attr($name); 
    if (! is_null($value))
        $xml .= " init=" . phpx_xml_attr($value); 
    if ($readonly)
        $xml .= " read-only=\"1\""; 
    if (! is_null($hint))
        $xml .= " hint=" . phpx_xml_attr($hint); 
    $xml .= "/>\n"; 
    return $xml; 
}

function phpx_af_selector($name, $content, $value = NULL, $readonly = false, $hint = NULL) {
    $xml = "<af:selector name=" . phpx_xml_attr($name); 
    if (! is_null($value))
        $xml .= " init=" . phpx_xml_attr($value); 
    if ($readonly)
        $xml .= " read-only=\"1\""; 
    if (! is_null($hint))
        $xml .= " hint=" . phpx_xml_attr($hint); 
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

function phpx_af_method($name, $hint = NULL) {
    $xml = "<af:method name=" . phpx_xml_attr($name); 
    if (! is_null($hint))
        $xml .= " hint=" . phpx_xml_attr($hint); 
    $xml .= "/>\n"; 
    return $xml; 
}

?>