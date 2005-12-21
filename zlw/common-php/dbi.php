<?php

/* Common-PHP
 *
 * Database Access Interface
 */

require_once dirname(__FILE__) . '/string.php'; 
require_once dirname(__FILE__) . '/dbi/' . DBI_DIALECT . '.php'; 

function phpx_dbi_cleanup() {
    global $PHPX_CONNECTED_DBI; 
    if (! is_null($PHPX_CONNECTED_DBI)) {
        foreach ($PHPX_CONNECTED_DBI as $dbi) {
            if ($dbi->_link) $dbi->_close(); 
        }
        $PHPX_CONNECTED_DBI = NULL; 
    }
}
register_shutdown_function('phpx_dbi_cleanup'); 

function phpx_dbi_logger_def($dbi, $type, $message) {
    # Hookable Logger
    global $PHPX_DBI_LOGGER; 
    if (is_null($PHPX_DBI_LOGGER))
        error_log($message); 
    else
        $PHPX_DBI_LOGGER($dbi, $type, $message); 
}

class phpx_dbi extends phpx_dbi_base {
    var $_host; 
    var $_user; 
    var $_password; 
    var $_database; 
    var $_persist; 
    var $_link; 
    var $_transacting = false; 
    var $_debug = false; 
    var $_logger = phpx_dbi_logger_def; 
    
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
    
    function _log() {
        if (! $_debug) 
            return true; 
        $args = func_get_args(); 
        $message = join('', $args); 
        $logger = $this->_logger; 
        $logger($this, E_NOTICE, $message); 
        return true; 
    }
    
    function _warning() {
        $args = func_get_args(); 
        $message = join('', $args); 
        $logger = $this->_logger; 
        $logger($this, E_WARNING, $message); 
        return 0; 
    }
    
    function _fatal() {
        $args = func_get_args(); 
        $message = join('', $args); 
        $logger = $this->_logger; 
        $logger($this, E_ERROR, $message); 
        return false; 
    }
    
    function _connect($persist = NULL) {
        if ($this->_link)
            $this->_warning("Already connected ($link)"); 
        
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
        
        $this->_log('Connect: ' . join('|', $passargs)); 
        if ($persist)
            $this->_link = parent::_connect($host, $user, $password); 
        else
            $this->_link = parent::_pconnect($host, $user, $password); 
        
        if ($this->_link) {
            global $PHPX_CONNECTED_DBI; 
            if (is_null($PHPX_CONNECTED_DBI))
                $PHPX_CONNECTED_DBI = array(); 
            $PHPX_CONNECTED_DBI[] = $this; 
            $this->_log("Connect to $host succeeded: $this->_link"); 
        } else {
            $this->_warning("Connect to $host => failed: " . $this->_error()); 
            return false; 
        }
        
        if ($this->_database) {
            if (! parent::_select_db($this->_database)) {
                $this->_fatal("Cannot select db " . $this->_database); 
                $this->_close(); 
                return false; 
            }
        }
        
        # set connection character set.
        if (! parent::_query("set names 'utf8'")) {
            $this->_fatal("Cannot set encoding to utf-8"); 
            $this->_close(); 
            return false; 
        }
        
        return $this->_link; 
    }
    
    function _close() {
        if (is_null($this->_link))
            $this->_warning("Already closed"); 
        if ($this->_transacting)
            $this->_commit(true); 
        $this->_log('Connect-Close: ' . $this->_link); 
        parent::_close($this->_link); 
        $this->_link = NULL; 
        global $PHPX_CONNECTED_DBI; 
        $index = array_search($this, $PHPX_CONNECTED_DBI); 
        if ($index !== false)
            array_splice($PHPX_CONNECTED_DBI, $index, 1); 
    }
    
    function _reuse($dbi) {
        if (! is_null($dbi)) {
            $this->_fatal("Cannot re-use null DBI. "); 
            return false; 
        }
        if ($this->_transacting) {
            $this->_fatal("This DBI was still in transaction. "); 
            return false; 
        }
        if ($dbi->_transacting) {
            $this->_fatal("The target DBI to re-use was still in transaction. "); 
            return false; 
        }
    	$this->_link = $dbi->_link; 
    	$this->_debug = $dbi->_debug; 
    	$dbi->_link = NULL; 
    	$dbi->_transacting = false; 
    	return true; 
    }
    
    function _query($sql) {
        $this->_log("SQL: $sql"); 
        $ret = parent::_query($sql); 
        if (! $ret)
            $this->_warning("SQL: $sql => failed: ", $this->_error()); 
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
        $ret = $this->_query($sql); 
        if ($ret === false)
            return false; 
        if ($row = $this->_fetch_row($ret))
            $result = $row[0]; 
        else
            $result = false; 
        $this->_free_result($ret); 
        return $result; 
    }
    
