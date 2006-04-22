<?php

require_once dirname(__FILE__) . '/af-mode.php'; 

class zlw_af_mode_buffered extends zlw_af_mode_base {
    private $xmlns;

    function zlw_af_mode_buffered($xmlns = 'af') {
        $this->xmlns = $xmlns;
    }
    
    function output($output) {
        $this->context()->add($object);
    }
    
    function on_error(&$error) {
        $this->output($error); 
        exit; 
    }
    
    function end_document() {
        $document = parent::end_document();
        phpx_xml_mode();
        echo phpx_xml_value($document, $this->xmlns);
    }
    
    function end_section() {
        $section = parent::end_section();
        return $this->output($section); 
    }
    
    function end_form() {
        $form = parent::form_end();
        return $this->output($form); 
    }
}

?>