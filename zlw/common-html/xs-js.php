<?php

class zlw_xs_javascript extends zlw_xs_base {

    function quote_array($array) {
        $buf = '[';
        foreach ($array as $i)
            $buf .= $this->quote_var($i) . ', ';
        if (strlen($buf) > 1)
            $buf = substr($buf, 0, -2);
        $buf .= ']';
        return $buf;
    }

    function quote_map($map) {
        $buf = '[';
        foreach ($map as $key=>$value)
            $buf .= $this->quote_string($key) . ', '
                . $this->quote_var($value) . ', ';
        if (strlen($buf) > 1)
            $buf = substr($buf, 0, -2);
        $buf .= ']';
        return $buf;
    }
    
    function quote_string($string) {
        $string = addslashes($string);  # \' \" \0 \\
        $string = str_replace("\r", '\r', $string); # \r
        $string = str_replace("\n", '\n', $string); # \n
        return '"' . $string . '"';
    }
    
    function quote_varname($string) {
        return $string;
    }
    
    function decl_var($varname) {
        return 'var ' . $varname . $this->EOS;
    }
    
    function decl_sub($name, $body, $params = null, $ret = null,
                      $throws = null) {
        return parent::decl_sub($name, $body, $params, $ret, $throws);
    }
}

?>