<?php

require_once dirname(__FILE__) . "/af-model.php";
require_once dirname(__FILE__) . "/af-dbi.php";

# .section. API Interface

global $ZLW_af_XMLNS; 

function zlw_af_get_xmlns() {
    global $ZLW_af_XMLNS; 
    return $ZLW_af_XMLNS; 
}

function zlw_af_set_xmlns($xmlns = '') {
    global $ZLW_af_XMLNS; 
    $ZLW_af_XMLNS = $xmlns; 
}

function zlw_af_value($o) {
    global $ZLW_af_XMLNS; 
    return $o->xml($ZLW_af_XMLNS);
}

function zlw_af_scalar($name, $value, $typestr = 'string', $hold = NULL, 
                       $hidden = NULL, $methods = NULL, $hint = NULL) {
    return zlw_af_value(new zlw_af_scalar(
        $name, $value, $typestr, $hold, $hidden, $methods, $hint)); 
}

function zlw_af_list($name, $items, $typestr = 'string', $hold = NULL, 
                     $hidden = NULL, $methods = NULL, $hint = NULL,
                     $sort = NULL, $sort_order = NULL) {
    return zlw_af_value(new zlw_af_list(
        $name, $items, $typestr, $hold, $hidden, $methods, $hint, $sort, $sort_order)); 
}

function zlw_af_map($name, $entries, $typestr = 'string', $hold = NULL, 
                    $hidden = NULL, $methods = NULL, $hint = NULL,
                    $sort = NULL, $sort_order = NULL) {
    return zlw_af_value(new zlw_af_map(
        $name, $entries, $typestr, $hold, $hidden, $methods, $hint, $sort, $sort_order)); 
}

function zlw_af_table($name, $rows = NULL, $columns = NULL, 
                      $typestr = 'string', $hold = NULL, $hidden = NULL, 
                      $methods = NULL, $hint = NULL) {
    return zlw_af_value(new zlw_af_table(
        $name, $rows, $columns, $typestr, $hold, $hidden, $methods, $hint)); 
}

function zlw_af_user($name, $user, $typestr = NULL, $hold = NULL,
                     $hidden = NULL, $methods = NULL, $hint = NULL) {
    return zlw_af_value(new zlw_af_user(
        $name, $user, $typestr, $hold, $hidden, $methods, $hint)); 
}

function zlw_af_input($name, $typestr = 'string', $value = NULL, 
                      $multiline = false, $read_only = false,
                      $max_length = NULL, $constraints = NULL,
                      &$selection) {
    return zlw_af_value(new zlw_af_input(
        $name, $typestr, $value, $multiline, $read_only, $max_length, 
        $constraints, $selection));
}

function zlw_af_method($name, $typestr = 'default', $hint = '', $param = NULL,
                       $const = false) {
    return zlw_af_value(new zlw_af_method(
        $name, $typestr, $hint, $param, $const)); 
}

function zlw_af_form($name, $inputs = NULL, $typestr = 'default',
                     $methods = NULL, $hint = NULL, $form_method = NULL) {
    return zlw_af_value(new zlw_af_form(
        $name, $inputs, $typestr, $methods, $hint, $form_method)); 
}

function zlw_af_section($name, $hint = NULL, $hidden = NULL, $data = NULL,
                        $forms = NULL) {
    return zlw_af_value(new zlw_af_section(
        $name, $hint, $hidden, $data, $forms)); 
}

function zlw_af_xml($title = 'Abstract Form', $sections = NULL,
                    $page_params = NULL) {
    return zlw_af_value(new zlw_af_xml(
        $title, $sections, $page_params)); 
}

function zlw_af_query_table($dbi, $sql, $keys = 'id', $name = NULL,
                            $methods = 'delete:modify', $hint = NULL,
                            $hidden = NULL, $page = 0, $page_size = -1) {
    $table = new zlw_af_table($name, NULL, NULL, NULL, NULL, $hidden,
                              $methods, $hint);
    $dbi->_query_view($sql, $table, $keys, NULL, $page, $page_size);
    return zlw_af_value($table);
}

function zlw_af_query_map($dbi, $sql, $keys = 'id', $format = NULL, $name = NULL,
                          $methods = 'delete:modify', $hint = NULL,
                          $hidden = NULL, $page = 0, $page_size = -1) {
    $map = new zlw_af_map($name, NULL, NULL, NULL, $hidden, $methods, $hint);
    $dbi->_query_view($sql, $map, $keys, $format, $page, $page_size);
    return zlw_af_value($map);
}

function zlw_af_query_list($dbi, $sql, $format = NULL, $name = NULL, 
                           $methods = 'delete:modify', $hint = '',
                           $hidden = NULL, $page = 0, $page_size = -1) {
    $list = new zlw_af_list($name, NULL, NULL, NULL, $hidden, $methods, $hint);
    $dbi->_query_view($sql, $list, NULL, $format, $page, $page_size);
    return zlw_af_value($list);
}

function zlw_af_query_edit($dbi, $sql, $keys = NULL, $init = NULL,
                           $name = NULL, $update_method = 'update',
                           $hint = NULL, $selection = NULL) {
    $methods = $update_method;
    if (is_null($methods)) $methods = 'update'; 
    
    $form = new zlw_af_form($name, NULL, NULL, $methods, $hint);
    $dbi->_query_edit($sql, $form, $keys, $init, $selection);
    return zlw_af_value($form);
}

?>