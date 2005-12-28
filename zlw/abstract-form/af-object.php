<?php

require_once dirname(__FILE__) . "/af-type.php"; 
require_once dirname(__FILE__) . "/af-constraint.php"; 

# .section. output

class zlw_af_data extends phpx_error_support {
    var $name; 
    var $type;      # typep
    var $hold; 
    var $hidden; 
    var $methods; 
    
    function zlw_af_data($name = NULL, $hold = false, $hidden = false, $methods = NULL) {
        $this->phpx_error_support(ZLW_AF); 
        $this->name = $name; 
    }
    
    function xml($ns = '') {
        $this->_err("[DOUT] Not implemented. "); 
        return NULL; 
    }
}

class zlw_af_scalar extends zlw_af_data {
    var $value;         # Xvalue
    
    function zlw_af_scalar($name, $value, $typestr = 'string', $hold = false, $hidden = false, $methods = NULL) {
        $this->zlw_af_data($name, $hold, $hidden, $methods); 
        $this->xval = $value; 
        $this->typestr = $typestr; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('scalar' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden, 
            )), $ns); 
        $xml .= phpx_xml_xvalue($this->var); 
        $xml .= phpx_xml_end_tag('scalar', $ns); 
        return $xml; 
    }
}

class zlw_af_list extends zlw_af_data {
    var $sort; 
    var $sort_order; 
    var $items; 
    
    function zlw_af_list($name, $hold = false, $hidden = false, $methods = NULL) {
        $this->zlw_af_data($name, $hold, $hidden, $methods); 
    }
    
    function xml_items($ns = '') {
        if ($this->items)
            foreach ($this->items as $item) {
                $xml .= phpx_xml_start_tag('item', $ns); 
                $xml .= phpx_xml_xvalue($item); 
                $xml .= phpx_xml_end_tag('item', $ns); 
            }
        return $xml; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('list' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden, 
            'sort' => $this->sort, 
            'sort-order' => $this->sort_order, 
            )), $ns); 
        $xml .= $this->xml_items($ns); 
        $xml .= phpx_xml_end_tag('list', $ns); 
        return $xml; 
    }
}

class zlw_af_map extends zlw_af_data {
    var $sort; 
    var $sort_order; 
    var $entries; 
    
    function zlw_af_map($name, $hold = false, $hidden = false, $methods = NULL) {
        $this->zlw_af_data($name, $hold, $hidden, $methods); 
    }
    
    function xml_entries($ns = '') {
        if ($this->entries)
            foreach ($this->entries as $key=>$value) {
                $xml .= phpx_xml_start_tag('entry key=' . phpx_xml_attr($key), $ns); 
                $xml .= phpx_xml_xvalue($value);
                $xml .= phpx_xml_end_tag('entry', $ns); 
            }
        return $xml; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('map' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden, 
            'sort' => $this->sort, 
            'sort-order' => $this->sort_order, 
            )), $ns); 
        $xml .= phpx_xml_end_tag('map', $ns); 
        return $xml; 
    }
}

class zlw_af_table extends zlw_af_data {
    var $columns; 
    var $rows; 
    
    function zlw_af_table($name, $hold = false, $hidden = false, $methods = NULL) {
        $this->zlw_af_data($name, $hold, $hidden, $methods); 
    }
    
    function xml_columns($ns = '') {
        assert($this->columns); 
        foreach ($this->columns as $column) {
            $xml .= phpx_xml_start_tag('column' . phpx_xml_attrs(array(
                'name' => $column->name, 
                'type' => $column->type, 
                'primary-key' => $column->primary_key, 
                'sort-priority' => $column->sort_priority, 
                'sort-order' => $column->sort_order, 
                )), $ns); 
            $xml .= phpx_xml_end_tag('column', $ns); 
        }
        return $xml; 
    }
    
    function xml_rows($ns = '', $rns = '') {
        if ($this->rows)
            foreach ($this->rows as $row) {
                $xml .= phpx_xml_start_tag('row', $ns); 
                foreach ($this->columns as $column) {
                    $field = $column->name; 
                    $xml .= phpx_xml_start_tag($field, $rns = ''); 
                    $xml .= phpx_xml_xvalue($row->$field, $rns = ''); 
                    $xml .= phpx_xml_end_tag($field, $rns = ''); 
                }
                $xml .= phpx_xml_end_tag('row'); 
            }
        return $xml; 
    }
    
