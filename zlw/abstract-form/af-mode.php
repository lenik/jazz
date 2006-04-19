<?php

require_once dirname(__FILE__) . '/../common-php/error.php'; 
require_once dirname(__FILE__) . '/af-object.php'; 

# .section.  error manager

function zlw_af_errordoc($error, $ns = '') {
    $doc = new zlw_af_document('Runtime Error', null, new zlw_af_section(
                               'error', null, null, $error)); 
    return $doc->xml($ns); 
}

class zlw_af_mode_em extends phpx_error_manager {
    private $ns; 
    
    function zlw_af_mode_em($provider = null, $ns = '') {
        $this->phpx_error_manager($provider); 
        $this->pref = PHPX_EM_TERM; 
        $this->ns = $ns; 
    }
    
    function handler(&$error) {
        $err = $error; 
        if ($err instanceof zlw_af_error)
            ; 
        elseif ($err instanceof phpx_error) {
            $cast = new zlw_af_error(null); 
            foreach ($err as $k=>$v)
                $cast->$k = $v; 
            $err = $cast; 
        }
        else                            # maybe string-summary or something else.
            $err = new zlw_af_error($err); 
        
        global $ZLW_AF_MODE; 
        if (isset($ZLW_AF_MODE)) {
            return $ZLW_AF_MODE->error_handler($err, $this->ns); 
        } else {
            # default mode, print die message
            die(zlw_af_errordoc($err, $this->ns)); 
        }
    }
}

function zlw_af_enable_xml_error($ns = '') {
    $em = new zlw_af_mode_em('ZLW_AF_MODE', $ns); 
    return $em->register(); 
}

function zlw_af_disable_xml_error() {
    return phpx_error_manager_unregister('ZLW_AF_MODE'); 
}

# .section.  AF programming mode

class zlw_af_mode_base {
    protected $document; 
    protected $section; 
    protected $form; 
    
    function process($obj) {
        return $obj; 
    }
    
    function error_handler(&$error, $ns = '') {
        die(zlw_af_errordoc($error, $ns)); 
    }
    
    function endup() {
        if (isset($this->form))
            $this->form_end();
        if (isset($this->section))
            $this->section_end();
        if (isset($this->document))
            $this->document_end(); 
    }
    
    function scalar($name, $value, $typestr = 'string', $hold = null, 
                    $hidden = null, $methods = null, $hint = null) {
        $scalar = new zlw_af_scalar($name, $value, $typestr, $hold, $hidden,
                                    $methods, $hint); 
        return $this->process($scalar); 
    }
    
    function list_($name, $items, $typestr = 'string', $hold = null, 
                   $hidden = null, $methods = null, $hint = null,
                   $sort = null, $sort_order = null) {
        $list = new zlw_af_list($name, $items, $typestr, $hold, $hidden,
                                $methods, $hint, $sort, $sort_order); 
        return $this->process($list); 
    }
    
    function map($name, $entries, $typestr = 'string', $hold = null, 
                 $hidden = null, $methods = null, $hint = null,
                 $sort = null, $sort_order = null) {
        $map = new zlw_af_map($name, $entries, $typestr, $hold, $hidden,
                              $methods, $hint, $sort, $sort_order); 
        return $this->process($map); 
    }
    
    function table($name, $rows = null, $columns = null, 
                   $typestr = 'string', $hold = null, $hidden = null, 
                   $methods = null, $hint = null) {
        $table = new zlw_af_table($name, $rows, $columns, $typestr, $hold,
                                  $hidden, $methods, $hint); 
        return $this->process($table); 
    }
    
    function user($name, $user, $typestr = null, $hold = null,
                  $hidden = null, $methods = null, $hint = null) {
        $user = new zlw_af_user($name, $user, $typestr, $hold, $hidden,
                                $methods, $hint); 
        return $this->process($user);
    }
    
    function input($name, $typestr = 'string', $value = null, 
                   $multiline = false, $read_only = false,
                   $max_length = null, $constraints = null,
                   $selection) {
        $input = new zlw_af_input($name, $typestr, $value, $multiline,
                                  $read_only, $max_length, $constraints,
                                  $selection);
        return $this->process($input); 
    }
    
