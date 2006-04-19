<?php

require_once dirname(__FILE__) . "/af-object.php";
require_once dirname(__FILE__) . "/af-dbi.php";
require_once dirname(__FILE__) . '/af-mode.php'; 

class zlw_af_mode_echo extends zlw_af_mode_base {
    private $xmlns;
    
    function zlw_af_mode_echo($xmlns = 'af') {
        $this->xmlns = $xmlns;
    }
    
    function process($o) {
        echo phpx_xml_value($o, $this->xmlns); 
    }
    
    function error_handler(&$error) {
        if (isset($this->document)) {
            echo $this->process($error); 
        } else {
            $doc = new zlw_af_document('Error', null, new zlw_af_section(
                    'first', null, null, $error));
            echo $this->process($doc); 
        }
        exit; 
    }

    function form_start($name, $typestr = 'default', 
                        $methods = null, $hint = null, $form_method = null) {
        parent::form_start($name, $typestr, $methods, $hint, $form_method);
        echo $this->form->xml_start($this->xmlns);
    }

    function form_end() {
        $form = parent::form_end();
        echo $form->xml_items($this->xmlns);
        echo $form->xml_methods($this->xmlns);
        echo $form->xml_end($this->xmlns); 
    }

    function section_start($name = null, $hint = null, $hidden = null) {
        parent::section_start($name, $hint, $hidden);
        echo $this->section->xml_start($this->xmlns); 
    }

    function section_end() {
        $section = parent::section_end();
        echo $section->xml_items($this->xmlns);
        echo $section->xml_end($this->xmlns); 
    }

    function document_start($title = 'Abstract Form', $params = null,
                            $sections = null, $af_base = null, $xsl = null,
                            $encoding = null, $version = null) {
        parent::document_start($title, $params, $sections, $af_base,
                               $xsl, $encoding, $version);
        echo $this->document->xml_start($this->xmlns); 
    }

    function document_end() {
        $document = parent::document_end();
        echo $document->xml_sections($this->xmlns);
        echo $document->xml_end($this->xmlns); 
    }
}

function zlw_af_seq_mode($xmlns = '') {
    global $ZLW_AF_MODE; 
    $ZLW_AF_MODE = new zlw_af_mode_echo($xmlns); 
    phpx_xml_mode();
}

?>