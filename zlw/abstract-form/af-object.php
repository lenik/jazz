<?php

require_once dirname(__FILE__) . "/af-type.php"; 
require_once dirname(__FILE__) . "/af-constraint.php"; 

# .section. xml document

class zlw_af_xml {
    var $af_base;
    var $title; 
    var $page_params; 
    var $sections; 
    
    function zlw_af_xml($title = 'Abstract Form', $sections = NULL,
                        $page_params = NULL) {
        global $ZLW_AF_BASE;
        $this->af_base = $ZLW_AF_BASE; 
        $this->title = $title;
        $this->page_params = $page_params; 
        $this->sections = $sections; 
    }
    
    function xml_start($ns = '') {
        return zlw_af_xml_start($ns, $this->af_base); 
    }
    
    function xml_end($ns = '') {
        return zlw_af_xml_end($ns);
    }
    
    function xml_page($ns = '') {
        $this->page_params['af-base'] = $this->af_base;
        return zlw_af_xml_page($ns, $this->title, $this->page_params); 
    }
    
    function xml_sections($ns = '') {
        if ($this->sections)
            foreach ($this->sections as $section)
                $xml .= phpx_xml_value($section, $ns); 
        return $xml; 
    }
    
    function xml($ns = '') {
        return $this->xml_start($ns)
            . $this->xml_page($ns)
            . $this->xml_sections($ns)
            . $this->xml_end($ns); 
    }
}

class zlw_af_section {
    var $name; 
    var $hint; 
    var $hidden; 
    var $data; 
    var $forms; 
    
    function zlw_af_section($name = NULL, $hint = NULL, $hidden = NULL,
                            $data = NULL, $forms = NULL) {
        $this->name = $name;
        $this->hint = zlw_af_hint_prep($hint);
        $this->hidden = $hidden;
        $this->data = &$data; 
        $this->forms = &$forms; 
    }
    
    function add(&$element) {
        $this->data[] = &$element;
    }
    
    function xml_start($ns = '') {
        return phpx_xml_start_tag('section', phpx_xml_attrs(array(
            'name' => $this->name, 
            'hint' => $this->hint, 
            'hidden' => $this->hidden, 
            )), $ns); 
    }
    
    function xml_end($ns = '') {
        return phpx_xml_end_tag('section', $ns); 
    }
    
    function xml_data($ns = '') {
        if ($this->data)
            foreach ($this->data as $data)
                $xml .= $data->xml($ns); 
        return $xml; 
    }
    
    function xml_forms($ns = '') {
        if ($this->forms)
            foreach ($this->forms as $form)
                $xml .= $form->xml($ns); 
        return $xml; 
    }
    
    function xml($ns = '') {
        return $this->xml_start($ns)
            . $this->xml_data($ns)
            . $this->xml_forms($ns)
            . $this->xml_end($ns); 
    }
}

function zlw_af_methods($methods, $ns = '') {
    if (is_null($methods)) return NULL;
    if (is_string($methods)) {
        $names = explode(':', $methods);
        foreach ($names as $name) {
            $method = new zlw_af_method($name);
            $xml .= $method->xml($ns);
        }
    } else if (is_array($methods)) {
        foreach ($methods as $method)
            $xml .= $method->xml($ns);
    } else if (is_a($methods, 'zlw_af_method')) {
        $xml = $methods->xml($ns); 
    } else {
        die("Invalid methods $methods"); 
    }
    return $xml; 
}

# .section. output

class zlw_af_data extends phpx_error_support {
    var $name; 
    var $type;      # typep
    var $hold; 
    var $hidden;
    var $hint; 
    var $methods; 
    var $dumped; 
    
    function zlw_af_data($name = NULL, $typestr = 'string', $hold = NULL,
                         $hidden = NULL, $methods = NULL, $hint = NULL) {
        $this->phpx_error_support(ZLW_AF); 
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
        $this->_err("[DOUT] Not implemented. "); 
        return NULL; 
    }
}

class zlw_af_scalar extends zlw_af_data {
    var $value;         # value
    
