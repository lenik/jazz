<?php

require_once dirname(__FILE__) . "/../common-php/xml.php"; 
require_once dirname(__FILE__) . "/af-base.php"; 
require_once dirname(__FILE__) . "/af-type.php"; 
require_once dirname(__FILE__) . "/af-constraint.php"; 

# .section.  xml document

class zlw_af_document extends phpx_xml {
    public $sections; 
    public $page;                       # page section
    
    function zlw_af_document($title = 'Abstract Form', $params = null, $sections = null,
                             $af_base = null, $xsl = null, $encoding = null, $version = null) {
        if (is_null($af_base)) {
            global $ZLW_AF_HOME; 
            $af_base = $ZLW_AF_HOME; 
        }
        if (is_null($xsl))
            $xsl = $af_base . '/html-view.xsl'; 
        
        parent::phpx_xml(null, $xsl, $encoding, $version); 
        
        if (is_object($sections))
            $sections = array($sections); 
        $this->sections = $sections; 
        $this->page = &$this->sections['.page']; 
        if (is_null($this->page)) {
            $this->page = new zlw_af_section('.page'); 
        }

        $this->set_param('title', $title);
        $this->set_param('af-base', $af_base);
        phpx_or($this->page->datas, $params); 
    }
    
    function add($section) {
        $sect = &$this->sections[$section->name]; 
        if (isset($sect))
            die("Section $section->name has already been added"); 
        $sect = $section; 
    }
    
    function get_param($name) {
        return $this->page->items[$name]; 
    }
    
    function set_param($name, $value) {
        $this->page->items[$name] = $value; 
    }
    
    function xml_start($ns = '') {
        $xml = parent::xml_start($ns); 
        # dtd? ignored
        
        $af_base = $this->get_param('af-base'); 
        $attrs = array(
            'xmlns:xsi' => 'http://www.w3.org/2001/XMLSchema-instance', 
            'xsi:schemaLocation' => "http://www.bodz.net/xml/zlw/abstract-form $af_base/abstract-form.xsd", 
        ); 
        
        if (strpos($ns, '=') === false) { # only prefix ?
            global $ZLW_AF_NSURI; 
            $attrs['xmlns' . ($ns ? ":$ns" : '')] = $ZLW_AF_NSURI; 
        }
        
        $xml .= phpx_xml_start_tag('abstract-form' . phpx_xml_attrs($attrs), $ns); 
        return $xml; 
    }
    
    function xml_end($ns = '') {
        $xml = phpx_xml_end_tag('abstract-form', $ns); 
        return $xml; 
    }
    
    function xml_sections($ns = '') {
        $xml = ''; 
        if ($this->sections)
            foreach ($this->sections as $section)
                $xml .= phpx_xml_value($section, $ns); 
        return $xml; 
    }
    
    function xml_page($ns = '') {
        return phpx_xml_value($this->page, $ns); 
    }
    
    function xml($ns = '') {
        return $this->xml_start($ns)
            . $this->xml_sections($ns)  # includes page section
            . $this->xml_end($ns); 
    }
}

class zlw_af_section {
    public $name; 
    public $hint; 
    public $hidden; 
    public $items; 
    
    function zlw_af_section($name = null, $items = null, $hint = null, 
                            $hidden = null) {
        $this->name = $name;
        $this->hint = zlw_af_hint_prep($hint);
        $this->hidden = $hidden;
        if (is_object($items))
            $items = array($items); 
        $this->items = $items; 
    }
    
    function add($item) {
        $this->items[] = $item;
    }
    
