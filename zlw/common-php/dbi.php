<?php

/* Common-PHP
 *
 * Database Access Interface
 */

require_once dirname(__FILE__) . '/lang.php'; 
require_once dirname(__FILE__) . '/error.php'; 
require_once dirname(__FILE__) . '/dbi/' . DBI_DIALECT . '.php'; 
require_once dirname(__FILE__) . '/qmode.php';

function phpx_dbi_cleanup() {
    global $PHPX_CONNECTED_DBI; 
    if (! is_null($PHPX_CONNECTED_DBI)) {
        foreach ($PHPX_CONNECTED_DBI as $id=>$dbi) {
            if ($dbi->_link) $dbi->_close(); 
        }
        $PHPX_CONNECTED_DBI = null; 
    }
}
register_shutdown_function('phpx_dbi_cleanup'); 

global $PHPX_DBI_EM; 
$PHPX_DBI_EM = new phpx_error_manager('PHPX_DBI'); 
$PHPX_DBI_EM->register(); 

class phpx_dbi extends phpx_dbi_base {
    private $_dbi_id; 
    private $_host; 
    private $_user; 
    private $_password; 
    private $_database; 
    private $_persist; 
    public $_link; 
    private $_transacting = false; 
    private $_debug = false; 
    private $_em; 
    
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
    
