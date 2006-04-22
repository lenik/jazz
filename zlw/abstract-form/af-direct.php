<?php

require_once dirname(__FILE__) . '/af-mode.php'; 

class zlw_af_mode_direct extends zlw_af_mode_base {
    private $xmlns;
    
    function zlw_af_mode_direct($xmlns = 'af') {
        $this->xmlns = $xmlns;
        phpx_xml_mode();
    }
    
    function on_error(&$error) {
        $this->output($error); 
        exit; 
    }
    
    function add_data($object) {
        echo phpx_xml_value($object, $this->xmlns); 
    }
    
    function add_input($object) {
        echo phpx_xml_value($object, $this->xmlns); 
    }

    function add_document($document) {
        parent::add_document($document);
        echo $document->xml_start($this->xmlns); 
    }

    function end_document() {
        $document = parent::end_document();
        echo $document->xml_sections($this->xmlns);
        echo $document->xml_end($this->xmlns); 
    }

    function add_section($section) {
        parent::add_section($section); 
        echo $section->xml_start($this->xmlns); 
    }

    function end_section() {
        $section = parent::end_section();
        echo $section->xml_items($this->xmlns);
        echo $section->xml_end($this->xmlns); 
    }
    
    function add_form($form) {
        parent::add_form($form); 
        echo $form->xml_start($this->xmlns);
    }

    function end_form() {
        $form = parent::end_form();
        echo $form->xml_items($this->xmlns);
        echo $form->xml_methods($this->xmlns);
        echo $form->xml_end($this->xmlns); 
    }
}

?>