    function xml_start($ns = '') {
        return phpx_xml_start_tag('section' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'hint' => $this->hint, 
            'hidden' => $this->hidden, 
            )), $ns); 
    }
    
    function xml_end($ns = '') {
        return phpx_xml_end_tag('section', $ns); 
    }
    
    function xml_items($ns = '') {
        $xml = ''; 
        if ($this->items)
            foreach ($this->items as $name=>$item) {
                if (method_exists($item, 'xml')) {
                    # maybe input, form, error, doc, etc.
                    $xml .= phpx_xml_value($item, $ns); 
                } elseif (is_array($item)) {
                    if (phpx_is_array($item, true)) {
                        # array[0..N-1]
                        $list = new zlw_af_list($name, $item); 
                        $xml .= $list->xml($ns); 
                    } else {
                        # map
                        $map = new zlw_af_map($name, $item); 
                        $xml .= $map->xml($ns); 
                    }
                } elseif (is_scalar($item)) {
                    $scalar = new zlw_af_scalar($name, $item); 
                    $xml .= $scalar->xml($ns); 
                }
            }
        return $xml; 
    }
    
    function xml($ns = '') {
        return $this->xml_start($ns)
            . $this->xml_items($ns)
            . $this->xml_end($ns); 
    }
}

function zlw_af_methods($methods, $ns = '') {
    $xml = ''; 
    if (is_null($methods)) return null;
    if (is_string($methods)) {
        $names = explode(':', $methods);
        foreach ($names as $name) {
            $method = new zlw_af_method($name);
            $xml .= $method->xml($ns);
        }
    } elseif (is_array($methods)) {
        foreach ($methods as $method)
            $xml .= $method->xml($ns);
    } elseif (is_a($methods, 'zlw_af_method')) {
        $xml = $methods->xml($ns); 
    } else {
        die("Invalid methods $methods"); 
    }
    return $xml; 
}

# .section.  output

class zlw_af_data extends phpx_error_support {
    public $name; 
    public $type;                       # typep
    public $hold; 
    public $hidden;
    public $hint; 
    public $methods; 
    public $dumped; 
    
    function zlw_af_data($name = null, $typestr = 'string', $hold = null,
                         $hidden = null, $methods = null, $hint = null) {
        $this->phpx_error_support('ZLW_AF'); 
        $this->name = $name;
        $this->type = &zlw_af_type($typestr); 
        $this->hold = $hold;
        $this->hidden = $hidden;
        $this->hint = zlw_af_hint_prep($hint); 
        $this->methods = $methods;
    }
    
    function xml_methods($ns = '') {
        return zlw_af_methods($this->methods, $ns); 
    }
    
    function xml($ns = '') {
        $ns; 
        $this->_err("[DOUT] Not implemented. "); 
        return null; 
    }
}

class zlw_af_scalar extends zlw_af_data {
    public $value;                      # value
    
    function zlw_af_scalar($name, $value, $typestr = 'string', $hold = null, 
                           $hidden = null, $methods = null, $hint = null) {
        $this->zlw_af_data($name, $typestr, $hold, $hidden, $methods, $hint); 
        $this->value = $value; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('scalar' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type->name, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden,
            'hint' => $this->hint, 
            )), $ns, false, false);
        $xml .= $this->type->xml($ns); 
        $xml .= $this->xml_methods($ns); 
        $xml .= phpx_xml_text($this->value, false, false);
        $xml .= phpx_xml_end_tag('scalar', $ns, true, false);
        $this->dumped = true; 
        return $xml; 
    }
}

class zlw_af_list extends zlw_af_data {
    public $items; 
    public $sort; 
    public $sort_order; 
    
    function zlw_af_list($name, $items = null, $typestr = 'string',
                         $hold = null, $hidden = null, $methods = null,
                         $hint = null, $sort = null, $sort_order = null) {
        $this->zlw_af_data($name, $typestr, $hold, $hidden, $methods, $hint); 
        if (is_array($items))
            $this->items = $items; 
        elseif (is_string($items))
            $this->items = explode(':', $items); 
        $this->sort = $sort; 
        $this->sort_order = $sort_order; 
    }

    function add($item) {
        $this->items[] = $item;
    }
    
