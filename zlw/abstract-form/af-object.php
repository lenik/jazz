<?php

require_once dirname(__FILE__) . "/af-xml.php"; 

class zlw_af_input {
    var $type; 
    var $typep; 
    var $constraints; 
    var $value; 
    var $ref; 
    var $read_only; 
    var $hidden; 
    
    function constrain($constraint, $name = NULL, $level = NULL, $reason = NULL, $side = NULL) {
        if ($constraints == NULL)
            $constraints = array(); 
        $constraint->name = $name; 
        $constraint->level = $level; 
        $constraint->reason = $reason; 
        $constraint->side = $side; 
        $constraints[] = $constraint; 
        (new zlw_af_c_range); 
    }
    
    function check_constraints() {
        if ($constraints == NULL)
            return true; 
        foreach ($constraints as $constraint) {
            # skip client-side-only constraints.
            if ($constraint->side == 'client')
                continue; 
            if (! $constraint->check($this)) {
                # checking failure
                # Logger??
                if ($constraint->level == 'warning') {
                    error_logger; 
                } else {
                    die; 
                }
            }
        }
        return true; 
    }
}

class zlw_af_cc {
    var $model = 'base'; 
    var $err_summary; 
    var $err_detail; 
    var $err_source; 
    
    function check() {
        return check_failure('Unexpected', 
            'The check() function of the constraint base should not be called'); 
    }
    
    function check_failure($summary, $detail) {
        $this->err_summary = $summary; 
        $this->err_detail = $detail; 
        $this->err_source = $this->model; 
        return false; 
    }
}

class zlw_af_cc_range extends zlw_af_cc {
    # min min-exclusive max max-exclusive
    var $model = 'range'; 
    var $min; 
    var $max; 
    var $min_excluded; 
    var $max_excluded; 
    
    function zlw_af_cc_range($min, $max, $min_excluded = NULL, $max_excluded = NULL) {
        $this->min = $min; 
        $this->max = $max; 
        $this->min_excluded = $min_excluded; 
        $this->max_excluded = $max_excluded; 
    }
    
    function check($input) {
        assert($input != NULL); 
        switch ($input->type) {
        case 'number': 
        case 'int': 
            $n = 1 * $input->value; 
            break; 
        default: 
            $n = strlen($input->value); 
        }
        if ($min != NULL)
            if ($min_excluded) {
                if ($n <= $min)
                    return check_failure("Too small", "Less than (or equal to) $min"); 
            } else {
                if ($n < $min)
                    return check_failure("Too small", "Less than $min"); 
            }
        if ($max != NULL)
            if ($max_excluded) {
                if ($n >= $max)
                    return check_failure("Too large", "Greater than (or equal to) $max"); 
            } else {
                if ($n > $max)
                    return check_failure("Too large", "Greater than $max"); 
            }
        return true; 
    }
}

class zlw_af_cc_pattern extends zlw_af_cc {
    # regex case-insensitive dot-all multiline comment
    var $model = 'pattern'; 
    var $regex; 
    var $case_insensitive; 
    var $dot_all; 
    var $multiline; 
    var $comment; 
    
    function zlw_af_cc_pattern($regex, $case_insensitive = NULL, $dot_all = NULL, 
            $multiline = NULL, $comment = NULL) {
        $this->regex = $regex; 
        $this->case_insensitive = $case_insensitive; 
        $this->dot_all = $dot_all; 
        $this->multiline = $multiline; 
        $this->comment = $comment; 
    }
    
    function check($input) {
        assert($input != NULL); 
        $s = $input->value; 
        $pattern = $regex; 
        $flags = ''; 
        if ($case_insensitive) $flags .= 'i'; 
        if ($dot_all) $flags .= 's'; 
        if ($multiline) $flags .= 'm'; 
        if ($comment) $flags .= 'x'; 
        if (preg_match("/$regex/$flags", $s) == 0)
            # not matched. 
            return $this->check_failure("Illegal Syntax", "Not matched to the pattern $regex"); 
        return true; 
    }
}

class zlw_af_cc_and extends zlw_af_cc {
    var $items; 
    
    function zlw_af_cc_and() {
        $args = func_get_args(); 
        $items = $args; 
    }
    
    function check($input) {
        foreach ($items as $item) {
            if ($item->side == 'client')
                continue; 
            if (! $item->check($input))
                ; #$this->
        }
        return true; 
    }
}

global $ZLW_AF_TICKETS; 

class zlw_af_ticket {
    var $next; 
}

class zlw_af_form extends phpx_http_buffer {
    var $ticket; 
    var $inputs; 
    var $methods; 
    
    var $xmlns; 
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
    
    function dump($buffer = false, $outer = true) {
        if ($buffer)
            $this->buffer_start(); 
        if ($outer) {
            $this->output("<$xmlns:form"); 
            if ($this->name)
                $this->output(" name=" . phpx_xml_attr($this->name)); 
            if ($this->type)
                $this->output(" type=" . phpx_xml_attr($this->type)); 
            if ($this->hint)
                $this->output(" hint=" . phpx_xml_attr($this->hint)); 
            if ($this->form_method)
                $this->output(" form-method=" . phpx_xml_attr($this->form_method)); 
            $this->output(">\n"); 
        }
        foreach ($this->inputs as $input) {
            $this->output($input->build_xml() . "\n"); 
        }
        foreach ($this->methods as $method) {
            $this->output($method->build_xml() . "\n"); 
        }
        if ($outer) {
            $this->output("</$xmlns:form>\n"); 
        }
        return $this->buffer_end(); 
    }
}

?>