    function _add_type($type, $summary) {
        global $PHPX_ERROR_FORMAT; 
        if (! preg_match($PHPX_ERROR_FORMAT, $summary, $matches))
            die("Illegal summary syntax: $summary"); 
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
    
    function _info($summary) {
        if (! $this->_debug) return true; 
        error_log($summary); 
    }
    
    function _warn($summary) {
        if ($this->_debug) {
            $summary = $this->_add_type('WARN', $summary); 
            return $this->_em->process($summary, $this); 
        } else {
            error_log($summary); 
        }
    }
    
    function _err($summary) {
        $summary = $this->_add_type('ERR', $summary); 
        return $this->_em->process($summary, $this); 
    }
    
    # @override provider-object._source_status
    function _source_status() {
        if (is_null($this->_link))
            return "Disconnected"; 
        return 'DBI Status [' . $this->_dialect . '/' . $this->_errno() . '] '
            . $this->_error(); 
    }
    
    function _connect($persist = null, $host = null, $user = null, $password = null) {
        if ($this->_link)
            $this->_warn("[CONN] Already connected: $this->_link"); 
        
        if (is_null($persist))
            $persist = $this->_persist; 
        
        if ($host == null)
            $host = $this->_host; 
        if ($user == null)
            $user = $this->_user; 
        if ($password == null)
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
        $this->_link = null; 
        
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
    	$dbi->_link = null; 
    	$dbi->_transacting = false; 
    	return true; 
    }
    
    function _query($sql) {
        $post = null;
        
        if (substr($sql, 0, 1) == '%') {
            $segs = explode('%', $sql, 4);
            $pre = $segs[1];            # pre
            $post = $segs[2];           # post
            $sql = $segs[3];            # body
            if ($pre == '') {           # special mode
                $post = trim($post); 
                $mode = eval("return new phpx_querymode_$post; ");
                $post = $mode->post(); 
                eval($mode->pre()); 
            } else {
                eval($pre);
            }
        }
        
        $this->_info("[SQL] query: $sql"); 
        $ret = parent::_query($sql);
        
        if (isset($post)) eval($post);
        
        if (! $ret)
            $this->_warn("[SQL] query failed: $sql"); 
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
    
    # _evaluate(SQL)                    0 0
    # _evaluate(SQL, null)              : :     [][]
    # _evaluate(SQL, null, null)        : :     [][]
    # _evaluate(SQL, null, row)         row :   []
    # _evaluate(SQL, col, null)         : col   []
    # _evaluate(SQL, 1, 1)              0 0
    # _evaluate(SQL, 1, rows)           * 0     []
    # _evaluate(SQL, cols, 1)           0 *     []
    # _evaluate(SQL, cols, rows)        * *     [][]
    function _evaluate($sql, $cols = false, $rows = false, $mat = false) {
        if (($ret = $this->_query($sql)) === false)
            return false; 
        if (($row = $this->_fetch_row($ret)) === false)
            return false; 
        
        if ($cols === false) {          # 0 0
            $v = $row[0]; 
        } elseif ($cols === null) {
            if ($rows === null || $rows === false) {
                do {                    # : :
                    $v[] = $row; 
                } while ($row = $this->_fetch_row($ret));
            } else {                    # row :
                do {
                    if (! $rows--) break; 
                } while ($row = $this->_fetch_row($ret));
                $v = $row; 
            }
        } elseif ($rows === null || $rows === false) {
            do {                        # : col
                $v[] = $row[$cols]; 
            } while ($row = $this->_fetch_row($ret)); 
        } elseif ($cols == 1 && ! $mat) {
            if ($rows == 1)             # 0 0
                $v = $row[0]; 
            else                        # * 0
                do {
                    $v[] = $row[0]; 
                    if (! --$rows) break; 
                } while ($row = $this->_fetch_row($ret)); 
        } elseif ($rows == 1 && ! $mat) { # 0 *
            $v = array_splice($row, 0, $cols); 
        } elseif ($cols > 0 && $rows > 0) {
            do {
                $v[] = array_splice($row, 0, $cols); 
                if (! --$rows) break; 
            } while ($row = $this->_fetch_row($ret)); 
        } else {
            $v = null; 
        }
        
        $this->_free_result($ret); 
        return $v; 
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
    
    function _default_keys(&$result) {
        $n = $this->_num_fields($result);
        for ($i = 0; $i < $n; $i++) {
            $flags = $this->_field_flags($result, $i);
            if (strpos($flags, 'primary_key') !== false)
                $keys[] = $this->_field_name($result, $i);
        }
        if (is_null($keys))
            $keys[] = $this->_field_name($result, 0);
        return $keys;
    }
    
    function _update_table($table, $values, $keys = null, $method = null) {
        $result = $this->_query("select * from $table where 1=2"); 
        if (! $result)                  # using error cause?
            return $this->_err("[UTBL] Failed to query table: $table"); 
        
        $n = $this->_num_fields($result);
        for ($i = 0; $i < $n; $i++) {
            $name = $this->_field_name($result, $i);
            $type = $this->_field_type($result, $i);
            $names[] = $name; 
            # $types[$name] = $type;
            if (! is_null($values[$name]))
                $values[$name] = $this->_sql_encode($values[$name], $type); 
        }
        
        if (is_string($keys))
            $keys = phpx_list_parse($keys); 
        if (is_null($keys))
            $keys = $this->_default_keys($result);
        foreach ($keys as $key)
            $is_key[$key] = true;
        
        $this->_free_result($result); 
        
        # the criteria is needed for auto-choose and update
        $criteria = '';
        
        if ($method != 'insert') {
            # update or auto-detect
            foreach ($keys as $key) {
                if (array_key_exists($key, $values)) {
                    if ($criteria != '') $criteria .= ' and ';
                    $value = $values[$key]; 
                    $criteria .= "$key=$value"; 
                }
            }
            if ($criteria == '') {
                if ($method == 'update')
                    return $this->_err("[UTBL] Range has not been specified"); 
                else
                    # always insert when range is not specified. 
                    $method = 'insert'; 
            }
        }
        
        if (is_null($method)) {
            # auto-detect
            $rows = $this->_evaluate("select count(*) from $table where $criteria"); 
            $method = $rows ? 'update' : 'insert'; 
        }
        
        if ($method == 'update') {
            foreach ($names as $name) {
                if (in_array($name, $keys)) continue; 
                $value = $values[$name]; 
                if (is_null($value)) continue; 
                if ($S1 != '') $S1 .= ', '; 
                $S1 .= "$name=$value"; 
            }
            if ($S1 == '')
                return $this->_err("[UTBL] The fields list to update is empty: table $table"); 
            if (! $this->_query("update $table set $S1 where $criteria"))
                return $this->_warn("[UTBL] Failed to update table: table $table"); 
        } else if ($method == 'insert') {
            foreach ($names as $name) {
                $value = $values[$name]; 
                if (is_null($value)) continue; 
                if ($S2 != '') {
                    $S2 .= ','; 
                    $S3 .= ','; 
                }
                $S2 .= $name; 
                $S3 .= $value; 
            }
            if ($S2 == '')
                return $this->_err("[UTBL] The fields list to insert is empty: table $table"); 
            if (! $this->_query("insert into $table($S2) values($S3)"))
                return $this->_warn("[UTBL] failed to insert into table: table $table"); 
        } else {
            return $this->_err("[UTBL] Unexpected update method: $method");
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