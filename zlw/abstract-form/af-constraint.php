<?php

require_once dirname(__FILE__) . "/../common-php/xml.php"; 
require_once dirname(__FILE__) . "/../common-php/error.php"; 

class zlw_af_cc extends phpx_error_support {
    function zlw_af_cc() {
        $this->phpx_error_support(ZLW_AF); 
    }
    
    function xml($ns = '') {
        $this->_err('[CONS] Not Implemented'); 
        return NULL; 
    }
    
    function check($var) {
        $this->_err('[CONS] Not Implemented'); 
        return false; 
    }
}

class zlw_af_cc_item extends zlw_af_cc {
    var $cc; 
    var $name; 
    var $reason; 
    var $level; 
    var $side; 
    
    function zlw_af_cc_item($cc, $name = NULL, $reason = NULL, $level = NULL, $side = NULL) {
        $this->cc = $cc; 
        $this->name = $name; 
        $this->reason = $reason; 
        $this->level = $level; 
        $this->side = $side; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('constraint' . phpx_xml_attrs(array(
            'name' => $this->name, 
            'reason' => $this->reason, 
            'level' => $this->level, 
            'side' => $this->side, 
            )), $ns); 
        $xml .= $this->cc->xml($ns); 
        $xml .= phpx_xml_end_tag('constraint', $ns); 
        return $xml; 
    }
    
    function check($var) {
        if ($this->side == 'client')
            return true;                # IGNORE. 
        if (! $this->cc->check($var))
            return $this->_err("[CONS.$this->name] Constraint checking failure: $this->reason (level=$this->level)"); 
        return true; 
    }
}

class zlw_af_cc_list {
    var $items; 
    
    function zlw_af_cc_list() {
        parent::zlw_af_cc(); 
        $args = func_get_args(); 
        $items = $args; 
    }
    
    function xml($ns = '') {
        foreach ($this->items as $item)
            $xml .= $item->xml($ns); 
        return $xml; 
    }
    
    function check($var) {
        foreach ($this->items as $item) {
            if (! $item->check($var))
                return false;           # ERR.
        }
        return true; 
    }
}

class zlw_af_cc_not extends zlw_af_cc {
    var $regular; 
    
    function zlw_af_cc_not($regular) {
        parent::zlw_af_cc(); 
        $this->regular = $regular; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('not', $ns); 
        $xml .= $this->regular->xml($ns); 
        $xml .= phpx_xml_end_tag('not', $ns); 
        return $xml; 
    }
    
    function check($var) {
        return ! $this->regular->check($var); 
    }
}

class zlw_af_cc_and extends zlw_af_cc {
    var $items; 
    
    function zlw_af_cc_and() {
        parent::zlw_af_cc(); 
        $args = func_get_args(); 
        $items = $args; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('and', $ns); 
        foreach ($this->items as $item)
            $xml .= $item->xml($ns); 
        $xml .= phpx_xml_end_tag('and', $ns); 
        return $xml; 
    }
    
    function check($var) {
        foreach ($this->items as $item) {
            if (! $item->check($var))
                return false;           # ERR.
        }
        return true; 
    }
}

class zlw_af_cc_or extends zlw_af_cc {
    var $items; 
    
    function zlw_af_cc_or() {
        parent::zlw_af_cc(); 
        $args = func_get_args(); 
        $items = $args; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('or', $ns); 
        foreach ($this->items as $item)
            $xml .= $item->xml($ns); 
        $xml .= phpx_xml_end_tag('or', $ns); 
        return $xml; 
    }
    
    function check($var) {
        foreach ($this->items as $item) {
            if ($item->check($var))
                return true; 
        }
        return false;                   # ERR.
    }
}

class zlw_af_cc_xor extends zlw_af_cc {
    var $items; 
    
    function zlw_af_cc_xor() {
        parent::zlw_af_cc(); 
        $args = func_get_args(); 
        $items = $args; 
    }
    
    function xml($ns = '') {
        $xml = phpx_xml_start_tag('xor', $ns); 
        foreach ($this->items as $item)
            $xml .= $item->xml($ns); 
        $xml .= phpx_xml_end_tag('xor', $ns); 
        return $xml; 
    }
    
    function check($var) {
        $only = false; 
        foreach ($this->items as $item) {
            if ($item->check($var)) {
                if ($only)
                    return false;       # ERR.
                $only = true; 
            }
        }
        return false;                   # ERR.
    }
}

class zlw_af_cc_range extends zlw_af_cc {
    # min min-exclusive max max-exclusive
    var $model = 'range'; 
    var $min; 
    var $max; 
    var $min_excluded; 
    var $max_excluded; 
    
    function zlw_af_cc_range($min = NULL, $max = NULL, $min_excluded = NULL, $max_excluded = NULL) {
        parent::zlw_af_cc(); 
        $this->min = $min; 
        $this->max = $max; 
        $this->min_excluded = $min_excluded; 
        $this->max_excluded = $max_excluded; 
    }
    
    function xml($ns = '') {
        return phpx_xml_start_tag('range' . phpx_xml_attrs(array(
            'min' => $this->min, 
            'max' => $this->max, 
            'min-excluded' => $this->min_excluded, 
            'max-excluded' => $this->max_excluded, 
            )), $ns, true); 
    }
    
    function check($var) {
        assert($var != NULL); 
        if ($var->typeof('number'))
            $n = 1 * $var->value; 
        else
            $n = strlen($var->value); 
        if ($min != NULL)
            if ($min_excluded) {
                if ($n <= $min)
                    return $this->_err("[CONS] Too small: At least $min (exclude)"); 
            } else {
                if ($n < $min)
                    return $this->_err("[CONS] Too small: At least $min (include)"); 
            }
        if ($max != NULL)
            if ($max_excluded) {
                if ($n >= $max)
                    return $this->_err("[CONS] Too large: At most $max (exclude)"); 
            } else {
                if ($n > $max)
                    return $this->_err("[CONS] Too large: At most $max (include)"); 
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
        parent::zlw_af_cc(); 
        $this->regex = $regex; 
        $this->case_insensitive = $case_insensitive; 
        $this->dot_all = $dot_all; 
        $this->multiline = $multiline; 
        $this->comment = $comment; 
    }
    
    function xml($ns = '') {
        return phpx_xml_start_tag('pattern' . phpx_xml_attrs(array(
            'regex' => $this->regex, 
            'case-insensitive' => $this->case_insensitive, 
            'dot-all' => $this->dot_all, 
            'multiline' => $this->multiline, 
            'comment' => $this->comment, 
            )), $ns, true); 
    }
    
    function check($var) {
        assert($var != NULL); 
        # $var->typeof('string')
        $s = '' . $var->value; 
        $pattern = $regex; 
        $flags = ''; 
        if ($case_insensitive) $flags .= 'i'; 
        if ($dot_all) $flags .= 's'; 
        if ($multiline) $flags .= 'm'; 
        if ($comment) $flags .= 'x'; 
        if (preg_match("/$regex/$flags", $s) == 0)
            # not matched. 
            return $this->_err("[CONS] Illegal text format: Not matched to the pattern $regex"); 
        return true; 
    }
}

?>