    function zlw_af_scalar($name, $value, $typestr = 'string', $hold = NULL, 
                           $hidden = NULL, $methods = NULL, $hint = NULL) {
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
            )), $ns);
        $xml .= $this->type->xml($ns); 
        $xml .= $this->xml_methods($ns); 
        $xml .= phpx_xml_value($this->var);
        $xml .= phpx_xml_end_tag('scalar', $ns);
        $this->dumped = true; 
        return $xml; 
    }
}

class zlw_af_list extends zlw_af_data {
    var $items; 
    var $sort; 
    var $sort_order; 
    
    function zlw_af_list($name, $items = NULL, $typestr = 'string',
                         $hold = NULL, $hidden = NULL, $methods = NULL,
                         $hint = NULL, $sort = NULL, $sort_order = NULL) {
        $this->zlw_af_data($name, $typestr, $hold, $hidden, $methods, $hint); 
        if (is_array($items))
            $this->items = &$items; 
        else if (is_string($items))
            $this->items = explode(':', $items); 
        $this->sort = $sort; 
        $this->sort_order = $sort_order; 
    }

    function add($item) {
        $this->items[] = $item;
    }
    
    function xml_items($ns = '') {
        if (is_null($this->items))
            return NULL; 
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
    var $entries; 
    var $sort; 
    var $sort_order; 
    
    function zlw_af_map($name, $entries = NULL, $typestr = 'string',
                        $hold = NULL, $hidden = NULL, $methods = NULL,
                        $hint = NULL, $sort = NULL, $sort_order = NULL) {
        $this->zlw_af_data($name, $typestr, $hold, $hidden, $methods, $hint); 
        if (is_array($entries) || is_object($entries))
            $this->entries = &$entries; 
        else if (is_string($entries))
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

class zlw_af_table extends zlw_af_data {
    var $columns; 
    var $rows; 
    
    function zlw_af_table($name, $rows = NULL, $columns = NULL,
                          $typestr = 'string', $hold = NULL, $hidden = NULL,
                          $methods = NULL, $hint = NULL) {
        $this->zlw_af_data($name, $typestr, $hold, $hidden, $methods, $hint);
        $this->rows = &$rows; 
        if (! is_null($columns)) {
            $this->columns = &$columns;
        } else if (sizeof($rows) > 0) {
            foreach ($rows[0] as $name=>$value)
                $this->add_column($name);
        }
    }
    
    function add_column($name, $typestr = NULL, $primary_key = NULL,
                        $sort_priority = NULL, $sort_order = NULL) {
        $column->name = $name;
        if (is_null($typestr))
            $column->type = &$this->type;
        else
            $column->type = &zlw_af_type($typestr);
        $column->primary_key = $primary_key;
        $column->sort_priority = $sort_priority;
        $column->sort_order = $sort_order;
        $this->columns[] = &$column; 
    }
    
    function add($row) {
        $this->rows[] = &$row;
    }
    
    function xml_columns($ns = '') {
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
    
    function xml_rows($ns = '', $rns = '') {
        if ($this->rows == NULL) return NULL; 
        foreach ($this->rows as $row) {
            $xml .= phpx_xml_start_tag('row', $ns); 
            if (is_array($row))
                foreach ($this->columns as $column) {
                    $name = $column->name; 
                    $xml .= phpx_xml_tag($name, NULL, $row[$name], $ns); 
                }
            else if (is_object($row))
                foreach ($this->columns as $column) {
                    $name = $column->name; 
                    $xml .= phpx_xml_tag($name, NULL, $row->$name, $ns); 
                }
            $xml .= phpx_xml_end_tag('row'); 
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
    var $user; 
    
    function zlw_af_user($name, $user, $typestr = NULL, $hold = NULL,
                         $hidden = NULL, $methods = NULL, $hint = NULL) {
        $this->zlw_af_data($name, $typestr, $hold, $hidden, $methods, $hint);
        $this->user = &$user; 
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

# .section. input

class zlw_af_input extends phpx_error_support {
    var $name; 
    var $var; 
    var $multiline; 
    var $read_only; 
    var $max_length; 
    var $constraints; 
    var $selection; 
    
    function zlw_af_input($name, $typestr = 'string', $value = NULL, 
                          $multiline = false, $read_only = false,
                          $max_length = NULL, $constraints = NULL, 
                          $selection = NULL) {
        $this->phpx_error_support(ZLW_AF);
        $this->name = $name;
        if (is_a($value, 'zlw_af_variant'))
            $this->var = $value;        # ignore $typestr
        else
            $this->var = new zlw_af_variant($value, $typestr);
        $this->multiline = $multiline;
        $this->read_only = $read_only;
        $this->max_length = $max_length;
        $this->constraints = $constraints;
        $this->selection = &$selection; 
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
            if (! $this->constraints->check($this->var))
                return false;           # ERR.
        return true; 
    }
    
    function xml_selection($ns = '') {
        if (is_null($this->selection))
            return NULL;
        if (is_string($this->selection)) {
            list($type, $name) = explode(':', $this->selection, 2);
            return phpx_xml_tag($type . '-ref', NULL, $name, $ns);
        }
        if ($this->selection->dumped) {
            if (is_a($this->selection, 'zlw_af_list'))
                $type = 'list';
            else if (is_a($this->selection, 'zlw_af_map'))
                $type = 'map';
            else if (is_a($this->selection, 'zlw_af_table'))
                $type = 'table';
            else
                die("Invalid selection type: " . get_class($this->selection));
            $xml = phpx_xml_tag($type . '-ref', NULL, $this->selection->name, $ns);
        } else {
            $xml = $this->selection->xml($ns);
        }
        return $xml; 
    }
    
    function xml_constraints($ns = '') {
        if (is_null($this->constraints))
            return NULL; 
        return $this->constraints->xml(); 
    }
    
    function xml($ns = '') {
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
        if ($xml == NULL)
            return phpx_xml_tag('input', $attrs, NULL, $ns); 
        return phpx_xml_start_tag('input' . phpx_xml_attrs($attrs), $ns)
            . $xml
            . phpx_xml_end_tag('input', $ns); 
    }
}

class zlw_af_method {
    var $name; 
    var $type;      # Reserved.
    var $hint;
    var $param; 
    var $const; 
    
    function zlw_af_method($name, $hint = '', $typestr = 'default', 
                           $param = NULL, $const = false) {
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
            $xml .= zlw_af_parameters($this->param, 'method-parameter', $ns); 
            $xml .= phpx_xml_end_tag('method', $ns); 
        } else {
            $xml = phpx_xml_tag('method', $attrs, $this->type, $ns); 
        }
        return $xml; 
    }
}

# .section. form

global $ZLW_AF_TICKETS; 

class zlw_af_ticket extends phpx_error_support {
    var $tid; 
    var $timestamp; 
    var $src; 
    var $next; 
    var $trace_prev; 
    var $trace_next; 
    
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
        if (is_null($this->trace_prev)) return NULL; 
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
    var $name; 
    var $type; 
    var $hint; 
    var $form_method; 
    var $inputs;                        # name => input
    var $methods; 
    var $ticket; 
    
    function zlw_af_form($name, $inputs = NULL, $typestr = 'default',
                         $methods = NULL, $hint = NULL,
                         $form_method = NULL) {
        $this->name = $name;
        $this->type = &zlw_af_type($typestr);
        $this->hint = zlw_af_hint_prep($hint);
        $this->form_method = $form_method;
        $this->inputs = &$inputs;
        $this->methods = &$methods;
    }
    
    function check() {
        foreach ($this->inputs as $input)
            if (! $input->check())
                return false;           # ERR.
        return true; 
    }
    
    function add_input($input) {
        $name = $input->name;
        $old = &$this->inputs[$name];
        if (is_null($old)) {
            $this->inputs[$name] = &$input;
            return 1; 
        }
        if (! is_array($old)) {
            $new = array(&$old); 
            $this->inputs[$name] = &$new; 
            $old = &$this->inputs[$name]; 
        }
        $old[] = &$input; 
        return sizeof($old); 
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
    
    function xml_inputs($ns = '') {
        if ($this->inputs)
            foreach ($this->inputs as $input)
                $xml .= $input->xml($ns); 
        return $xml; 
    }
    
    function xml_methods($ns = '') {
        return zlw_af_methods($this->methods, $ns); 
    }
    
    function xml($ns = '', $outer = true) {
        if ($outer) $xml .= $this->xml_start($ns); 
        $xml .= $this->xml_inputs($ns); 
        $xml .= $this->xml_methods($ns); 
        if ($outer) $xml .= $this->xml_end($ns); 
        return $xml; 
    }
}

?>