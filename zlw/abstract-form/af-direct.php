<?php

require_once dirname(__FILE__) . "/af-object.php";
require_once dirname(__FILE__) . "/af-dbi.php";
require_once dirname(__FILE__) . '/af-error.php'; 

# .section.  AF programming mode

global $ZLW_AF_XMLNS; 
global $ZLW_AF_DOCUMENT;                # current document
global $ZLW_AF_SECTION;                 # current section

function zlw_af_get_xmlns() {
    global $ZLW_AF_XMLNS; 
    return $ZLW_AF_XMLNS; 
}

function zlw_af_set_xmlns($xmlns = '') {
    global $ZLW_AF_XMLNS; 
    $ZLW_AF_XMLNS = $xmlns; 
}

function zlw_af_process($o) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->process($o); 
}

function zlw_af_scalar($name, $value, $typestr = 'string', $hold = null, 
                       $hidden = null, $methods = null, $hint = null) {
    echo zlw_af_process(new zlw_af_scalar(
        $name, $value, $typestr, $hold, $hidden, $methods, $hint)); 
}

function zlw_af_list($name, $items, $typestr = 'string', $hold = null, 
                     $hidden = null, $methods = null, $hint = null,
                     $sort = null, $sort_order = null) {
    echo zlw_af_process(new zlw_af_list(
        $name, $items, $typestr, $hold, $hidden, $methods, $hint, $sort, $sort_order)); 
}

function zlw_af_map($name, $entries, $typestr = 'string', $hold = null, 
                    $hidden = null, $methods = null, $hint = null,
                    $sort = null, $sort_order = null) {
    echo zlw_af_process(new zlw_af_map(
        $name, $entries, $typestr, $hold, $hidden, $methods, $hint, $sort, $sort_order)); 
}

function zlw_af_table($name, $rows = null, $columns = null, 
                      $typestr = 'string', $hold = null, $hidden = null, 
                      $methods = null, $hint = null) {
    echo zlw_af_process(new zlw_af_table(
        $name, $rows, $columns, $typestr, $hold, $hidden, $methods, $hint)); 
}

function zlw_af_user($name, $user, $typestr = null, $hold = null,
                     $hidden = null, $methods = null, $hint = null) {
    echo zlw_af_process(new zlw_af_user(
        $name, $user, $typestr, $hold, $hidden, $methods, $hint)); 
}

function zlw_af_input($name, $typestr = 'string', $value = null, 
                      $multiline = false, $read_only = false,
                      $max_length = null, $constraints = null,
                      $selection) {
    echo zlw_af_process(new zlw_af_input(
        $name, $typestr, $value, $multiline, $read_only, $max_length, 
        $constraints, $selection));
}

function zlw_af_method($name, $hint = null, $typestr = 'default', 
                       $param = null, $const = false) {
    echo zlw_af_process(new zlw_af_method(
        $name, $hint, $typestr, $param, $const)); 
}

function zlw_af_form($name, $items = null, $typestr = 'default',
                     $methods = null, $hint = null, $form_method = null) {
    echo zlw_af_process(new zlw_af_form(
        $name, $items, $typestr, $methods, $hint, $form_method)); 
}

function zlw_af_section($name = null, $hint = null, $hidden = null, 
                        $data = null, $forms = null) {
    echo zlw_af_process(new zlw_af_section(
        $name, $hint, $hidden, $data, $forms)); 
}

function zlw_af_start($title = 'Abstract Form', $params = null, $sections = null,
                      $af_base = null, $xsl = null, $encoding = null, $version = null) {
    global $ZLW_AF_XMLNS; 
    global $ZLW_AF_DOCUMENT; 
    if (! is_null($ZLW_AF_DOCUMENT))
        die("last zlw_af_start($ZLW_AF_DOCUMENT->title) without zlw_af_end"); 
    
    $ZLW_AF_DOCUMENT = new zlw_af_document($title, $sections, $params, $af_base,
                                           $xsl, $encoding, $version); 
    phpx_xml_mode();
    echo   $ZLW_AF_DOCUMENT->xml_start($ZLW_AF_XMLNS) 
         . $ZLW_AF_DOCUMENT->xml_page($ZLW_AF_XMLNS); 
}