    function xml_items($ns = '') {
        if (is_null($this->items))
            return null; 
        foreach ($this->items as $item) {
            $xml .= phpx_xml_start_tag('item', $ns); 
            $xml .= phpx_xml_value($item); 
            $xml .= phpx_xml_end_tag('item', $ns); 
        }
        return $xml; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('list' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type->name, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden, 
            'hint' => $this->hint, 
            'sort' => $this->sort, 
            'sort-order' => $this->sort_order, 
            )), $ns);
        $xml .= $this->type->xml($ns); 
        $xml .= $this->xml_methods($ns); 
        $xml .= $this->xml_items($ns); 
        $xml .= phpx_xml_end_tag('list', $ns); 
        $this->dumped = true; 
        return $xml; 
    }
}

class zlw_af_map extends zlw_af_data {
    public $entries; 
    public $sort; 
    public $sort_order; 
    
    function zlw_af_map($name, $entries = null, $typestr = 'string',
                        $hold = null, $hidden = null, $methods = null,
                        $hint = null, $sort = null, $sort_order = null) {
        $this->zlw_af_data($name, $typestr, $hold, $hidden, $methods, $hint); 
        if (is_array($entries) || is_object($entries))
            $this->entries = $entries; 
        elseif (is_string($entries))
            $this->entries = phpx_map_parse($entries); 
        $this->sort = $sort; 
        $this->sort_order = $sort_order; 
    }
    
    function add($key, $value) {
        $this->entries[$key] = $value;
    }
    
    function xml_entries($ns = '') {
        if ($this->entries)
            foreach ($this->entries as $key=>$value) {
                $xml .= phpx_xml_start_tag('entry key=' . phpx_xml_attr($key), $ns); 
                $xml .= phpx_xml_value($value);
                $xml .= phpx_xml_end_tag('entry', $ns); 
            }
        return $xml; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('map' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type->name, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden, 
            'hint' => $this->hint, 
            'sort' => $this->sort, 
            'sort-order' => $this->sort_order, 
            )), $ns);
        $xml .= $this->type->xml($ns); 
        $xml .= $this->xml_methods($ns);
        $xml .= $this->xml_entries($ns); 
        $xml .= phpx_xml_end_tag('map', $ns); 
        $this->dumped = true; 
        return $xml; 
    }
}

class zlw_af_column {
    public $name; 
    public $type; 
    public $primary_key; 
    public $sort_priority; 
    public $sort_order; 
}

class zlw_af_table extends zlw_af_data {
    public $columns; 
    public $rows; 
    
    function zlw_af_table($name, $rows = null, $columns = null,
                          $typestr = 'string', $hold = null, $hidden = null,
                          $methods = null, $hint = null) {
        $this->zlw_af_data($name, $typestr, $hold, $hidden, $methods, $hint);
        $this->rows = $rows; 
        if (! is_null($columns))
            $this->columns = $columns;
        elseif (sizeof($rows) > 0)
            foreach (array_keys($rows[0]) as $name)
                $this->add_column($name);
    }
    
    function add_column($name, $typestr = null, $primary_key = null,
                        $sort_priority = null, $sort_order = null) {
        $column = new zlw_af_column; 
        $column->name = $name;
        if (is_null($typestr))
            $column->type = &$this->type;
        else
            $column->type = &zlw_af_type($typestr);
        $column->primary_key = $primary_key;
        $column->sort_priority = $sort_priority;
        $column->sort_order = $sort_order;
        $this->columns[] = $column; 
    }
    
    function add($row) {
        $this->rows[] = $row;
    }
    
    function xml_columns($ns = '') {
        $xml = ''; 
        assert($this->columns); 
        foreach ($this->columns as $column) {
            $xml .= phpx_xml_tag('column', array(
                'name' => $column->name, 
                'type' => $column->type->name, 
                'primary-key' => $column->primary_key, 
                'sort-priority' => $column->sort_priority, 
                'sort-order' => $column->sort_order, 
                ), $column->type, $ns);
        }
        return $xml; 
    }
    
