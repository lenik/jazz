<?php

/* Common-PHP
 *
 * Database Access Interface
 */

require_once dirname(__FILE__) . '/string.php'; 
require_once dirname(__FILE__) . '/error.php'; 
require_once dirname(__FILE__) . '/dbi/' . DBI_DIALECT . '.php'; 

function phpx_dbi_cleanup() {
    global $PHPX_CONNECTED_DBI; 
    if (! is_null($PHPX_CONNECTED_DBI)) {
        foreach ($PHPX_CONNECTED_DBI as $id=>$dbi) {
            if ($dbi->_link) $dbi->_close(); 
        }
        $PHPX_CONNECTED_DBI = NULL; 
    }
}
register_shutdown_function('phpx_dbi_cleanup'); 

global $PHPX_DBI_EM; 
$PHPX_DBI_EM = new phpx_error_manager(PHPX_DBI); 
$PHPX_DBI_EM->register(); 

class phpx_dbi extends phpx_dbi_base {
    var $_dbi_id; 
    var $_host; 
    var $_user; 
    var $_password; 
    var $_database; 
    var $_persist; 
    var $_link; 
    var $_transacting = false; 
    var $_debug = false; 
    var $_em; 
    
	function phpx_dbi($host, $user, $password, $database, 
	        $connect = true, $persist = true, $debug = false) {
        $this->phpx_dbi_base(); 
        
        global $PHPX_DBI_NEXT_ID; 
        $this->_dbi_id = $PHPX_DBI_NEXT_ID++; 
        
        $this->_host = $host; 
        $this->_user = $user; 
        $this->_password = $password; 
        $this->_database = $database; 
        $this->_persist = $persist; 
        $this->_debug = $debug; 
        
        global $PHPX_DBI_EM; 
        $this->_em = &$PHPX_DBI_EM; 
        
        if ($connect) $this->_connect(); 
    }
    
    function _summary_addtype($type, $args) {
        global $PHPX_ERROR_FORMAT; 
        $str = join('', $args); 
        if (! preg_match($PHPX_ERROR_FORMAT, $str, $matches))
            die("Illegal summary syntax: $str"); 
        if ($name = $matches[1]) {
            if ($matches[2])
                $name .= '.' . $matches[2]; 
            $type .= '.' . $name; 
        }
        if ($type)
            $type = "[$type] "; 
        $text = $matches[3]; 
        if ($detail = $matches[4])
            $text .= ': ' . $detail; 
        return $type . $text; 
    }
    
    function _info() {
        if (! $this->_debug) return true; 
        $args = func_get_args(); 
        $summary = $this->_summary_addtype('INFO', $args); 
        return $this->_em->process($summary, $this); 
    }
    
    function _warn() {
        $args = func_get_args(); 
        $summary = $this->_summary_addtype('WARN', $args); 
        return $this->_em->process($summary, $this); 
    }
    
    function _err() {
        $args = func_get_args(); 
        $summary = $this->_summary_addtype('ERR', $args); 
        return $this->_em->process($summary, $this); 
    }
    
    # @override provider-object.source_status
    function source_status() {
        if (is_null($this->_link))
            return "Disconnected"; 
        return 'DBI Status [' . $this->_dialect . '/' . $this->_errno() . '] '
            . $this->_error(); 
    }
    
    function _connect($persist = NULL, $host = NULL, $user = NULL, $password = NULL) {
        if ($this->_link)
            $this->_warn("[CONN] Already connected: $this->_link"); 
        
        if (is_null($persist))
            $persist = $this->_persist; 
        
        if ($host == NULL)
            $host = $this->_host; 
        if ($user == NULL)
            $user = $this->_user; 
        if ($password == NULL)
            $password = $this->_password; 
        
        $this->_info("[CONN] Connecting: host $host, user $user, password $password"); 
        if ($persist)
            $this->_link = parent::_connect($host, $user, $password); 
        else
            $this->_link = parent::_pconnect($host, $user, $password); 
        
        if ($this->_link) {
            global $PHPX_CONNECTED_DBI; 
            $PHPX_CONNECTED_DBI[$this->_dbi_id] = &$this; 
            $this->_info("[CONN] Connect succeeded: $this->_link"); 
        } else {
            $this->_err("[CONN] Connect failed: " . $this->_error()); 
            return false; 
        }
        
        if ($this->_database) {
            if (! parent::_select_db($this->_database)) {
                $this->_err("[CONN] Cannot select db: $this->_database"); 
                $this->_close(); 
                return false; 
            }
        }
        
        # set connection character set.
        if (! parent::_query("set names 'utf8'")) {
            $this->_err("[CONN] Cannot set encoding to utf-8"); 
            $this->_close(); 
            return false; 
        }
        
        return $this->_link; 
    }
    
    function _close() {
        if (is_null($this->_link))
            $this->_warn("[CONN] Already closed"); 
        if ($this->_transacting)
            $this->_commit(true); 
        $this->_info('[CONN] Disconnecting: ' . $this->_link); 
        parent::_close($this->_link); 
        $this->_link = NULL; 
        
        global $PHPX_CONNECTED_DBI; 
        unset($PHPX_CONNECTED_DBI[$this->_dbi_id]); 
    }
    
    function _reuse($dbi) {
        if (! is_null($dbi)) {
            $this->_err("[DBI] Cannot reuse null DBI"); 
            return false; 
        }
        if ($this->_transacting) {
            $this->_err("[DBI] This DBI was still in transaction. "); 
            return false; 
        }
        if ($dbi->_transacting) {
            $this->_err("[DBI] The target DBI to re-use was still in transaction. "); 
            return false; 
        }
    	$this->_link = $dbi->_link; 
    	$this->_debug = $dbi->_debug; 
    	$dbi->_link = NULL; 
    	$dbi->_transacting = false; 
    	return true; 
    }
    
    function _query($sql) {
        $this->_info("[SQL] query: $sql"); 
        $ret = parent::_query($sql); 
        if (! $ret)
            $this->_warn("[SQL] query failed: ", $this->_error()); 
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
            $this->_err('[TRAN] Transaction has already begun'); 
            return false; 
        }
        if ($_persist) {
            $this->_err('[TRAN] Transacting in persistency connection is not allowed'); 
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
            $this->_err('[TRAN] Transaction has not begun, yet'); 
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
            $this->_err('[TRAN] Transaction has not begun, yet'); 
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
            $this->_err("[UTBL] Failed to query table: $table_name"); 
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
                    $this->_err("[UTBL] Range has not been specified"); 
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
                $this->_err("[UTBL] The fields list to update is empty: table $table_name"); 
                return false; 
            }
            if (! $this->_query("update $table_name set $sets where $criteria")) {
                $this->_warn("[UTBL] Failed to update table: table $table_name"); 
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
                $this->_err("[UTBL] The fields list to insert is empty: table $table_name"); 
                return false; 
            }
            if (! $this->_query("insert into $table_name($names) values($values)")) {
                $this->_warn("[UTBL] failed to insert into table: table $table_name"); 
                return false; 
            }
        } else {
            $this->_err("[UTBL] Unexpected update method: $method"); 
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