    function xml($ns = '', $rns = '') {
        $xml = phpx_xml_start_tag('table' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden, 
            )), $ns); 
        $xml .= $this->xml_columns($ns); 
        $xml .= $this->xml_rows($ns, $rns); 
        $xml .= phpx_xml_end_tag('table', $ns); 
        return $xml; 
    }
}

class zlw_af_user extends zlw_af_data {
    var $user; 
    
    function zlw_af_user($name, $hold = false, $hidden = false, $methods = NULL) {
        $this->zlw_af_data($name, $hold, $hidden, $methods); 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('user' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type, 
            'hold' => $this->hold, 
            'hidden' => $this->hidden, 
            )), $ns); 
        $xml .= phpx_xml_xvalue($this->user, $ns); 
        $xml .= phpx_xml_end_tag('user', $ns); 
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
    
    var $ref; 
    
    function zlw_af_input() {
        $this->phpx_error_support(ZLW_AF); 
    }
    
    function xml($ns = '') {
        $type = $this->var->get_type(); 
        if (! is_null($this->var->value))
            $init = $this->var->format(); 
        $xml = phpx_xml_start_tag('input' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $type, 
            'init' => $init, 
            'multiline' => $this->multiline, 
            'read-only' => $this->read_only, 
            'max-length' => $this->max_length, 
            )), $ns); 
        $xml .= $this->var->typep_xml($ns); 
        if ($this->constraints)
            $xml .= $this->constraints->xml(); 
        $xml .= phpx_xml_end_tag('input', $ns); 
        return $xml; 
    }
    
    function check() {
        if ($this->constraints)
            if (! $this->constraints->check($this->var))
                return false;           # ERR.
        return true; 
    }
}

class zlw_af_method {
    var $name; 
    var $type;      # Reserved.
    var $hint; 
    var $const; 
    var $param; 
    
    function zlw_af_method($name, $hint = '', $const = false) {
        $this->name = $name; 
        $this->hint = zlw_af_hint_prep($hint); 
        $this->const = $const; 
    }
    
    function xml($ns = '') {
        if (! is_null($this->type)) {
            $type = $this->type->get_type(); 
            $typep = $this->type->typep_xml($ns); 
        }
        $xml = phpx_xml_start_tag('method' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $type, 
            'hint' => $this->hint, 
            'const' => $this->const, 
            )), $ns); 
        $xml .= $typep; 
        $xml .= zlw_af_parameters($this->param, 'method-parameter', $ns); 
        $xml .= phpx_xml_end_tag('method', $ns); 
        return $xml; 
    }
}

# .section. form

global $ZLW_AF_TICKETS; 

class zlw_af_ticket {
    var $next; 
}

class zlw_af_form {
    var $ticket; 
    var $inputs; 
    var $methods; 
    
    var $name; 
    var $type; 
    var $hint; 
    var $form_method; 
    
    function check() {
        foreach ($this->inputs as $input) {
            if (! $input->check()) {
                return false; 
            }
        }
        return true; 
    }
    
    function xml_start($ns = '') {
        return phpx_xml_start_tag('form' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'type' => $this->type, 
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
        if ($this->methods)
            foreach ($this->methods as $method)
                $xml .= $method->xml($ns); 
        return $xml; 
    }
    
    function xml($ns = '', $outer = true) {
        if ($outer) $xml .= $this->xml_start($ns); 
        $xml .= $this->xml_inputs($ns); 
        $xml .= $this->xml_methods($ns); 
        if ($outer) $xml .= $this->xml_end($ns); 
        return $xml; 
    }
}

# .section. section

class zlw_af_section {
    var $name; 
    var $hint; 
    var $hidden; 
    var $data; 
    var $forms; 
    
    function xml_start($ns = '') {
        phpx_xml_start_tag('section', phpx_xml_attrs(array(
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

?>