    function xml_rows($ns = '', $ns2 = null) {
        $xml = ''; 
        if ($this->rows == null) return null; 
        if (is_null($ns2)) $ns2 = $ns; 
        foreach ($this->rows as $row) {
            $xml .= phpx_xml_start_tag('row', $ns); 
            if (is_array($row))
                foreach ($this->columns as $column) {
                    $name = $column->name; 
                    $xml .= phpx_xml_tag($name, null, $row[$name], $ns2); 
                }
            elseif (is_object($row))
                foreach ($this->columns as $column) {
                    $name = $column->name; 
                    $xml .= phpx_xml_tag($name, null, $row->$name, $ns2); 
                }
            $xml .= phpx_xml_end_tag('row', $ns); 
        }
        return $xml; 
    }
    
    function xml($ns = '', $rns = '') {
        $xml = phpx_xml_start_tag('table' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type->name, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden, 
            'hint' => $this->hint, 
            )), $ns);
        $xml .= $this->type->xml($ns); 
        $xml .= $this->xml_methods($ns); 
        $xml .= $this->xml_columns($ns); 
        $xml .= $this->xml_rows($ns, $rns); 
        $xml .= phpx_xml_end_tag('table', $ns); 
        $this->dumped = true; 
        return $xml; 
    }
}

class zlw_af_user extends zlw_af_data {
    public $user; 
    
    function zlw_af_user($name, $user, $typestr = null, $hold = null,
                         $hidden = null, $methods = null, $hint = null) {
        $this->zlw_af_data($name, $typestr, $hold, $hidden, $methods, $hint);
        $this->user = $user; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('user' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type->name, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden, 
            'hint' => $this->hint, 
            )), $ns);
        $xml .= $this->type->xml($ns); 
        $xml .= $this->xml_methods($ns);
        $xml .= phpx_xml_value($this->user, $ns); 
        $xml .= phpx_xml_end_tag('user', $ns); 
        $this->dumped = true; 
        return $xml; 
    }
}

# .section.  input

class zlw_af_input extends phpx_error_support {
    public $name; 
    public $var; 
    public $multiline; 
    public $read_only; 
    public $max_length; 
    public $constraints; 
    public $selection; 
    
    function zlw_af_input($name, $typestr = 'string', $value = null, 
                          $multiline = false, $read_only = false,
                          $max_length = null, $constraints = null, 
                          $selection = null) {
        $this->phpx_error_support('ZLW_AF');
        $this->name = $name;
        if ($value instanceof zlw_af_variant)
            $this->var = $value;        # ignore $typestr
        else
            $this->var = new zlw_af_variant($value, $typestr);
        $this->multiline = $multiline;
        $this->read_only = $read_only;
        $this->max_length = $max_length;
        $this->constraints = $constraints;
        $this->selection = $selection; 
    }
    
    function parse($string) {
        return $this->var->parse($string); 
    }
    
    function is_init() {
        return $this->var->is_init();
    }
    
    function uninit() {
        return $this->var->uninit();
    }
    
    function check() {
        if ($this->constraints)
            if (! $this->constraints->check($this->var)) {
                return false;           # ERR.
            }
        return true; 
    }
    
    function xml_selection($ns = '') {
        if (is_null($this->selection))
            return null;
        if (is_string($this->selection)) {
            list($type, $name) = explode(':', $this->selection, 2);
            return phpx_xml_tag($type . '-ref', null, $name, $ns);
        }
        if ($this->selection->dumped) {
            if ($this->selection instanceof zlw_af_list)
                $type = 'list';
            elseif ($this->selection instanceof zlw_af_map)
                $type = 'map';
            elseif ($this->selection instanceof zlw_af_table)
                $type = 'table';
            else
                die("Invalid selection type: " . get_class($this->selection));
            $xml = phpx_xml_tag($type . '-ref', null, $this->selection->name, $ns);
        } else {
            $xml = $this->selection->xml($ns);
        }
        return $xml; 
    }
    
    function xml_constraints($ns = '') {
        if (is_null($this->constraints))
            return null; 
        return $this->constraints->xml($ns); 
    }
    