    function _evaluates($sql) {
        $ret = $this->_query($sql); 
        if ($ret === false)
            return false; 
        $result = array(); 
        while ($row = $this->_fetch_row($ret))
            $result[] = $row[0]; 
        $this->_free_result($ret); 
        return $result; 
    }
    
    function _begin() {
        if ($this->_transacting) {
            $this->_fatal('transaction has already begun'); 
            return false; 
        }
        if ($_persist) {
            $this->_fatal('transacting in persistency connection is not allowed. '); 
            return false; 
        }
        if (parent::_begin($this->$_link)) {
            $this->_transacting = true; 
            return true; 
        }
        return false; 
    }
    
    function _commit($auto_rollback = false) {
        if (! $this->_transacting) {
            $this->_fatal('transaction has not begun, yet'); 
            return false; 
        }
        if (parent::_commit($this->$_link)) {
            $this->_transacting = false; 
            return true; 
        }
        if ($auto_rollback) {
            if (parent::_rollback($this->$_link)) {
                $this->_transacting = false; 
                return 'rollback'; 
            }
        }
        return false; 
    }
    
    function _rollback() {
        if (! $this->_transacting) {
            $this->_fatal('transaction has not begun, yet'); 
            return false; 
        }
        if (parent::_rollback($this->$_link)) {
            $this->_transacting = false; 
            return true; 
        }
        return false; 
    }
    
    function _update_table($table_name, $map, $primary_key = NULL, $method = NULL) {
        if (! is_null($primary_key)) {
            $keys = explode(':', $primary_key); 
        } else {
            $keys = array(); 
        }
        $criteria = ''; 
        
        #  - retrieve the table meta info
        $result = $this->_query("select * from $table_name where 1=2"); 
        if (! $result) {
            $this->_fatal("failed to query table $table_name"); 
            return false; 
        }
        $nfields = $this->_num_fields($result); 
        $fields = array(); 
        for ($i = 0; $i < $nfields; $i++) {
            $field = $this->_field_name($result, $i); 
            $fields[] = $field; 
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
                if (is_null($primary_key)) {
                    $flags = $this->_field_flags($result, $i); 
                    if (strpos($flags, 'primary_key') !== false)
                        $keys[] = $field; 
                }
            }
        }
        $this->_free_result($result); 
        
        # the criteria is needed for auto-choose and update
        if ($method != 'insert') {
            foreach ($keys as $pk) {
                if (array_key_exists($pk, $map)) {
                    if ($criteria != '')
                        $criteria .= ' and '; 
                    $criteria .= "$pk=" . $map[$pk]; 
                }
            }
            if ($criteria == '') {
                if ($method == 'update') {
                    $this->_fatal("Range is not specified."); 
                    return false; 
                } else {
                    # always insert when range is not specified. 
                    $method = 'insert'; 
                }
            }
        }
        
        # auto choose insert or update
        if (is_null($method)) {
            $rows = $this->_evaluate("select count(*) from $table_name where $criteria"); 
            $method = $rows ? 'update' : 'insert'; 
        }
        
        if ($method == 'update') {
            $sets = ''; 
            foreach ($fields as $k) {
                if (in_array($k, $keys)) continue; 
                $v = $map[$k]; 
                if (is_null($v)) continue; 
                if ($sets != '') {
                    $sets .= ', '; 
                }
                $sets .= "$k=$v"; 
            }
            if ($sets == '') {
                $this->_fatal("the fields list to update is empty (table $table_name)"); 
                return false; 
            }
            if (! $this->_query("update $table_name set $sets where $criteria")) {
                $this->_warning("failed to do table-update on $table_name"); 
                return false; 
            }
        } else if ($method == 'insert') {
            $names = ''; 
            $values = ''; 
            foreach ($fields as $k) {
                $v = $map[$k]; 
                if (is_null($v)) continue; 
                if ($names != '') {
                    $names .= ','; 
                    $values .= ','; 
                }
                $names .= $k; 
                $values .= $v; 
            }
            if ($names == '') {
                $this->_fatal("the fields list to insert is empty (table $table_name)"); 
                return false; 
            }
            if (! $this->_query("insert into $table_name($names) values($values)")) {
                $this->_warning("failed to do table-insert on $table_name"); 
                return false; 
            }
        } else {
            $this->_fatal("Unexpected update method: $method"); 
            return false; 
        }
        return true; 
    }
}

function phpx_connect_fast($debug = false) {
    $dbi = new phpx_dbi(DBI_HOST, DBI_USER, DBI_PASSWORD, DBI_DATABASE, 
        true, true, $debug); 
    return $dbi; 
}

function phpx_connect_safe($debug = false) {
    $dbi = new phpx_dbi(DBI_HOST, DBI_USER, DBI_PASSWORD, DBI_DATABASE, 
        true, false, $debug); 
    return $dbi; 
}

?>