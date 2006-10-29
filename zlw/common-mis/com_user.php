<?php
    require_once dirname(__FILE__) . '/config.php'; 
    require_once 'zlw/common-php/http.php'; 
    require_once 'zlw/common-php/xml.php'; 
    require_once 'zlw/common-php/once.php'; 
    require_once 'zlw/common-php/dbi.php'; 
    require_once 'zlw/abstract-form/af-direct.php'; 
    require_once 'zlw/abstract-form/af-quick.php'; 
    
    
    phpx_transient(); 
    
    $reload = ! is_null($method = $_REQUEST['_method']); 
    $id = $_REQUEST['id']; 
    
    /* @var $db zlw_af_dbi */
    $db = zlw_af_connect_fast(true); 
    
    switch ($method) {
    case 'insert': 
    case 'update': 
        $db->_update_table('com_lang', phpx_noslashes($_REQUEST), 'id', $method); 
        break; 
    case 'delete': 
        $db->_query("delete from com_lang where id=$id"); 
        break; 
    default: 
        $reload = false; 
    }
    
    #if ($reload)
    #    phpx_redirect_relative(); 
    
    MODE('direct', 'af', 'Languages'); 
    SECTION('main'); 
    
    switch ($method) {
    case 'modify': 
        OUT($db->_query_edit(null, "select * from com_lang where id=$id", 'id', 
            phpx_noslashes($_REQUEST), null, 'update')); 
        break; 
    default: 
        OUT($db->_query_table(null, "select * from com_lang")); 
        OUT($db->_query_edit(null, "select * from com_lang where id=-1", 'id', 
            phpx_noslashes($_REQUEST), null, 'insert')); 
        break; 
    }
    
    FORM('navbar'); 
    METHOD('Language Preference', 'vbank_lang_pref.php'); 
create table com_user(
    id          int not null auto_increment,
    name        varchar(30) not null,   /* display name */
    login       int null,               /* ext, e.g. cux.login */
    info        int null,               /* ext, e.g. cux.uinfo */
    primary key pk(id),
    index       login(login),
    index       info(info)
    );