    function method($name, $hint = null, $typestr = 'default', 
                    $param = null, $const = false) {
        return $this->process(new zlw_af_method(
            $name, $hint, $typestr, $param, $const)); 
    }
    
    function form($name, $items = null, $typestr = 'default',
                  $methods = null, $hint = null, $form_method = null) {
        $form = new zlw_af_form($name, $items, $typestr, $methods, $hint,
                                $form_method); 
        return $this->process($form); 
    }
    
    function form_start($name, $typestr = 'default', 
                        $methods = null, $hint = null, $form_method = null) {
        if (isset($this->form))
            $this->form_end(); 
        $this->form = new zlw_af_form($name, null, $typestr, $methods, $hint,
                                      $form_method); 
        return null; 
    }
    
    function form_end() {
        if (! isset($this->form))
            die("not in a form context"); 
        $form = $this->form; 
        unset($this->form); 
        return $form; 
    }
    
    function section($name = null, $items = null, $hint = null, $hidden = null) {
        $section = new zlw_af_section($name, $items, $hint, $hidden); 
        return $this->process($section);
    }
    
    function section_start($name = null, $hint = null, $hidden = null) {
        if (isset($this->section))
            $this->session_end(); 
        $this->section = new zlw_af_section($name, null, $hint, $hidden); 
        return null; 
    }
    
    function section_end() {
        if (! isset($this->section))
            die("not in a section context"); 
        if (isset($this->form))
            $this->form_end();
        $section = $this->section;
        unset($this->section); 
        return $section; 
    }
    
    function document($title = 'Abstract Form', $params = null,
                      $sections = null, $af_base = null, $xsl = null,
                      $encoding = null, $version = null) {
        $document = new zlw_af_document($title, $params, $sections, $af_base,
                                        $xsl, $encoding, $version); 
        return $this->process($document); 
    }
    
    function document_start($title = 'Abstract Form', $params = null,
                            $sections = null, $af_base = null, $xsl = null,
                            $encoding = null, $version = null) {
        if (isset($this->document))
            $this->document_end(); 
        
        $this->document = new zlw_af_document($title, $sections, $params,
                                              $af_base, $xsl,
                                              $encoding, $version); 
        return null;
    }
    
    function document_end() {
        if (! isset($this->document))
            die("not in a document context"); 
        if (isset($this->section))
            $this->section_end();
        $document = $this->document;
        unset($this->document); 
        return $document; 
    }
}

# .section.  globals

global $ZLW_AF_MODE;

function zlw_af_endup() {
    global $ZLW_AF_MODE;
    if (isset($ZLW_AF_MODE))
        $ZLW_AF_MODE->endup();
}

register_shutdown_function('zlw_af_endup');

function zlw_af_scalar($name, $value, $typestr = 'string', $hold = null, 
                       $hidden = null, $methods = null, $hint = null) {
    global $ZLW_AF_MODE;
    return $ZLW_AF_MODE->scalar($name, $value, $typestr, $hold, $hidden, $methods,
                                $hint); 
}

function zlw_af_list($name, $items, $typestr = 'string', $hold = null, 
                     $hidden = null, $methods = null, $hint = null,
                     $sort = null, $sort_order = null) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->list_($name, $items, $typestr, $hold, $hidden, $methods,
                               $hint, $sort, $sort_order); 
}

function zlw_af_map($name, $entries, $typestr = 'string', $hold = null, 
                    $hidden = null, $methods = null, $hint = null,
                    $sort = null, $sort_order = null) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->map($name, $entries, $typestr, $hold, $hidden, $methods,
                             $hint, $sort, $sort_order); 
}

function zlw_af_table($name, $rows = null, $columns = null, 
                      $typestr = 'string', $hold = null, $hidden = null, 
                      $methods = null, $hint = null) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->table($name, $rows, $columns, $typestr, $hold, $hidden,
                               $methods, $hint); 
}

function zlw_af_user($name, $user, $typestr = null, $hold = null,
                     $hidden = null, $methods = null, $hint = null) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->user($name, $user, $typestr, $hold, $hidden, $methods,
                              $hint); 
}

