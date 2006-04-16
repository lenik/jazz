<?php

require_once dirname(__FILE__) . '/../common-php/http.php'; 
require_once dirname(__FILE__) . '/../common-php/xml.php'; 
require_once dirname(__FILE__) . '/../common-php/error.php'; 

# .section. ZLW-AF Install Location

function zlw_af_base($req = NULL) {
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
    if ($req_len > 0 && substr($inc, 0, $req_len) == $req)
        return '.' . substr($inc, $req_len); 
    return $inc; 
}

global $ZLW_AF_BASE; 
$ZLW_AF_BASE = zlw_af_base(); 

# .section. Common Functions

function zlw_af_hint_prep($hint) {
    if ($hint == '*this*') {
        $hint = phpx_this_url(); 
        if ($pos = strpos($hint, '?')) {
            $hint = substr($hint, 0, $pos); 
        }
    }
    return $hint; 
}

function zlw_af_parameters($param, $tagname, $ns = '') {
    if (gettype($param) != 'array')
        return NULL; 
    $xml = ''; 
    foreach ($param as $name=>$value) {
        if (is_null($value)) continue;
        $xml .= phpx_xml_tag($tagname, array('name' => $name), $value, $ns); 
    }
    return $xml; 
}

# .section. XML Document

function zlw_af_xml_start($ns = '', $af_base = NULL) {
    if (is_null($af_base)) {
        global $ZLW_AF_BASE; 
        $af_base = $ZLW_AF_BASE; 
    }
    
    phpx_xml_header(); 
    echo "<?xml-stylesheet type=\"text/xsl\" href=\"$af_base/html-view.xsl\"?>\n"; 
    
    $uri = 'http://www.bodz.net/xml/zlw/abstract-form'; 
    if (strpos($ns, '=') !== false)
        list($ns, $uri) = explode('=', $ns, 2); 
    
    echo phpx_xml_start_tag('abstract-form' . phpx_xml_attrs(array(
        'xmlns' . ($ns ? ":$ns" : '') => $uri, 
        'xmlns:xsi' => 'http://www.w3.org/2001/XMLSchema-instance', 
        'xsi:schemaLocation' => "http://www.bodz.net/xml/zlw/abstract-form $af_base/abstract-form.xsd", 
        )), $ns); 
}

function zlw_af_xml_end($ns = '') {
    echo phpx_xml_end_tag('abstract-form', $ns); 
}

function zlw_af_section_start($ns = '', $name = NULL, $hint = NULL, 
                              $hidden = NULL) {
    echo phpx_xml_start_tag('section' . phpx_xml_attrs(array(
        'name' => $name, 
        'hint' => $hint, 
        'hidden' => $hidden, 
        )), $ns); 
}

function zlw_af_section_end($ns = '') {
    echo phpx_xml_end_tag('section', $ns); 
}

function zlw_af_xml_page($ns = '', $title = 'Abstract Form', $param = NULL) {
    # default af-base
    if (is_null($param)) $param = array(); 
    if (! array_key_exists('af-base', $param)) {
        global $ZLW_AF_BASE; 
        $param['af-base'] = $ZLW_AF_BASE; 
    }
    
    $xml = phpx_xml_start_tag('section name=".page"', $ns); 
    $xml .= phpx_xml_tag('scalar', array('name' => 'title'), $title, $ns); 
    foreach ($param as $name=>$value)
        $xml .= phpx_xml_tag('scalar', array('name' => $name), $value, $ns);
    $xml .= phpx_xml_end_tag('section', $ns);
    echo $xml; 
}

# .section. Error In XML

class zlw_af_error extends phpx_error {
    var $methods; 
    var $hint; 
    
    function zlw_af_error($summary, $source = NULL, $cause = NULL, $methods = NULL, $hint = '') {
        $this->phpx_error(ZLW_AF, $summary, $source, $cause); 
        $this->methods = $methods; 
        $this->hint = zlw_af_hint_prep($hint); 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('error' . phpx_xml_attrs(array(
            'time' => $this->time, 
            'provider' => $this->provider, 
            'type' => $this->type, 
            'name' => $this->name, 
            'text' => $this->text, 
            'detail' => $this->detail, 
            'hint' => $this->hint, 
            )), $ns); 
        
        if (! is_null($this->source)) {
            if (is_object($this->source))
                $source_name = get_class($this->source); 
            else
                $source_name = "$this->source"; 
            
            if (method_exists($this->source, '_source_status'))
                $status = $this->source->_source_status(); 
            
            $xml .= phpx_xml_tag('error-source', array(
                'name' => $source_name, 
                'status' => $status,
                ), NULL, $ns); 
        }
        
        if (is_object($this->cause)) {
            $cause_type = get_class($this->cause); 
            if (is_a($this->cause, 'zlw_af_error')) {
                $xml .= $this->cause->xml($ns); 
            } else if (is_a($this->cause, 'phpx_error')) {
                $zlw_wrapper = new zlw_af_error(NULL); 
                phpx_or($zlw_wrapper, $this->cause); 
                $xml .= $zlw_wrapper->xml($ns); 
            } else {
                die("Invalid error object: $this->cause"); 
            }
        }
        
        if (! is_null($this->methods)) {
            foreach (explode(':', $methods) as $method) {
                if ($method == '') continue; 
                $xml .= phpx_xml_tag('method', array('name' => $method), NULL, $ns); 
            }
        }
        
        $xml .= phpx_xml_end_tag('error', $ns); 
        return $xml; 
    }
}

function &zlw_af_error(&$src, $source = NULL, $cause = NULL) {
    if (is_string($src))
        return new zlw_af_error($src, $source, $cause); 
    if (is_a($src, 'zlw_af_error'))
        return $src; 
    if (is_a($src, 'phpx_error'))
        return new zlw_af_error(NULL, $source, $src); 
    die("Invalid error-src to be converted into zlw_af_error: $src"); 
    return NULL; 
}

class zlw_af_xmlem extends phpx_error_manager {
    var $ns; 
    
    function zlw_af_xmlem($provider = NULL, $using_ns = '') {
        $this->phpx_error_manager($provider); 
        $this->pref = PHPX_EM_TERM; 
    }
    function handler(&$error) {
        global $PHPX_XML_BEGUN; 
        if (get_class($error) == 'phpx_error') {
            $cast = new zlw_af_error(NULL); 
            foreach ($error as $k=>$v)
                $cast->$k = $error->$k; 
        } else {
            $cast = &zlw_af_error($error); 
        }
        if ($PHPX_XML_BEGUN) {
            echo $cast->xml($this->ns); 
        } else {
            # Early error (or warning, info..) is taken as 'parse error'
            echo zlw_af_xml_start($this->ns); 
            echo zlw_af_xml_page($this->ns, 'Fatal Error'); 
            echo zlw_af_section_start($this->ns, 'error'); 
            echo $cast->xml($this->ns); 
            echo zlw_af_section_end($this->ns); 
            echo zlw_af_xml_end($this->ns); 
            exit; 
        }
    }
}

function zlw_af_enable_xml_error($ns = '') {
    $em = new zlw_af_xmlem(ZLW_AF_XML, $ns); 
    return $em->register(); 
}

function zlw_af_disable_xml_error() {
    return phpx_error_manager_unregister(ZLW_AF_XML); 
}

?>