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
        else
            # maybe string-summary or something else.
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

# .section.  simplifized mode

class zlw_af_mode_base {
    public $document; 
    public $section; 
    public $form; 
    protected $document_id = 0; 
    protected $section_id = 0; 
    protected $form_id = 0; 
    
    function on_error(&$error, $ns = '') {
        die(zlw_af_errordoc($error, $ns)); 
    }
    
    function on_shutdown() {
        if (isset($this->form))
            $this->end_form();
        if (isset($this->section))
            $this->end_section();
        if (isset($this->document))
            $this->end_document(); 
    }
    
    function output($object) {
        $name = get_class($object); 
        if (substr($name, 0, 7) == 'zlw_af_') {
            $mtd = 'add_' . substr($name, 7); 
            $this->$mtd($object); 
        }
    }
    
    function &form() {
        if (! isset($this->form)) {
            $form = new zlw_af_form('form_' . $this->form_id++); 
            $this->output($form); 
        }
        return $this->form; 
    }
    
    function &section() {
        if (! isset($this->section)) {
            $section = new zlw_af_section('section_' . $this->section_id++); 
            $this->output($section); 
        }
        return $this->section; 
    }
    
    function &document() {
        if (! isset($this->document)) {
            $document = new zlw_af_document(); 
            $this->output($document); 
        }
        return $this->document; 
    }
    
    function &context() {
        if (isset($this->form))
            return $this->form; 
        elseif (isset($this->section))
            return $this->section; 
        else
            return $this->document(); 
    }
    
    function add_document($document) {
        if (isset($this->document))
            $this->end_document(); 
        $this->document = $document; 
    }
    
    function end_document() {
        if (! isset($this->document))
            die("not in a document context"); 
        if (isset($this->section))
            $this->end_section();
        $document = $this->document;
        unset($this->document); 
        return $document; 
    }
    
    function add_section($section) {
        $this->document(); 
        if (isset($this->section))
            $this->end_section(); 
        $this->section = $section; 
    }
    
    function end_section() {
        if (! isset($this->section))
            die("not in a section context"); 
        if (isset($this->form))
            $this->end_form();
        $section = $this->section;
        unset($this->section); 
        return $section; 
    }
    
    function add_form($form) {
        $this->section(); 
        if (isset($this->form))
            $this->end_form(); 
        $this->form = $form; 
    }
    
    function end_form() {
        if (! isset($this->form))
            die("not in a form context"); 
        $form = $this->form; 
        unset($this->form); 
        return $form; 
    }
    
    function add_data($data) {
        $this->section()->add($data); 
    }
    
    function add_input($input) {
        $this->form()->add($input); 
    }
    
    function add_scalar($scalar) {
        $this->add_data($scalar); 
    }
    
    function add_list($list) {
        $this->add_data($list); 
    }
    
    function add_map($map) {
        $this->add_data($map); 
    }
    
    function add_table($table) {
        $this->add_data($table); 
    }
    
    function add_user($user) {
        $this->add_data($user); 
    }
    
    function add_method($method) {
        $this->add_input($method); 
    }
    
    function add_doc($doc) {
        $this->context()->add($doc); 
    }
    
    function add_error($error) {
        $this->context()->add($error); 
    }
}

# .section.  globals

global $ZLW_AF_MODE;

function zlw_af_shutdown() {
    global $ZLW_AF_MODE;
    if (isset($ZLW_AF_MODE))
        $ZLW_AF_MODE->on_shutdown();
}

register_shutdown_function('zlw_af_shutdown');

function MODE($mode_name = 'seq', $xmlns = '', $title = null) {
    global $ZLW_AF_MODE; 
    if (isset($ZLW_AF_MODE))
        die('mode is already set to ' . get_class($ZLW_AF_MODE)); 
    $mode_class = 'zlw_af_mode_' . $mode_name; 
    $ZLW_AF_MODE = new $mode_class($xmlns); 
    zlw_af_enable_xml_error($xmlns); 
    if (isset($title))
        DOCUMENT($title); 
}

# .section.  short functions

function DOCUMENT($doc_title = 'Abstract Form', $params = null,
                  $sections = null, $af_base = null, $xsl = null, 
                  $encoding = null, $version = null) {
    global $ZLW_AF_MODE;
    assert(isset($ZLW_AF_MODE)); 
    if ($doc_title instanceof zlw_af_document) {
        $ZLW_AF_MODE->output($doc_title); 
    } elseif (is_string($doc_title)) {
        $document = new zlw_af_document($doc_title, $params, $sections,
                                        $af_base, $xsl, $encoding, $version);
        $ZLW_AF_MODE->output($document); 
    } elseif (is_null($doc_title)) {
        $ZLW_AF_MODE->end_document();
    } else {
        die("invalid document type"); 
    }
}

