<?php

require_once dirname(__FILE__) . "/af-base.php"; 

# .section. API Interface

global $ZLW_AF_XMLNS; 

function zlw_af_set_xmlns($xmlns = '') {
    global $ZLW_AF_XMLNS; 
    $ZLW_AF_XMLNS = $xmlns; 
}

function zlw_af_get_xmlns() {
    global $ZLW_AF_XMLNS; 
    return $ZLW_AF_XMLNS; 
}

function zlw_af_scalar($name, $value, $typestr = 'string', $hold = false, 
        $hidden = false, $methods = NULL) {
    global $ZLW_AF_XMLNS; 
    $o = new zlw_af_scalar($name, $value, $typestr, $hold, $hidden, $methods); 
    return $o->xml($ZLW_AF_XMLNS); 
}

function zlw_af_list() {
}

function zlw_af_map() {
}

function zlw_af_table() {
}

function zlw_af_user() {
}

function zlw_af_input() {
}

function zlw_af_method() {
}

function zlw_af_form() {
}

function zlw_af_section() {
}

# .section. XML Document

class zlw_af_xml {
    var $title = 'Abstract Form'; 
    var $af_base; 
    var $sections; 
    
    function zlw_af_xml() {
        global $ZLW_AF_BASE; 
        $this->af_base = $ZLW_AF_BASE; 
    }
    
    function xml_start($ns = '') {
        global $ZLW_AF_BASE; 
        
        phpx_xml_header(); 
        echo "<?xml-stylesheet type=\"text/xsl\" href=\"$ZLW_AF_BASE/html-view.xsl\"?>\n"; 
        
        $uri = 'http://www.bodz.net/xml/zlw/abstract-form'; 
        if (strpos($ns, '=') !== false)
            list($ns, $uri) = explode('=', $ns, 2); 
        
        phpx_xml_start_tag('abstract-form' . phpx_xml_attrs(array(
            'xmlns' . ($ns ? ":$ns" : '') => $uri, 
            'xmlns:xsi' => 'http://www.w3.org/2001/XMLSchema-instance', 
            'xsi:schemaLocation' => "http://www.bodz.net/xml/zlw/abstract-form $ZLW_AF_BASE/abstract-form.xsd", 
            )), $ns); 
        return $xml; 
    }
    
    function xml_end($ns = '') {
        return phpx_xml_end_tag('abstract-form', $ns); 
    }
    
    function xml_page($ns = '') {
        $xml = phpx_xml_start_tag('section name=' . phpx_xml_attr('.page'), $ns); 
        $xml .= zlw_af_xml_scalar('title', $this->title); 
        $xml .= zlw_af_xml_scalar('af-base', $this->af_base); 
        $xml .= phpx_xml_end_tag('section', $ns); 
        return $xml; 
    }
    
    function xml_sections($ns = '') {
        if ($this->sections)
            foreach ($this->sections as $section)
                $xml .= phpx_xml_xvalue($section, $ns); 
        return $xml; 
    }
    
    function xml($ns = '') {
        return $this->xml_start($ns)
            . $this->xml_page($ns)
            . $this->xml_sections($ns)
            . $this->xml_end($ns); 
    }
}

?>