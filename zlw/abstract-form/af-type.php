<?php

require_once dirname(__FILE__) . '/af-base.php'; 

function &zlw_af_type($typestr) {
    global $ZLW_AF_TYPE; 
    $type = &$ZLW_AF_TYPE[$typestr]; 
    if (is_null($type)) {
        list($name, $typep) = explode(':', $typestr); 
        $type = eval("return new zlw_af_type_$name(\$typep)"); 
    }
    return $type; 
}

class zlw_af_type extends phpx_error_support {
    var $name = '(type-base)'; 
    var $typep; 
    
    function zlw_af_type($typep = NULL) {
        $this->phpx_error_support(ZLW_AF); 
        if (is_array($typep)) {
            $this->typep = $typep; 
        } else {
            $this->typep = phpx_map_parse($typep); 
        }
    }
    
    # <internal> => <string>
    function format($value) {
        $this->_err('[TYPE] Not implemented'); 
        return NULL; 
    }
    
    # <string> => <internal>
    # @throws ZLW_AF*
    function parse($string) {
        $this->_err('[TYPE] Not implemented'); 
        return NULL; 
    }
    
    function typep_xml($ns = '') {
        return zlw_af_parameters($this->typep, 'type-parameter', $ns); 
    }
}

class zlw_af_type_string extends zlw_af_type {
    var $name = 'string'; 
    
    function format($value) {
        return "$value"; 
    }
    function parse($string) {
        return $string; 
    }
}

class zlw_af_type_binary extends zlw_af_type {
    var $name = 'binary'; 
    # @inherit
}

class zlw_af_type_number extends zlw_af_type {
    var $name = 'number'; 
    
    function format($value) {
        return "$value"; 
    }
    function parse($string) {
        if (! is_numeric($string)) {
            $this->_err("[TYPE] Not a number: $string"); 
            return 0; 
        }
        $number = $string + 0; 
        if ("$string" != "$number")
            $this->_warn("[TYPE] Numeric information lost: from $string, to $number"); 
        return $number; 
    }
}

class zlw_af_type_float extends zlw_af_type_number {
    var $name = 'float'; 
    # @inherit
}

class zlw_af_type_double extends zlw_af_type_number {
    var $name = 'double'; 
    # @inherit
}

class zlw_af_type_decimal extends zlw_af_type_number {
    var $name = 'decimal'; 
    # @inherit
}

class zlw_af_type_integer extends zlw_af_type_decimal {
    var $name = 'integer'; 
    
    function format($value) {
        return '' . floor($value); 
    }
    function parse($string) {
        $number = parent::parse($string); 
        $integer = $number >= 0 ? floor($number) : ceil($number); 
        if ($number != $integer) 
            $this->_warn("[TYPE] Decimal fraction is truncated: from $number, to $integer"); 
        return $integer; 
    }
}

class zlw_af_type_long extends zlw_af_type_integer {
    var $name = 'long'; 
    
    function parse($string) {
        $integer = zlw_af_type_integer::parse($string); 
        if ($integer < -2147483647 || $integer > 2147483647)
            $this->_warn("[TYPE] Overflow of long int type: $integer"); 
        return $integer; 
    }
}

class zlw_af_type_int extends zlw_af_type_long {
    var $name = 'int'; 
    # @inherit
}

class zlw_af_type_short extends zlw_af_type_int {
    var $name = 'short'; 
    
    function parse($string) {
        $integer = zlw_af_type_integer::parse($string); 
        if ($integer < -32768 || $integer > 32767)
            $this->_warn("[TYPE] Overflow of short int type: $integer"); 
        return $integer; 
    }
}

class zlw_af_type_byte extends zlw_af_type_short {
    var $name = 'byte'; 
    
    function parse($string) {
        $integer = zlw_af_type_integer::parse($string); 
        if ($integer < -128 || $integer > 127)
            $this->_warn("[TYPE] Overflow of short int type: $integer"); 
        return $integer; 
    }
}

class zlw_af_type_boolean extends zlw_af_type {
    var $name = 'boolean'; 
    
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
    var $name = 'date_time'; 
    var $format = 'Y-m-d H:i:s'; 
    
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
    var $name = 'date'; 
    var $format = 'Y-m-d'; 
    
    # function format($value)
    function parse($string) {
        $date_time = parent::parse($string); 
        # truncate time ?
        return $date_time; 
    }
}

class zlw_af_type_time extends zlw_af_type_date_time {
    var $name = 'time'; 
    var $format = 'H:i:s'; 
    
    # function format($value)
    function parse($string) {
        $date_time = parent::parse($string); 
        $time = $date_time; ## ??
        return $time; 
    }
}

class zlw_af_variant {
    var $type; 
    var $value; 
    
    function zlw_af_variant($init, $typestr = 'string', $parse = true) {
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
        return $this->type->format($this->value); 
    }
    
    function parse($string) {
        return $this->value = $this->type->parse($string); 
    }
    
    function get_type() {
        if (is_null($this->type))
            $this->type = gettype($this->value); 
        return $this->type->name; 
    }
    
    function typeof($base) {
        return zlw_af_type_of($this->get_type(), $base); 
    }
}

$ZLW_AF_TYPE_BASE = array(
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
    global $ZLW_AF_TYPE_INHERITS; 
    do {
        if ($type == $base) return true; 
    } while ($type = $ZLW_AF_TYPE_BASE[$type]); 
    return false; 
}

?>