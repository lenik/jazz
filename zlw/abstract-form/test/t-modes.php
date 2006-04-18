
zlw_af_page_xxx
zlw_af_session('session name'); 
zlw_af_session_name(..); 

$_MODEL['sect']['var']['key']
$_SECTION['var']['key']

section model
    section-> xml()
        sequenced stream xml

<?php
/*
    # [af-objects] random buffered mode, error -> die
        $sect = new zlw_af_section('name'); 
        $sect->add_scalar(..); 
        $model->add_section($sect); 
        echo $model->xml(); 

        -or-
        
        $sect = new zlw_af_section($data[]...); 
        echo $sect->xml_begin('af');
            ..
        echo $sect->xml_end('af'); 
        
    # [af-modseq] sequenced stream xml mode (functional), error -> echo zlw_af_error->xml()
        zlw_af_set_xmlns('af');
        zlw_af_xml_section(.., $data[]); 
    
    # [af-modbuf] sequenced buffered mode, error -> current-section.add(err)
        $obj = zlw_af_section('newsect1'); 
        $obj->title(..); 
        $_SECTION['name'] = value..; 
        zlw_af_form('newform'); 
        ...
        echo zlw_af_build(); 
*/
?>