function zlw_af_input($name, $typestr = 'string', $value = null, 
                      $multiline = false, $read_only = false,
                      $max_length = null, $constraints = null,
                      $selection) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->input($name, $typestr, $value, $multiline, $read_only,
                               $max_length, $constraints, $selection);
}

function zlw_af_method($name, $hint = null, $typestr = 'default', 
                       $param = null, $const = false) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->method($name, $hint, $typestr, $param, $const); 
}

function zlw_af_form($name, $items = null, $typestr = 'default',
                     $methods = null, $hint = null, $form_method = null) {
    global $ZLW_AF_MODE; 
    if (is_null($items) && is_null($methods))
        return $ZLW_AF_MODE->form_start($name, $typestr, $methods, $hint, 
                                        $form_method); 
    else
        return $ZLW_AF_MODE->form($name, $items, $typestr, $methods, $hint,
                                  $form_method); 
}

function zlw_af_form_start($name, $typestr = 'default', 
                           $methods = null, $hint = null, $form_method = null) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->form_start($name, $typestr, $methods, $hint,
                                    $form_method);
}

function zlw_af_form_end() {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->form_end();
}

function zlw_af_section($name = null, $items = null, $hint = null, 
                        $hidden = null) {
    global $ZLW_AF_MODE; 
    if (is_null($items))
        return $ZLW_AF_MODE->section_start($name, $hint, $hidden); 
    else
        return $ZLW_AF_MODE->section($name, $items, $hint, $hidden); 
}

function zlw_af_section_start($name = null, $hint = null, $hidden = null) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->section_start($name, $hint, $hidden); 
}

function zlw_af_section_end() {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->section_end(); 
}

function zlw_af_document($title = 'Abstract Form', $params = null,
                         $sections = null, $af_base = null, $xsl = null,
                         $encoding = null, $version = null) {
    global $ZLW_AF_MODE; 
    if (is_null($sections))
        return $ZLW_AF_MODE->document_start($title, $params, $sections, $af_base,
                                            $xsl, $encoding, $version);
    else
        return $ZLW_AF_MODE->document($title, $params, $sections, $af_base,
                                      $xsl, $encoding, $version);
}

function zlw_af_start($title = 'Abstract Form', $params = null,
                      $sections = null, $af_base = null, $xsl = null,
                      $encoding = null, $version = null) {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->document_start($title, $params, $sections, $af_base,
                                        $xsl, $encoding, $version); 
}

function zlw_af_end() {
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->document_end();
}

# .section.  mode ext functions

function zlw_af_query_table($dbi, $sqlrc, $keys = 'id', $name = null,
                            $methods = 'delete:modify', $hint = null,
                            $hidden = null) {
    $table = new zlw_af_table($name, null, null, null, null, $hidden,
                              $methods, $hint);
    $dbi->_query_view($sqlrc, $table, $keys, null);
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->process($table);
}

function zlw_af_query_map($dbi, $sqlrc, $keys = 'id', $format = null, $name = null,
                          $methods = 'delete:modify', $hint = null,
                          $hidden = null) {
    $map = new zlw_af_map($name, null, null, null, $hidden, $methods, $hint);
    $dbi->_query_view($sqlrc, $map, $keys, $format);
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->process($map);
}

function zlw_af_query_list($dbi, $sqlrc, $format = null, $name = null, 
                           $methods = 'delete:modify', $hint = '',
                           $hidden = null) {
    $list = new zlw_af_list($name, null, null, null, $hidden, $methods, $hint);
    $dbi->_query_view($sqlrc, $list, null, $format);
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->process($list);
}

function zlw_af_query_edit($dbi, $sqlrc, $keys = null, $init = null,
                           $name = null, $update_method = 'update',
                           $hint = null, $selection = null) {
    $methods = $update_method;
    if (is_null($methods)) $methods = 'update'; 
    
    $form = new zlw_af_form($name, null, null, $methods, $hint);
    $dbi->_query_edit($sqlrc, $form, $keys, $init, $selection);
    global $ZLW_AF_MODE; 
    return $ZLW_AF_MODE->process($form);
}

?>