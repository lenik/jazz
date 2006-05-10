<?php

require_once dirname(__FILE__) . '/../common-php/http.php'; 
require_once dirname(__FILE__) . '/../common-php/xml.php'; 
require_once dirname(__FILE__) . '/../common-php/once.php'; 
require_once dirname(__FILE__) . "/af-object.php";
require_once dirname(__FILE__) . "/af-dbi.php";

# .section.  mode ext functions

function zlw_af_query_table($dbi, $sqlrc, $keys = 'id', $name = null,
                            $methods = 'delete:modify', $hint = null,
                            $hidden = null) {
    $table = new zlw_af_table($name, null, null, null, null, $hidden,
                              $methods, $hint);
    $dbi->_query_view($sqlrc, $table, $keys, null);
    return $table; 
}

function zlw_af_query_map($dbi, $sqlrc, $keys = 'id', $format = null, $name = null,
                          $methods = 'delete:modify', $hint = null,
                          $hidden = null) {
    $map = new zlw_af_map($name, null, null, null, $hidden, $methods, $hint);
    $dbi->_query_view($sqlrc, $map, $keys, $format);
    return $map; 
}

function zlw_af_query_list($dbi, $sqlrc, $format = null, $name = null, 
                           $methods = 'delete:modify', $hint = '',
                           $hidden = null) {
    $list = new zlw_af_list($name, null, null, null, $hidden, $methods, $hint);
    $dbi->_query_view($sqlrc, $list, null, $format);
    return $list; 
}

function zlw_af_query_edit($dbi, $sqlrc, $keys = null, $init = null,
                           $name = null, $update_method = 'update',
                           $hint = null, $selection = null) {
    $methods = $update_method;
    if (is_null($methods)) $methods = 'update'; 
    
    $form = new zlw_af_form($name, null, null, $methods, $hint);
    $dbi->_query_edit($sqlrc, $form, $keys, $init, $selection);
    return $form; 
}

function _getvals($names) {
    foreach ($names as $name) {
        if (($name = trim($name)) == '') continue; 
        $vals[$name] = $_REQUEST[$name]; 
    }
    return $vals; 
}

function zlw_af_sp_crud($dbi, $table_name, $keys = null, $vars = null) {
    if (is_null($vars))
        $vars = phpx_noslashes($_REQUEST); 
    $method = $vars['_method']; 
    $reload = false; 
    switch ($method) {
    case 'insert': 
    case 'update': 
    case 'delete': 
        $db->_update_table($table, $req, $keys, $method); 
        $reload = true; 
    }
    
    if ($reload)
        phpx_redirect_relative(); 
    
    SECTION('zlw_af_sp_crud'); 
    
    switch ($method) {
    case 'modify': 
        if (is_null($keys)) $keys = 
        $keys = phpx_list_parse($keys); 
        $keyvals = _getvals($keys); 
        OUT($db->_query_edit(null, "select * from $table where $keyvals", $keys, 
            phpx_noslashes($_REQUEST), null, 'update')); 
        break; 
    default: 
        OUT($db->_query_table(null, "select * from $table")); 
        OUT($db->_query_edit(null, "select * from $table where id=-1", $keys, 
            phpx_noslashes($_REQUEST), null, 'insert')); 
        break; 
    }
}

?>