    function xml($ns = '') {
        $init = null; 
        if (! is_null($this->var->value))
            $init = $this->var->format(); 
        $attrs = array(
            'name' => $this->name, 
            'type' => $this->var->type->name, 
            'init' => $init, 
            'multiline' => $this->multiline, 
            'read-only' => $this->read_only, 
            'max-length' => $this->max_length, 
            ); 
        $xml = $this->var->type->xml($ns); 
        $xml .= $this->xml_selection($ns); 
        $xml .= $this->xml_constraints($ns); 
        if ($xml == null)
            return phpx_xml_tag('input', $attrs, null, $ns); 
        return phpx_xml_start_tag('input' . phpx_xml_attrs($attrs), $ns)
            . $xml
            . phpx_xml_end_tag('input', $ns); 
    }
}

class zlw_af_method {
    public $name; 
    public $type;                       # Reserved.
    public $hint;
    public $param; 
    public $const; 
    
    function zlw_af_method($name, $hint = '', $typestr = null, 
                           $param = null, $const = false) {
        $this->name = $name; 
        $this->type = &zlw_af_type($typestr); 
        $this->hint = zlw_af_hint_prep($hint);
        if (is_array($param))
            $this->param = $param;
        else
            $this->param = phpx_map_parse($param); 
        $this->const = $const;
    }
    
    function xml($ns = '') {
        $attrs = array(
            'name' => $this->name, 
            'type' => $this->type->name, 
            'hint' => $this->hint, 
            'const' => $this->const, 
            ); 
        if ($this->param) {
            $xml = phpx_xml_start_tag('method' . phpx_xml_attrs($attrs), $ns); 
            $xml .= $this->type->xml($ns); 
            foreach ($this->param as $name=>$value) {
                if (is_null($value)) continue;
                $xml .= phpx_xml_tag('method-parameter', array('name' => $name), $value, $ns); 
            }
            $xml .= phpx_xml_end_tag('method', $ns); 
        } else {
            $xml = phpx_xml_tag('method', $attrs, $this->type, $ns); 
        }
        return $xml; 
    }
}

# .section.  form

global $ZLW_AF_TICKETS; 

class zlw_af_ticket extends phpx_error_support {
    private $tid; 
    private $timestamp; 
    private $src; 
    private $next; 
    private $trace_prev; 
    private $trace_next; 
    
    function zlw_af_ticket($tid) {
        $this->tid = $tid; 
        $this->timestamp = time(); 
        $this->src = $_SERVER['referer'];
    }
    
    function validate() {
        if (! is_null($this->trace_prev)) {
            $prev = &$this->trace(); 
            if (is_null($prev) || $prev->trace_next != $this->tid)
                return $this->_err("[TIKT] Bad ticket: Failed to trace the ticket");
        }
        if (! is_null($this->next))
            return $this->_err("[TIKT] Bad ticket: ticket has been already used");
        return true; 
    }

    function &trace() {
        if (is_null($this->trace_prev)) return null; 
        return zlw_af_ticket_get($this->trace_prev);
    }
    
    function next($next, $traced = false) {
        if (! $this->validate()) return false; 
        $this->next = $next;
        if ($traced) {
            $prealloc = &zlw_af_ticket_alloc(); 
            $this->trace_next = $prealloc->tid; 
            $prealloc->trace_prev = $this->tid; 
        }
        return true; 
    }
}

function &zlw_af_ticket_alloc() {
    global $ZLW_AF_TICKETS;
    $id = rand(); 
    do {
        $tid = ZLW_AF . '.' . $id++; 
        $ticket = &$ZLW_AF_TICKETS[$tid];
    } while (! is_null($ticket));
    $ticket = new zlw_af_ticket($tid);
    return $ticket; 
}

function zlw_af_ticket_use($tid, $target) {
    global $ZLW_AF_TICKETS;
    $ticket = &$ZLW_AF_TICKETS[$tid];
    if (is_null($ticket))
        $ticket = new zlw_af_ticket(); 
    return $ticket->use($target); 
}

class zlw_af_form {
    public $name; 
    public $type; 
    public $hint; 
    public $form_method; 
    public $items;                      # name => input, method, af:doc, af:error, etc.
    public $methods;                    # additional methods at bottom
    public $ticket; 
    
