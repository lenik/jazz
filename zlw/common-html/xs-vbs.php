<?php

class zlw_xs_vbscript extends zlw_xs_base {
    protected $TRUE = 'True';
    protected $FALSE = 'False'; 
    protected $NULL = 'Null'; 
    protected $EOS = "\n";
    
    function quote_array($array) {
        $buf = 'Array(';
        foreach ($array as $i)
            $buf .= $this->quote_var($i) . ', ';
        if (strlen($buf) > 6)
            $buf = substr($buf, 0, -2);
        $buf .= ')';
        return $buf;
    }
    
    function quote_map($map) {
        $buf = 'Array(';
        foreach ($map as $key=>$value)
            $buf .= $this->quote_string($key) . ', '
                . $this->quote_var($value) . ', ';
        if (strlen($buf) > 1)
            $buf = substr($buf, 0, -2);
        $buf .= ')';
        return $buf;
    }
    
    function quote_string($string) {
        $string = str_replace('"', '""', $string); # \"
        $string = str_replace("\r\n", '" + vbNewLine + "', $string); # \r\n
        $string = str_replace("\n", '" + vbNewLine + "', $string); # \n
        return '"' . $string . '"';
    }
    
    function quote_varname($string) {
        return $string;
    }
    
    function decl_var($varname) {
        return 'Dim ' . $varname . $this->EOS;
    }
    
    function decl_sub($name, $body, $params = null, $ret = null,
                      $throws = null) {
        $throws; 
        $sub_kw = isset($ret) ? 'Function' : 'Sub';
        $params2 = '';
        if (isset($params)) {
            foreach ($params as $name=>$type)
                $params2 .= $name . ', ';
            if ($params2 != '')
                $params2 = substr($params2, 0, -2);
        }
        return "$sub_kw $name($params2)\n$body\nEnd $sub_kw\n"; 
    }
}

?>