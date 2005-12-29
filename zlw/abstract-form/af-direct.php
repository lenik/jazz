<?php

require_once dirname(__FILE__) . "/af-model.php";

# .section. API Interface

global $ZLW_AFX_XMLNS; 

function zlw_afx_get_xmlns() {
    global $ZLW_AFX_XMLNS; 
    return $ZLW_AFX_XMLNS; 
}

function zlw_afx_set_xmlns($xmlns = '') {
    global $ZLW_AFX_XMLNS; 
    $ZLW_AFX_XMLNS = $xmlns; 
}

function zlw_afx_value($o) {
    global $ZLW_AFX_XMLNS; 
    return $o->xml($ZLW_AFX_XMLNS);
}

function zlw_afx_scalar($name, $value, $typestr = 'string', $hold = false, 
                       $hidden = false, $methods = NULL) {
    return zlw_afx_value(new zlw_af_scalar(
        $name, $value, $typestr, $hold, $hidden, $methods)); 
}

function zlw_afx_list($name, $items, $typestr = 'string', $hold = false, 
                     $hidden = false, $sort = NULL, $sort_order = NULL,
                     $methods = NULL) {
    return zlw_afx_value(new zlw_af_list(
        $name, $items, $typestr, $hold, $hidden, $sort, $sort_order, $methods)); 
}

function zlw_afx_map($name, $entries, $typestr = 'string', $hold = false, 
                    $hidden = false, $sort = NULL, $sort_order = NULL,
                    $methods = NULL) {
    return zlw_afx_value(new zlw_af_map(
        $name, $entries, $typestr, $hold, $hidden, $sort, $sort_order, $methods)); 
}

function zlw_afx_table($name, $rows, $columns = NULL, $typestr = 'string',
                      $hold = false, $hidden = false, $methods = NULL) {
    return zlw_afx_value(new zlw_af_table(
        $name, $rows, $columns, $typestr, $hold, $hidden, $methods)); 
}

function zlw_afx_user($name, $user, $typestr = NULL, $hold = false,
                     $hidden = false, $methods = NULL) {
    return zlw_afx_value(new zlw_af_user(
        $name, $user, $typestr, $hold, $hidden, $methods)); 
}

function zlw_afx_input($name, $typestr = 'string', $value = NULL, 
                      $multiline = false, $read_only = false,
                      $ref = NULL, $max_length = NULL, $constraints = NULL) {
    return zlw_afx_value(new zlw_af_input(
        $name, $typestr, $value, $multiline, $read_only, $ref, $max_length, $constraints));
}

function zlw_afx_method($name, $typestr = 'default', $hint = '', $param = NULL,
                       $const = false) {
    return zlw_afx_value(new zlw_af_method($name, $typestr, $hint, $param, $const)); 
}

function zlw_afx_form() {
    return zlw_afx_value(new zlw_af_form(
        )); 
}

function zlw_afx_section() {
    return zlw_afx_value(new zlw_af_section(
        )); 
}

function zlw_afx_xml($title = 'Abstract Form', $sections = NULL,
                    $page_params = NULL) {
    return zlw_afx_value(new zlw_af_xml($title, $sections, $page_params)); 
}

?>