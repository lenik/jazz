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
    
    # format: '$name age($age)'
    function _dump_list($result, $list, $format) {
        $format = 'return "' . addslashes($format) . '";'; 
        while (($row = $this->_fetch_array($result))) {
            extract($row);
            $text = eval($format); 
            $list->add($text); 
        }
    }
    
    function _dump_map($result, $map, $format_k = null, $format_v = null) {
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
    
    function _dump_table($result, $table, $keys) {
        assert(! is_null($keys));
        foreach ($keys as $key)
            $is_key[$key] = true;
        
        $n = $this->_num_fields($result);
        for ($i = 0; $i < $n; $i++) {
            $name = $this->_field_name($result, $i); 
            $type = $this->_field_type($result, $i); 
            # $fields[] = $name;          # tag-ns? 
            $table->add_column($name, $this->_type_af($type),
                               isset($is_key[$i]) || isset($is_key[$name])); 
        }
        while (($row = $this->_fetch_array($result))) {
            $table->add($row); 
        }
    }
    
    function _query_list($list_name, $sqlrc, $format = null, 
                         $methods = 'delete:modify', $hint = null, 
                         $hidden = null) {
        if (is_string($sqlrc)) {
            if (($rs = $this->_query($sqlrc)) === false)
                return null; 
        } else
            $rs = $sqlrc; 

        if ($list_name instanceof zlw_af_list)
            $list = $list_name; 
        else
            $list = new zlw_af_list($list_name, null, null, null, $hidden, 
                                    $methods, $hint);

        # default format = colname:colname:...:colname
        if (is_null($format)) {
            $keys = $this->_default_keys($rs);
            $format = $this->_concat_vars($keys);
        }
        
        $this->_dump_list($rs, $list, $format);
        
        if (is_string($sqlrc))
            $this->_free_result($rs);
        return $list; 
    }
    
    function _query_map($map_name, $sqlrc, $keys = null, $format = null, 
                        $methods = 'delete:modify', $hint = null, 
                        $hidden = null) {
        if (is_string($sqlrc)) {
            if (($rs = $this->_query($sqlrc)) === false)
                return null; 
        } else
            $rs = $sqlrc; 

        if ($map_name instanceof zlw_af_map)
            $map = $map_name; 
        else
            $map = new zlw_af_map($map_name, null, null, null, $hidden, 
                                  $methods, $hint);

        # find (primary-)keys if not specified
        if (is_null($keys))
            $keys = $this->_default_keys($rs);
        elseif (is_string($keys))
            $keys = phpx_list_parse($keys);

        # format-k is in fixed format, and cannot be specified by caller.
        $format_k = $this->_concat_vars($keys);
        
        if (is_null($format))
            $format = $format_k; 
        
        $this->_dump_map($rs, $map, $format_k, $format);
        
        if (is_string($sqlrc))
            $this->_free_result($rs);
        return $map; 
    }
    
    function _query_table($table_name, $sqlrc, $keys = null, 
                          $methods = 'delete:modify', $hint = null, 
                          $hidden = null) {
        if (is_string($sqlrc)) {
            if (($rs = $this->_query($sqlrc)) === false)
                return null; 
        } else
            $rs = $sqlrc; 

        if ($table_name instanceof zlw_af_table)
            $table = $table_name;
        else
            $table = new zlw_af_table($table_name, null, null, null, null, 
                                      $hidden, $methods, $hint);
        
        # find (primary-)keys if not specified
        if (is_null($keys))
            $keys = $this->_default_keys($rs);
        elseif (is_string($keys))
            $keys = phpx_list_parse($keys);
        
        $this->_dump_table($rs, $table, $keys); 
        
        if (is_string($sqlrc))
            $this->_free_result($rs);
        return $table; 
    }
    
    function _query_edit($form_name, $sqlrc, $keys = null, $init = null,
                         $selection = null, $methods = 'update', $hint = null) {
        if (is_string($sqlrc)) {
            if (($rs = $this->_query($sqlrc)) === false)
                return null; 
        } else
            $rs = $sqlrc; 
        
        if ($form instanceof zlw_af_form)
            $form = $form_name;
        else
            $form = new zlw_af_form($form_name, null, null, $methods, $hint);
        
        if (is_string($keys))
            $keys = phpx_list_parse($keys); 
        if (is_null($keys))
            $keys = $this->_default_keys($rs);
        
        foreach ($keys as $key)
            $is_key[$key] = true;
        
        $n = $this->_num_fields($rs);
        $count = 0; 
        while (($row = $this->_fetch_array($rs))) {
            for ($i = 0; $i < $n; $i++) {
                $name = $this->_field_name($rs, $i); 
                $type = $this->_field_type($rs, $i);
                $len = $this->_field_len($rs, $i); 
                $multiline = $this->_type_is_multiline($type); 
                $read_only = $is_key[$i] || $is_key[$name];
                $value = isset($init[$name]) ? $row[$i] : $init[$name];
                $value = new zlw_af_variant($value, $this->_type_af($type), false); 
                $sel = isset($selection) ? $selection[$name] : null; 
                $input = new zlw_af_input($name, null, $value, $multiline, $read_only, 
                                          $len, null, $sel); 
                $form->add($input);
            }
            $count++; 
        }
        if ($count == 0) {
            # blank frame
            for ($i = 0; $i < $n; $i++) {
                $flags = $this->_field_flags($rs, $i);
                if (strpos($flags, 'auto_increment') !== false)
                    continue;           # skip auto fields
                $name = $this->_field_name($rs, $i); 
                $type = $this->_field_type($rs, $i);
                $len = $this->_field_len($rs, $i); 
                $multiline = $this->_type_is_multiline($type); 
                $value = new zlw_af_variant($init[$name], $this->_type_af($type), false); 
                $read_only = false; 
                $sel = isset($selection) ? $selection[$name] : null; 
                $input = new zlw_af_input($name, null, $value, $multiline, $read_only, 
                                          $len, null, $sel); 
                $form->add($input); 
            }
        }
        
        if (is_string($sqlrc))
            $this->_free_result($rs); 
        return $form;
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