function zlw_af_end() {
    global $ZLW_AF_XMLNS; 
    global $ZLW_AF_DOCUMENT;
    if (is_null($ZLW_AF_DOCUMENT))
        die("zlw_af_end without zlw_af_start"); 
    echo $ZLW_AF_DOCUMENT->xml_end($ZLW_AF_XMLNS); 
}

function zlw_af_section_start($name = null, $hint = null, $hidden = null) {
    global $ZLW_AF_XMLNS; 
    global $ZLW_AF_SECTION;
    if (! is_null($ZLW_AF_SECTION))
        die("last zlw_af_section_start($ZLW_AF_SECTION->name) without zlw_af_section_end"); 
    
    $ZLW_AF_SECTION = new zlw_af_section($name, $hint, $hidden); 
    echo $ZLW_AF_SECTION->xml_start($ZLW_AF_XMLNS); 
}

function zlw_af_section_end() {
    global $ZLW_AF_XMLNS; 
    global $ZLW_AF_SECTION;
    if (is_null($ZLW_AF_SECTION))
        die("zlw_af_section_end without zlw_af_section_start"); 
    echo $ZLW_AF_SECTION->xml_end($ZLW_AF_XMLNS); 
}

function zlw_af_document($title = 'Abstract Form', $params = null, $sections = null,
                         $af_base = null, $xsl = null, $encoding = null, $version = null) {
    echo zlw_af_process(new zlw_af_document(
        $title, $params, $sections, $af_base, $xsl, $encoding, $version)); 
}

function zlw_af_query_table($dbi, $sqlrc, $keys = 'id', $name = null,
                            $methods = 'delete:modify', $hint = null,
                            $hidden = null) {
    $table = new zlw_af_table($name, null, null, null, null, $hidden,
                              $methods, $hint);
    $dbi->_query_view($sqlrc, $table, $keys, null);
    echo zlw_af_process($table);
}

function zlw_af_query_map($dbi, $sqlrc, $keys = 'id', $format = null, $name = null,
                          $methods = 'delete:modify', $hint = null,
                          $hidden = null) {
    $map = new zlw_af_map($name, null, null, null, $hidden, $methods, $hint);
    $dbi->_query_view($sqlrc, $map, $keys, $format);
    echo zlw_af_process($map);
}

function zlw_af_query_list($dbi, $sqlrc, $format = null, $name = null, 
                           $methods = 'delete:modify', $hint = '',
                           $hidden = null) {
    $list = new zlw_af_list($name, null, null, null, $hidden, $methods, $hint);
    $dbi->_query_view($sqlrc, $list, null, $format);
    echo zlw_af_process($list);
}

function zlw_af_query_edit($dbi, $sqlrc, $keys = null, $init = null,
                           $name = null, $update_method = 'update',
                           $hint = null, $selection = null) {
    $methods = $update_method;
    if (is_null($methods)) $methods = 'update'; 
    
    $form = new zlw_af_form($name, null, null, $methods, $hint);
    $dbi->_query_edit($sqlrc, $form, $keys, $init, $selection);
    echo zlw_af_process($form);
}


class zlw_af_mode_xmlseq {
    function process($element) {
        global $ZLW_AF_XMLNS; 
        return $o->xml($ZLW_AF_XMLNS);
    }
    function error_handler(&$error) {
        global $ZLW_AF_DOCUMENT; 
        if (isnull($ZLW_AF_DOCUMENT)) {
            $doc = new zlw_af_document('Error', null, new zlw_af_section(
                    'first', null, null, $error));
            echo zlw_af_process($doc); 
        } else {
            echo zlw_af_process($error); 
        }
        exit; 
    }
}
global $ZLW_AF_MODE; 

?>