<?php

require_once dirname(__FILE__) . '/../common-php/error.php'; 

function &zlw_af_type($typestr) {
    global $ZLW_AF_TYPE; 
    $type = &$ZLW_AF_TYPE[$typestr]; 
    if (is_null($type)) {
        list($name, $param) = explode(':', $typestr, 2);
        if ($param == '') $param = null; 
        $typecls = 'zlw_af_type_' . $name; 
        if (class_exists($typecls))
            $type = eval("return new $typecls(\$param);");
        else
            $type = new zlw_af_type($name, $param); 
    }
    return $type; 
}

class zlw_af_type extends phpx_error_support {
    public $name = '(type-base)'; 
    public $param; 
    
    function zlw_af_type($name, $param = null) {
        $this->phpx_error_support(ZLW_AF);
        $this->name = $name; 
        if (is_array($param))
            $this->param = $param; 
        else
            $this->param = phpx_map_parse($param); 
    }
    
    # <internal> => <string>
    function format($value) {
        $value; 
        $this->_err('[AF-TYPE] Not implemented'); 
        return null; 
    }
    
    # <string> => <internal>
    # @throws ZLW_AF*
    function parse($string) {
        $string; 
        $this->_err('[AF-TYPE] Not implemented'); 
        return null; 
    }
    
    function xml($ns = '') {
        return zlw_af_parameters($this->param, 'type-parameter', $ns); 
    }
}

class zlw_af_type_def  extends zlw_af_type {
    public $name = null;
    # @inherit
}

class zlw_af_type_string extends zlw_af_type {
    public $name = 'string'; 
    
    function format($value) {
        return "$value"; 
    }
    function parse($string) {
        return $string; 
    }
}

class zlw_af_type_binary extends zlw_af_type {
    public $name = 'binary'; 
    # @inherit
}

class zlw_af_type_number extends zlw_af_type {
    public $name = 'number'; 
    
    function format($value) {
        return "$value"; 
    }
    function parse($string) {
        if (! is_numeric($string)) {
            $this->_err("[AF-TYPE] Not a number: $string"); 
            return 0; 
        }
        $number = $string + 0; 
        if ("$string" != "$number")
            $this->_warn("[AF-TYPE] Numeric information lost: from $string, to $number"); 
        return $number; 
    }
}

class zlw_af_type_float extends zlw_af_type_number {
    public $name = 'float'; 
    # @inherit
}

class zlw_af_type_double extends zlw_af_type_number {
    public $name = 'double'; 
    # @inherit
}

class zlw_af_type_decimal extends zlw_af_type_number {
    public $name = 'decimal'; 
    # @inherit
}

class zlw_af_type_integer extends zlw_af_type_decimal {
    public $name = 'integer'; 
    
    function format($value) {
        return '' . floor($value); 
    }
    function parse($string) {
        $number = parent::parse($string); 
        $integer = $number >= 0 ? floor($number) : ceil($number); 
        if ($number != $integer) 
            $this->_warn("[AF-TYPE] Decimal fraction is truncated: from $number, to $integer"); 
        return $integer; 
    }
}

class zlw_af_type_long extends zlw_af_type_integer {
    public $name = 'long'; 
    
    function parse($string) {
        $integer = zlw_af_type_integer::parse($string); 
        if ($integer < -2147483647 || $integer > 2147483647)
            $this->_warn("[AF-TYPE] Overflow of long int type: $integer"); 
        return $integer; 
    }
}

class zlw_af_type_int extends zlw_af_type_long {
    public $name = 'int'; 
    # @inherit
}

class zlw_af_type_short extends zlw_af_type_int {
    public $name = 'short'; 
    
    function parse($string) {
        $integer = zlw_af_type_integer::parse($string); 
        if ($integer < -32768 || $integer > 32767)
            $this->_warn("[AF-TYPE] Overflow of short int type: $integer"); 
        return $integer; 
    }
}

class zlw_af_type_byte extends zlw_af_type_short {
    public $name = 'byte'; 
    
    function parse($string) {
        $integer = zlw_af_type_integer::parse($string); 
        if ($integer < -128 || $integer > 127)
            $this->_warn("[AF-TYPE] Overflow of short int type: $integer"); 
        return $integer; 
    }
}

class zlw_af_type_boolean extends zlw_af_type {
    public $name = 'boolean'; 
    
    function format($value) {
        return $value ? 'true' : 'false'; 
    }
    function parse($string) {
        if ($string == 'true')
            return true; 
        return $string != 0; 
    }
}

class zlw_af_type_date_time extends zlw_af_type {
    public $name = 'date_time'; 
    public $format = 'Y-m-d H:i:s'; 
    
    # <GMT-timestamp> => <locale-timestr>
    function format($value) {
        return time_0_format($value, $this->format); 
    }
    # <locale-timestr> => <GMT-timestamp>
    function parse($string) {
        return time_0_of($string); 
    }
}

class zlw_af_type_date extends zlw_af_type_date_time {
    public $name = 'date'; 
    public $format = 'Y-m-d'; 
    
    # function format($value)
    function parse($string) {
        $date_time = parent::parse($string); 
        # truncate time ?
        return $date_time; 
    }
}

class zlw_af_type_time extends zlw_af_type_date_time {
    public $name = 'time'; 
    public $format = 'H:i:s'; 
    
    # function format($value)
    function parse($string) {
        $date_time = parent::parse($string); 
        $time = $date_time; ## ??
        return $time; 
    }
}

class zlw_af_variant {
    public $type;                       # zlw_af_type, never be null.
    public $value; 
    
    function zlw_af_variant($init, $typestr = 'string', $parse = true) {
        assert(! is_null($typestr)); 
        if (is_string($typestr)) {
            if (! ($type = &zlw_af_type($typestr)))
                die("Invalid type: $typestr"); 
            $this->type = $type; 
        } else {
            $this->type = $typestr; 
        }
        if ($parse)
            $this->value = $this->parse($init); 
        else
            $this->value = $init; 
    }
    
    function format() {
        assert(! is_null($this->type)); 
        return $this->type->format($this->value); 
    }
    
    function parse($string) {
        assert(! is_null($this->type)); 
        return $this->value = $this->type->parse($string); 
    }
    
    function is_init() {
        assert(! is_null($this->type));
        return is_null($this->value); 
    }
    
    function uninit() {
        unset($this->value); 
    }
    
    function typeof($base) {
        assert(! is_null($this->type)); 
        return zlw_af_type_of($this->type->name, $base); 
    }
}

$ZLW_AF_TYPE_BASE = array(
    # derived => base
    'float' => 'number', 
    'double' => 'number', 
    'decimal' => 'number', 
    'integer' => 'decimal', 
    'long' => 'integer', 
    'int' => 'long', 
    'short' => 'int', 
    'byte' => 'short', 
    'date' => 'date_time', 
    'time' => 'date_time', 
    ); 

function zlw_af_type_of($type, $base) {
    global $ZLW_AF_TYPE_BASE; 
    do {
        if ($type == $base) return true; 
    } while (($type = $ZLW_AF_TYPE_BASE[$type])); 
    return false; 
}

?>