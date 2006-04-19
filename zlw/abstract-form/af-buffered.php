<?php

require_once dirname(__FILE__) . "/af-object.php";
require_once dirname(__FILE__) . "/af-dbi.php";
require_once dirname(__FILE__) . '/af-mode.php'; 

class zlw_af_mode_buffered extends zlw_af_mode_base {
    private $xmlns;

    function zlw_af_mode_buffered($xmlns = 'af') {
        $this->xmlns = $xmlns;
    }
    
    function process($o) {
        if (isset($this->form))
            $this->form->add($o);
        elseif (isset($this->section))
            $this->section->add($o);
        elseif (isset($this->document))
            $this->document->add($o);
        return $o; 
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
    
    function form_end() {
        $form = parent::form_end();
        return $this->process($form); 
    }

    function section_end() {
        $section = parent::section_end();
        return $this->process($section); 
    }

    function document_end() {
        $document = parent::document_end();
        phpx_xml_mode();
        echo phpx_xml_value($document, $this->xmlns);
    }
}

function zlw_af_buf_mode($xmlns = '') {
    global $ZLW_AF_MODE; 
    $ZLW_AF_MODE = new zlw_af_mode_buffered($xmlns); 
}

?>