    function zlw_af_form($name = null, $items = null, $typestr = 'default',
                         $methods = null, $hint = null,
                         $form_method = null) {
        $this->name = $name;
        $this->type = &zlw_af_type($typestr);
        $this->hint = zlw_af_hint_prep($hint);
        $this->form_method = $form_method;
        if (is_object($items))
            $items = array($items); 
        $this->items = $items;
        $this->methods = $methods;
    }
    
    function check() {
        foreach ($this->items as $item) {
            if (is_a($item, 'zlw_af_input')) {
                if (! $input->check())
                    return false;           # ERR.
            }
        }
        return true; 
    }
    
    function add($item) {
        $this->items[] = $item; 
    }
    
    function xml_start($ns = '') {
        return phpx_xml_start_tag('form' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type->name, 
            'hint' => $this->hint, 
            'form-method' => $this->form_method, 
            )), $ns); 
    }
    
    function xml_end($ns = '') {
        return phpx_xml_end_tag('form', $ns); 
    }
    
    function xml_items($ns = '') {
        $xml = ''; 
        if ($this->items)
            foreach ($this->items as $item)
                $xml .= phpx_xml_value($item, $ns); 
        return $xml; 
    }
    
    function xml_methods($ns = '') {
        return zlw_af_methods($this->methods, $ns); 
    }
    
    function xml($ns = '', $outer = true) {
        $xml = ''; 
        if ($outer) $xml .= $this->xml_start($ns); 
        $xml .= $this->xml_items($ns); 
        $xml .= $this->xml_methods($ns); 
        if ($outer) $xml .= $this->xml_end($ns); 
        return $xml; 
    }
}

# .section.  misc

class zlw_af_doc {
    public $doctype;
    public $contents;
    
    function zlw_af_doc($contents, $doctype = 'text/html') {
        $this->contents = $contents; 
        $this->doctype = $doctype; 
    }

    function xml($ns = '') {
        $xml = phpx_xml_tag('doc' . phpx_xml_attrs(array(
            'doctype' => $this->doctype)), $this->contents, $ns);
        return $xml; 
    }
}

class zlw_af_error extends phpx_error {
    public $methods; 
    public $hint; 
    
    function zlw_af_error($summary, $source = null, $cause = null, $methods = null, $hint = '') {
        $this->phpx_error('ZLW_AF', $summary, $source, $cause); 
        $this->methods = $methods; 
        $this->hint = zlw_af_hint_prep($hint); 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('error' . phpx_xml_attrs(array(
            'time' => $this->time, 
            'provider' => $this->provider, 
            'type' => $this->type, 
            'name' => $this->name, 
            'text' => $this->text, 
            'detail' => $this->detail, 
            'hint' => $this->hint, 
            )), $ns); 
        
        if (! is_null($this->source)) {
            if (is_object($this->source))
                $source_name = get_class($this->source); 
            else
                $source_name = "$this->source"; 
            
            if (method_exists($this->source, '_source_status'))
                $status = $this->source->_source_status(); 
            
            $xml .= phpx_xml_tag('error-source', array(
                'name' => $source_name, 
                'status' => $status,
                ), null, $ns); 
        }
        
        if (is_object($this->cause)) {
            if (is_a($this->cause, 'zlw_af_error')) {
                $xml .= $this->cause->xml($ns); 
            } else if (is_a($this->cause, 'phpx_error')) {
                $zlw_wrapper = new zlw_af_error(null); 
                phpx_or($zlw_wrapper, $this->cause); 
                $xml .= $zlw_wrapper->xml($ns); 
            } else {
                die("Invalid error object: $this->cause"); 
            }
        }
        
        if (! is_null($this->methods)) {
            foreach (explode(':', $this->methods) as $method) {
                if ($method == '') continue; 
                $xml .= phpx_xml_tag('method', array('name' => $method), null, $ns); 
            }
        }
        
        $xml .= phpx_xml_end_tag('error', $ns); 
        return $xml; 
    }
}

?>