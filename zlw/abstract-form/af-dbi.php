<?php

require_once dirname(__FILE__) . "/../common-php/dbi.php"; 
require_once dirname(__FILE__) . "/af-object.php"; 

class zlw_af_dbi extends phpx_dbi {
    function zlw_af_dbi($dbi_or_host, $user = null, $password = null,
                        $database = null, $connect = true, $persist = true,
                        $debug = false) {
        if (is_string($dbi_or_host)) {
            $this->phpx_dbi($dbi_or_host, $user, $password, $database, $connect,
                            $persist, $debug);
        } else if (is_a($dbi_or_host, 'phpx_dbi')) {
            phpx_or($this, $dbi_or_host);
        }
    }
    
    function _type_af($sqltype) {
        switch ($sqltype) {
        case 'int':
            return 'integer'; 
        case 'real':
            return 'decimal'; 
        case 'blob':
            return 'binary';
        case 'text':
        case 'longtext': 
            return 'string';
        case 'blob':
        case 'longblob':
            return 'binary'; 
        }
        return $sqltype;
    }
    
    function _type_is_multiline($sqltype) {
        if ($sqltype == 'text')
            return true; 
        return false;
    }
    
    function _concat_vars($names) {
        foreach ($names as $name)
            $vars[] = '${' . $name . '}'; 
        return phpx_list_format($vars);
    }
    
    function _dump_list($result, &$list, $format) {
        $format = 'return "' . addslashes($format) . '";'; 
        while (($row = $this->_fetch_array($result))) {
            extract($row);
            $text = eval($format); 
            $list->add($text); 
        }
    }
    
    function _dump_map($result, &$map, $format_k = null, $format_v = null) {
        $format_k = 'return "' . addslashes($format_k) . '";';
        if (is_null($format_v))
            $format_v = $format_k;
        else
            $format_v = 'return "' . addslashes($format_v) . '";'; 
        while (($row = $this->_fetch_array($result))) {
            extract($row);
            $key = eval($format_k); 
            $text = eval($format_v); 
            $map->add($key, $text); 
        }
    }
    
    function _dump_table($result, &$table, $keys) {
        assert(! is_null($keys));
        foreach ($keys as $key)
            $is_key[$key] = true;
        
        $n = $this->_num_fields($result);
        for ($i = 0; $i < $n; $i++) {
            $name = $this->_field_name($result, $i); 
            $type = $this->_field_type($result, $i); 
            # $fields[] = $name;          # tag-ns? 
            $table->add_column($name, $this->_type_af($type),
                               $is_key[$i] || $is_key[$name]); 
        }
        while (($row = $this->_fetch_array($result))) {
            $table->add($row); 
        }
    }
    
    function _query_view($sqlrc, $object, $keys = null, $format = null) {
        if (is_string($sqlrc)) {
            $sqlrc = $this->_query($sqlrc);
            if ($sqlrc === false) return false;
        }
        
        if (is_a($object, 'zlw_af_list')) {
            $type = 'list';
            if (! is_null($format)) {
                $this->_dump_list($sqlrc, $object, $format);
                $this->_free_result($sqlrc);
                return true;
            }
        } else if (is_a($object, 'zlw_af_map')) {
            $type = 'map';
        } else if (is_a($object, 'zlw_af_table')) {
            $type = 'table';
        }
        
        if (is_string($keys))
            $keys = phpx_list_parse($keys); 
        if (is_null($keys))
            $keys = $this->_default_keys($sqlrc);
        
        # for list/map
        if (is_null($format) && $type != 'table') {
            $format = $this->_concat_vars($keys);
            $format_k = $format;        # format_k is always in std format
        }
        
        if ($type == 'list') {
            $this->_dump_list($sqlrc, $object, $format);
        } elseif ($type == 'map') {
            if (is_null($format_k))     # format_k is always in std format
                $format_k = $this->_concat_vars($keys); 
            $this->_dump_map($sqlrc, $object, $format_k, $format);
        } elseif ($type == 'table') {
            $this->_dump_table($sqlrc, $object, $keys);
        } else {
            die("Invalid object type: " . get_class($object));
        }
        
        $this->_free_result($sqlrc); 
        return true; 
    }
    
    function _query_edit($sqlrc, &$form, $keys = null, $init = null,
                         $selection = null) {
        if (is_string($sqlrc)) {
            $sqlrc = $this->_query($sqlrc);
            if ($sqlrc === false) return false;
        }
        
        if (is_string($keys))
            $keys = phpx_list_parse($keys); 
        if (is_null($keys))
            $keys = $this->_default_keys($sqlrc);
        
        foreach ($keys as $key)
            $is_key[$key] = true;
        
        $n = $this->_num_fields($sqlrc);
        $count = 0; 
        while (($row = $this->_fetch_array($sqlrc))) {
            for ($i = 0; $i < $n; $i++) {
                $name = $this->_field_name($sqlrc, $i); 
                $type = $this->_field_type($sqlrc, $i);
                $len = $this->_field_len($sqlrc, $i); 
                $multiline = $this->_type_is_multiline($type); 
                $read_only = $is_key[$i] || $is_key[$name];
                $value = is_null($init[$name]) ? $row[$i] : $init[$name];
                $value = new zlw_af_variant($value, $this->_type_af($type), false); 
                $input = new zlw_af_input($name, null, $value, $multiline, $read_only, 
                                          $len, null, $selection[$name]); 
                $form->add_input($input);
            }
            $count++; 
        }
        if ($count == 0) {
            # blank frame
            for ($i = 0; $i < $n; $i++) {
                $flags = $this->_field_flags($sqlrc, $i);
                if (strpos($flags, 'auto_increment') !== false)
                    continue;           # skip auto fields
                $name = $this->_field_name($sqlrc, $i); 
                $type = $this->_field_type($sqlrc, $i);
                $len = $this->_field_len($sqlrc, $i); 
                $multiline = $this->_type_is_multiline($type); 
                $value = new zlw_af_variant($init[$name], $this->_type_af($type), false); 
                $input = new zlw_af_input($name, null, $value, $multiline, $read_only, 
                                          $len, null, $selection[$name]); 
                $form->add_input($input); 
            }
        }
        $this->_free_result($sqlrc); 
        return true;
    }
}

function zlw_af_connect_fast($debug = false) {
    $dbi = new zlw_af_dbi(DBI_HOST, DBI_USER, DBI_PASSWORD, DBI_DATABASE, 
        true, true, $debug); 
    return $dbi; 
}

function zlw_af_connect_safe($debug = false) {
    $dbi = new zlw_af_dbi(DBI_HOST, DBI_USER, DBI_PASSWORD, DBI_DATABASE, 
        true, false, $debug); 
    return $dbi; 
}

?>