function SECTION($sect_name = null, $items = null, $hint = null,
                 $hidden = null) {
    global $ZLW_AF_MODE;
    assert(isset($ZLW_AF_MODE)); 
    if ($sect_name instanceof zlw_af_section) {
        $ZLW_AF_MODE->output($sect_name); 
    } elseif (is_string($sect_name)) {
        $section = new zlw_af_section($sect_name, $items, $hint, $hidden);
        $ZLW_AF_MODE->output($section); 
    } elseif (is_null($sect_name)) {
        $ZLW_AF_MODE->end_section();
    } else {
        die("invalid section type"); 
    }
}

function FORM($form_name = null, $items = null, $typestr = 'default',
              $methods = null, $hint = null, $form_method = null) {
    global $ZLW_AF_MODE;
    assert(isset($ZLW_AF_MODE));
    if ($form_name instanceof zlw_af_form) {
        $ZLW_AF_MODE->output($form_name);
    } elseif (is_string($form_name)) {
        $form = new zlw_af_form($form_name, $items, $typestr, $methods, $hint,
                                $form_method);
        $ZLW_AF_MODE->output($form);
    } elseif (is_null($form_name)) {
        $ZLW_AF_MODE->end_form();
    } else {
        die("invalid form type");
    }
}

function OUT($data_name, $value = null, $typestr = null, $hold = null, 
             $hidden = null, $methods = null, $hint = null, 
             $sort = null, $sort_order = null, $columns = null) {
    global $ZLW_AF_MODE;
    assert(isset($ZLW_AF_MODE));
    if (is_string($data_name)) {
        $name = $data_name; 
        if (is_scalar($value)) {
            $o = new zlw_af_scalar($name, $value, $typestr, $hold, $hidden, 
                                   $methods, $hint); 
        } elseif (is_array($value)) {
            if (phpx_is_array($value, true)) {
                $item = $value[0]; 
                if (isset($columns) || is_array($item)) {
                    # table
                    $o = new zlw_af_table($name, $value, $columns, $typestr, 
                                          $hold, $hidden, $methods, $hint); 
                } else {
                    # list
                    $o = new zlw_af_list($name, $value, $typestr, $hold, $hidden, 
                                         $methods, $hint, $sort, $sort_order); 
                }
            } else {
                # map
                $o = new zlw_af_map($name, $value, $typestr, $hold, $hidden, 
                                    $methods, $hint, $sort, $sort_order); 
            }
        } else {
            die('invalid data value type'); 
        }
        $ZLW_AF_MODE->output($o); 
    } elseif ($data_name instanceof zlw_af_data) {
        $ZLW_AF_MODE->output($data_name);
    } elseif ($data_name instanceof zlw_af_form) {
        $ZLW_AF_MODE->output($data_name);
    } elseif ($data_name instanceof zlw_af_doc) {
        $ZLW_AF_MODE->output($data_name);
    } elseif ($data_name instanceof zlw_af_error) {
        $ZLW_AF_MODE->output($data_name);
    } elseif (is_null($data_name)) {
        die('cannot output a null'); 
    } else {
        die('invalid data type'); 
    }
}

function IN($input_name, $value = null, $typestr = null, $multiline = false, 
            $read_only = false, $max_length = null, $constraints = null, 
            $selection = null) {
    global $ZLW_AF_MODE;
    assert(isset($ZLW_AF_MODE));
    if (is_string($input_name)) {
        $input = new zlw_af_input($input_name, $typestr, $value, $multiline, 
                                  $read_only, $max_length, $constraints, 
                                  $selection);
        $ZLW_AF_MODE->output($input); 
    } elseif ($input_name instanceof zlw_af_input) {
        $ZLW_AF_MODE->output($input_name); 
    } else {
        die('invalid input type'); 
    }
}

function METHOD($method_name, $hint = null, $param = null, $typestr = null, 
                $const = false) {
    global $ZLW_AF_MODE;
    assert(isset($ZLW_AF_MODE));
    if (is_string($method_name)) {
        $method = new zlw_af_method($method_name, $hint, $param, $typestr, 
                                    $const);
        $ZLW_AF_MODE->output($method); 
    } elseif ($method_name instanceof zlw_af_method) {
        $ZLW_AF_MODE->output($method_name); 
    } else {
        die('invalid method type');
    }
}

function DOC($contents, $doctype = 'text/html') {
    global $ZLW_AF_MODE;
    assert(isset($ZLW_AF_MODE));
    if (is_string($contents)) {
        $doc = new zlw_af_doc($contents, $doctype);
        $ZLW_AF_MODE->output($doc);
    } elseif ($contents instanceof zlw_af_doc) {
        $ZLW_AF_MODE->output($doc);
    } else {
        die('invalid doc type');
    }
}

function ERROR($error_summary, $source = null, $cause = null,
               $methods = null, $hint = null) {
    global $ZLW_AF_MODE;
    assert(isset($ZLW_AF_MODE));
    if (is_string($error_summary)) {
        $error = new zlw_af_error($error_summary, $source, $cause,
                                  $methods, $hint);
        $ZLW_AF_MODE->output($error);
    } elseif ($error_summary instanceof zlw_af_error) {
        $ZLW_AF_MODE->output($error);
    } else {
        die('invalid error type');
    }
}

?>