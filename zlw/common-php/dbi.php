<?php
/* Common-PHP
 *
 * Database Access Interface
 */
require '_Phpfixes.php'; 
_RequireOnce('string.php'); 
_RequireOnce('dbi/' . DBI_DIALECT . '.php'); 

class phpx_dbi extends phpx_dbi_base {
    var $_host; 
    var $_user; 
    var $_password; 
    var $_database; 
    var $_persist; 
    var $_link; 
    var $_debug = false; 

	function phpx_dbi($host, $user, $password, $database, 
	        $connect = true, $persist = true, $debug = false) {
        $this->phpx_dbi_base(); 
        $this->_host = $host; 
        $this->_user = $user; 
        $this->_password = $password; 
        $this->_database = $database; 
        $this->_persist = $persist; 
        $this->_debug = $debug; 
        if ($connect)
            $this->_connect(); 
    }
    
    function _connect($persist = NULL) {
        if ($this->_link)
            error_log("Already connected ($link)"); 
        
        if (is_null($persist))
            $persist = $this->_persist; 
        
        $host = $this->_host; 
        $user = $this->_user; 
        $password = $this->_password; 
        
        $args = func_get_args(); 
        $passargs = array(); 
        switch (func_num_args()) {
        default: 
        case 3: 
            $password = $args[2]; 
        case 2: 
            $user = $args[1]; 
        case 1: 
            $host = $args[0]; 
        case 0: 
            while (true) {
                if (! $host) break; 
                    $passargs[] = $host; 
                if (!$user) break; 
                    $passargs[] = $user; 
                if (!$password) break; 
                    $passargs[] = $password; 
                if (!func_num_args() > 3)
                    $passargs[] = array_slice($args, 3); 
                break; 
            }
        }
        
        $this->_debug && logger('Connect: ', join('|', $passargs)); 
        if ($persist) {
            $this->_link = parent::_connect($host, $user, $password); 
        } else {
            $this->_link = parent::_pconnect($host, $user, $password); 
        }
        $this->_debug && logger('Connect-Return: ', $this->_link ? "succeeded, $this->_link" : 'failed'); 
        
        # set connection character set.
        # if ($this->_link)
        #     parent::_query("set names 'utf8'", $this->_link); 
        
        if ($this->_database)
            parent::_select_db($this->_database); 
        
        return $this->_link; 
    }
    
    function _close() {
        if (is_null($this->_link))
            error_log("Already closed"); 
        $this->_debug && logger('Connect-Close: ', $this->_link); 
        parent::_close($this->_link); 
        $this->_link = NULL; 
    }
    
    function _reuse($dbi) {
        if (! is_null($dbi)) {
        	$this->_link = $dbi->_link; 
        	$this->_debug = $dbi->_debug; 
        }
    }
    
    function _query($sql) {
        $this->_debug && logger("SQL: $sql", false); 
        $ret = parent::_query($sql); 
        if ($this->_debug) {
            if ($ret) logger_end(" => succeeded, $ret"); 
            else logger_end(" => failed, ", $this->_error()); 
        }
        return $ret; 
    }
    
    function _row($sql) {
        $result = array(); /* for test if any exist record */
        if ($rs = $this->_query($sql)) {
            $result = $this->_fetch_row($rs); 
            $this->_free_result($rs); 
        }
        return $result; 
    }
    
    function _assoc($sql) {
        $result = array(); /* for test if any exist record */
        if ($rs = $this->_query($sql)) {
            $result = $this->_fetch_assoc($rs); 
            $this->_free_result($rs); 
        }
        return $result; 
    }
    
    function _evaluate($sql) {
        $result = false; 
        $ret = $this->_query($sql); 
        if (is_bool($ret))
            return $ret; 
        if ($row = $this->_fetch_row($ret))
            $result = $row[0]; 
        $this->_free_result($ret); 
        return $result; 
    }
    
    function _update_table($table_name, $map, $primary_key = 'id', $method = NULL) {
        $keys = explode(':', $primary_key); 
        $criteria = ''; 
        
        #  - retrieve the table meta info
        $result = $this->_query("select * from $table_name where 1=2"); 
        if (! $result) {
            error_log("failed to query table $table_name: " . $this->_error()); 
            return false; 
        }
        $nfields = $this->_num_fields($result); 
        for ($i = 0; $i < $nfields; $i++) {
            $field = $this->_field_name($result, $i); 
            if (array_key_exists($field, $map)) {
                $type = $this->_field_type($result, $i); 
                switch ($type) {
                case 'int': 
                    $map[$field] = (int)($map[$field]); 
                    break; 
                case 'real': 
                    $map[$field] = (double)($map[$field]); 
                    break; 
                case 'string': 
                    $map[$field] = "'" . $this->_escape_string($map[$field]) . "'"; 
                    break; 
                }
            }
        }
        $this->_free_result($result); 
        
        # the criteria is needed for auto-choose and update
        if ($method != 'insert') {
            foreach ($keys as $pk) {
                if ($criteria != '')
                    $criteria .= ' and '; 
                $criteria .= "$pk=" . $map[$pk]; 
            }
        }
        
        # auto choose insert or update
        if (is_null($method)) {
            $rows = $this->_evaluate("select count(*) from $table_name where $criteria"); 
            $method = $rows ? 'update' : 'insert'; 
        }
        
        if ($method == 'update') {
            $sets = ''; 
            foreach ($map as $k=>$v) {
                if (is_null($v)) continue; 
                if ($sets != '') {
                    $sets .= ', '; 
                }
                $sets .= "$k=$v"; 
            }
            if ($sets == '') {
                error_log("the fields list to update is empty (table $table_name)"); 
                return false; 
            }
            if (! $this->_query("update $table_name set $sets where $criteria")) {
                error_log("failed to do table-update on $table_name: " . $this->_error()); 
                return false; 
            }
        } else if ($method == 'insert') {
            $fields = ''; 
            $values = ''; 
            foreach ($map as $k=>$v) {
                if (is_null($v)) continue; 
                if ($fields != '') {
                    $fields .= ','; 
                    $values .= ','; 
                }
                $fields .= $k; 
                $values .= $v; 
            }
            if ($fields == '') {
                error_log("the fields list to insert is empty (table $table_name)"); 
                return false; 
            }
            if (! $this->_query("insert into $table_name($fields) values($values)")) {
                error_log("failed to do table-insert on $table_name: " . $this->_error()); 
                return false; 
            }
        } else {
            error_log("Unexpected update method: $method"); 
            return false; 
        }
        return true; 
    }
}

function phpx_connect($persist = true, $debug = false) {
    $dbi = new phpx_dbi(DBI_HOST, DBI_USER, DBI_PASSWORD, DBI_DATABASE, 
        true, $persist, $debug); 
    return $dbi; 
}

?>