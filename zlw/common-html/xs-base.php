<?php

class zlw_xs_base {
    protected $NULL = 'null';
    protected $TRUE = 'true';
    protected $FALSE = 'false'; 
    protected $INF = 'INF';
    protected $NaN = 'NAN'; 
    protected $EOS = "; \n";
    
    function quote_var($var) {
        if (is_scalar($var))
            return $this->quote_scalar($var);
        elseif (is_array($var))
            if (phpx_is_array($var, true))
                return $this->quote_array($var);
            else
                return $this->quote_map($var);
        elseif (is_null($var))
            return $this->NULL;
        else
            die('quote type of ' . gettype($var) . ' is not supported');
    }
    
    function quote_map($map) {
        $buf = 'array(';
        foreach ($map as $key=>$value) {
            $buf .= $this->quote_string($key) . '=>'
                  . $this->quote_var($value) . ', ';
        }
        if (strlen($buf) > 6)
            $buf = substr($buf, 0, -2);
        $buf .= ')';
        return $buf;
    }
    
    function quote_array($array) {
        $buf = 'array(';
        foreach ($array as $i) {
            $buf .= $this->quote_var($i) . ', ';
        }
        if (strlen($buf) > 6)
            $buf = substr($buf, 0, -2);
        $buf .= ')';
        return $buf;
    }
    
    function quote_scalar($scalar) {
        if (is_string($scalar))
            return $this->quote_string($scalar);
        elseif (is_bool($scalar))
            return $this->quote_bool($scalar);
        elseif (is_numeric($scalar))
            return $this->quote_numeric($scalar); 
        else
            return $scalar;
    }
    
    function quote_string($string) {
        $string = addslashes($string);  # \' \" \0 \\
        $string = str_replace("\r", '\r', $string); # \r
        $string = str_replace("\n", '\n', $string); # \n
        $string = str_replace('$', '\$', $string); # \$
        return '"' . $string . '"';
    }
    
    function quote_bool($bool) {
        return $bool ? $this->TRUE : $this->FALSE; 
    }
    
    function quote_numeric($num) {
        if (is_nan($num))
            return $this->NaN; 
        elseif (is_infinite($num))
            return ($num < 0 ? '-' : '') . $this->INF; 
        else
            return $num;
    }

    function quote_varname($name) {
        return '$' . $name;
    }

    function decl_var($name, $type = null, $init = null) {
        $name;
        $type;
        $init; 
        return '';
    }
    
    function decl_sub($name, $body, $params = null, $ret = null,
                      $throws = null) {
        $ret;
        $throws;
        $params2 = '';
        if (isset($params)) {
            foreach ($params as $name=>$type)
                $params2 .= $name . ', ';
            if ($params2 != '')
                $params2 = substr($params2, 0, -2);
        }
        return "function $name($params2) {\n$body\n}\n";
    }
    
    function exp_assign($lhs, $rhs) {
        return $lhs . ' = ' . $rhs; 
    }
    
    function exp_concat($lhs, $rhs) {
        return "($lhs . $rhs)"; 
    }
    
    function vardump($name, $var) {
        return $this->decl_var($name)
            . $this->exp_assign($this->quote_varname($name),
                                $this->quote_var($var))
            . $this->EOS; 
    }
}

?>