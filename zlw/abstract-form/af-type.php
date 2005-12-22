<?php

/**
    string
        normalizedString
            token
                language
                Name
                    NCName
                        ID
                        IDREF
                            IDREFS
                        ENTITY
                            ENTITIES
                NMTOKEN
                    NMTOKENS
    binary
        base64Binary
        hexBinary
    boolean
    number
        float
        double
        decimal
            integer
                nonPositiveInteger
                    negativeInteger
                long
                    int
                        short
                            byte
                nonNegativeInteger
                    positiveInteger
                    unsignedLong
                        unsignedInt
                            unsignedShort
                                unsignedByte
    dateTime
    date
    time
    duration
    anyURI
    QName
    NOTATION
*/

class zlw_af_type {
    var $class = '(type-base)'; 
    var $typep; 
    
    function zlw_af_type($typep = NULL) {
        $this->typep = $typep; 
    }
    
    function format($value) {
        return "$class: $value"; 
    }
    
    function parse($string) {
        return NULL; 
    }
}

class zlw_af_type_string : extends zlw_af_type {
}

class zlw_af_type_binary : extends zlw_af_type {
}

class zlw_af_type_number : extends zlw_af_type {
}

class zlw_af_type_boolean : extends zlw_af_type {
}

class zlw_af_type_dateTime : extends zlw_af_type {
}

class zlw_af_type_float : extends zlw_af_type_number {
}

class zlw_af_type_double : extends zlw_af_type_number {
}

class zlw_af_type_decimal : extends zlw_af_type_number {
}

class zlw_af_type_integer : extends zlw_af_type_decimal {
}

class zlw_af_type_long : extends zlw_af_type_integer {
}

class zlw_af_type_int : extends zlw_af_type_long {
}

class zlw_af_type_short : extends zlw_af_type_int {
}

class zlw_af_type_byte : extends zlw_af_type_short {
}

class zlw_af_type_date : extends zlw_af_type {
}

class zlw_af_type_time : extends zlw_af_type {
}

class zlw_af_variant {
    var $name; 
    var $type; 
    var $constraints; 
    var $value; 
    
    function add_constraint($constraint) {
    }
    
    function check() {
    }
}

function zlw_af_new($name, $type, $typep = NULL) {
    $typemeta = eval("new zlw_af_type_$type($typep)"); 
    $value = new zlw_af_variant($name, $typemeta); 
    
}

?>