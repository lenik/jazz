<?php
    require_once dirname(__FILE__) . '/t-config.php'; 
    require_once 'zlw/common-php/http.php'; 
    require_once 'zlw/common-php/xml.php'; 
    require_once 'zlw/common-php/once.php'; 
    require_once 'zlw/common-php/dbi.php'; 
    require_once 'zlw/abstract-form/af-direct.php'; 
    require_once 'zlw/abstract-form/af-quick.php'; 
    
    phpx_transient(); 
    ob_start(); 
    
    MODE('direct', 'af', 'Languages'); 
    
    /* @var $db zlw_af_dbi */
    $db = zlw_af_connect_fast(true); 
    
    if (is_null($_REQUEST['_method'])) {
        switch ('') {
        case 'd': 
            $_REQUEST['_method'] = 'delete'; 
            $_REQUEST['id'] = 4000; 
            break; 
        case 'i': 
            $_REQUEST['_method'] = 'insert'; 
            $_REQUEST['id'] = 4000; 
            $_REQUEST['code'] = 'te_ST'; 
            $_REQUEST['name_en'] = 'test lang'; 
            $_REQUEST['loc_en'] = 'test loc'; 
            $_REQUEST['sample'] = 'a test'; 
            break; 
        case 'm': 
            $_REQUEST['_method'] = 'modify'; 
            $_REQUEST['id'] = 4000; 
            break; 
        }
    }
    
    zlw_af_sp_crud($db, 'com_lang'); 
?>