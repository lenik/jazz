




        if ($PHPX_XML_MODE) {
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
<?php

require_once dirname(__FILE__) . '/../common-php/http.php'; 
require_once dirname(__FILE__) . '/../common-php/xml.php'; 
require_once dirname(__FILE__) . '/../common-php/once.php'; 
require_once dirname(__FILE__) . "/af-object.php";
require_once dirname(__FILE__) . "/af-dbi.php";

function zlw_af_quick_table($table_name) {
    # zlw_af_enable_xml_error(); 
    
    $reload = ! is_null($method = $_REQUEST['_method']); 
    $id = $_REQUEST['id']; 
    
    $db = zlw_af_connect_fast(true); 
    
    switch ($method) {
    case 'insert': 
    case 'update': 
        $db->_update_table('vbank_lang', phpx_noslashes($_REQUEST), 'id', $method); 
        break; 
    case 'delete': 
        $db->_query("delete from vbank_lang where id=$id"); 
        break; 
    default: 
        $reload = false; 
    }
    
    if ($reload)
        phpx_redirect_relative(); 
    
    phpx_transient(); 
    
    # $af = new zlw_af_xml('Languages'); 
    # echo $af->xml_start(); 
    # echo $af->xml_page(); 
    
    $sec = new zlw_af_section(); 
    echo $sec->xml_start(); 
    
    switch ($method) {
    case 'modify': 
        echo zlw_af_query_edit($db, "select * from vbank_lang where id=$id", 'id', 
            phpx_noslashes($_REQUEST), '', 'update', 'common-php/context.php'); 
        break; 
    default: 
        echo zlw_af_query_table($db, "select * from vbank_lang"); 
        echo zlw_af_query_edit($db, "select * from vbank_lang where id=-1", 'id', 
            phpx_noslashes($_REQUEST), '', 'insert'); 
        break; 
    }
    
    echo zlw_af_form(null, null, null, 
        new zlw_af_method('Language Preference', 'vbank_lang_pref.php')
        ); 
    
    echo $sec->xml_end(); 
    # echo $af->xml_end(); 
}
?>