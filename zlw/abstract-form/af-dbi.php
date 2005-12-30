<?php

require_once dirname(__FILE__) . "/../common-php/dbi.php"; 
require_once dirname(__FILE__) . "/af-model.php"; 

class zlw_af_dbi extends phpx_dbi {
    function zlw_af_dbi($dbi_or_host, $user = NULL, $password = NULL,
                        $database = NULL, $connect = true, $persist = true,
                        $debug = false) {
        if (is_string($dbi_or_host)) {
            $this->phpx_dbi($dbi_or_host, $user, $password, $database, $connect,
                            $persist, $debug);
        } else if (is_subclass_of($dbi_or_host, 'phpx_dbi')) {
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
    
    function _dump_list(&$result, &$list, $format) {
        $format = 'return "' . addslashes($format) . '";'; 
        while ($row = $this->_fetch_array($result)) {
            extract($row);
            $text = eval($format); 
            $object->add($text); 
        }
        return true; 
    }
    
    function _dump_map(&$result, &$map, $format_k = NULL, $format_v = NULL) {
        $format_k = 'return "' . addslashes($format_k) . '";';
        if (is_null($format_v))
            $format_v = $format_k;
        else
            $format_v = 'return "' . addslashes($format_v) . '";'; 
        while ($row = $this->_fetch_array($result)) {
            extract($row);
            $key = eval($format_k); 
            $text = eval($format_v); 
            $object->add($key, $text); 
        }
        return true; 
    }
    
    function _dump_table(&$result, &$table, $keys) {
        assert(! is_null($keys));
        foreach ($keys as $key)
            $is_key[$key] = true;
        
        $n = $this->_num_fields($result);
        for ($i = 0; $i < $n; $i++) {
            $name = $this->_field_name($result, $i); 
            $type = $this->_field_type($result, $i); 
            $fields[] = $name;          # tag-ns? 
            $table->add_column($name, $this->_type_af($type),
                               $is_key[$i] || $is_key[$name]); 
        }
        while ($row = $this->_fetch_array($result)) {
            $table->add($row); 
        }
        return true; 
    }
    
    function _query_view($sql, &$object, $keys = NULL, $format = NULL, 
                         $page = 0, $page_size = -1) {
        $result = $this->_query($sql);
        if (! $result) return false;
        
        assert(is_object($object)); 
        if (is_subclass_of($object, 'zlw_af_list')) {
            $type = 'list';
            if (! is_null($format)) {
                $xml = $this->_dump_list($result, $object, $format);
                $this->_free_result($result);
                return $xml;
            }
        } else if (is_subclass_of($object, 'zlw_af_map')) {
            $type = 'map';
        } else if (is_subclass_of($object, 'zlw_af_table')) {
            $type = 'table';
        }
        
        if (is_string($keys))
            $keys = phpx_list_parse($keys); 
        if (is_null($keys))
            $keys = $this->_default_keys($result);
        
        # for list/map
        if (is_null($format) && $type != 'table') {
            $format = $this->_concat_vars($keys);
            $format_k = $format;        # format_k is always in std format
        }
        
        if ($type == 'list') {
            $xml = $this->_dump_list($result, $object, $format);
        } else if (is_subclass_of($object, 'zlw_af_map')) {
            if (is_null($format_k))     # format_k is always in std format
                $format_k = $this->_concat_vars($keys); 
            $xml = $this->_dump_map($result, $object, $format_k, $format);
        } else if (is_subclass_of($object, 'zlw_af_table')) {
            $xml = $this->_dump_table($result, $object, $keys);
        } else {
            die("Invalid object type: " . get_class($object));
        }
        
        $this->_free_result($result); 
        return true; 
    }
    
    function _query_edit($sql, &$form, $keys = NULL, $init = NULL,
                         $selection = NULL) {
        $result = $this->_query($sql);
        if (! $result) return false;
        
        if (is_string($keys))
            $keys = phpx_list_parse($keys); 
        if (is_null($keys))
            $keys = $this->_default_keys($result);
        
        foreach ($keys as $key)
            $is_key[$key] = true;
        
        $n = $this->_num_fields($result);
        $count = 0; 
        while ($row = $this->_fetch_array($result)) {
            for ($i = 0; $i < $n; $i++) {
                $name = $this->_field_name($result, $i); 
                $type = $this->_field_type($result, $i);
                $len = $this->_field_len($result, $i); 
                $multiline = $this->_type_is_multiline($type); 
                $read_only = $is_key[$i] || $is_key[$name];
                $value = is_null($init[$name]) ? $row[$i] : $init[$name];
                $input = new zlw_af_input($name, $this->_type_af($type),
                                          $value, $multiline, $read_only, 
                                          $len, NULL, $selection[$name]); 
                $form->add_input($input);
            }
            $count++; 
        }
        if ($count == 0) {
            # blank frame
            for ($i = 0; $i < $n; $i++) {
                $flags = $this->_field_flags($result, $i);
                if (strpos($flags, 'auto_increment') !== false)
                    continue;           # skip auto fields
                $name = $this->_field_name($result, $i); 
                $type = $this->_field_type($result, $i);
                $len = $this->_field_len($result, $i); 
                $multiline = $this->_type_is_multiline($type); 
                $input = new zlw_af_input($name, $this->_type_af($type),
                                          $init[$name], $multiline, $read_only, 
                                          $len, NULL, $selection[$name]); 
                $form->add_input($input); 
            }
        }
        $this->_free_result($result); 
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