<?php

/* Common-PHP
 *
 * Database Access Interface
 */

require_once dirname(__FILE__) . '/lang.php'; 
require_once dirname(__FILE__) . '/error.php'; 
require_once dirname(__FILE__) . '/dbi/' . DBI_DIALECT . '.php'; 
require_once dirname(__FILE__) . '/sqlex.php';

function phpx_dbi_cleanup() {
    global $PHPX_CONNECTED_DBI; 
    if (isset($PHPX_CONNECTED_DBI)) {
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
    protected $_host; 
    protected $_user; 
    protected $_password; 
    protected $_database; 
    private $_persist; 
    public $_link; 
    protected $_transacting = false; 
    protected $_debug = false; 
    protected $_em;
    protected $_sqlex; 
    
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
    
    function _reuse($dbi) {
        if (is_null($dbi)) {
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
        if (! parent::_fast_query("set names 'utf8'")) {
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

    function _sql_set($array, $quote = false) {
        $sql = '';
        if ($quote) {
            foreach ($array as $i) {
                if ($sql != '')
                    $sql .= ', ';
                $sql .= "'" . $this->_escape_string($i) . "'"; 
            }
        } else {
            foreach ($array as $i) {
                if ($sql != '')
                    $sql .= ', ';
                $sql .= $i; 
            }
        }
        return $sql; 
    }
    
    # ^% pre(..) % post(..) % body
    # ^%% class(..) % body
    function _parse_sqlex(&$sql) {
        $post = null;
        if (substr($sql, 0, 1) == '%') {
            $segs = explode('%', $sql, 4);
            $pre = $segs[1];            # pre
            $post = $segs[2];           # post
            $sql = $segs[3];            # body
            if ($pre == '') {           # %% class
                $sqlex = phpx_sqlex_get($post, $this); 
                $sql = $sqlex->pre($sql);
                $this->_sqlex = $sqlex; 
                return $sqlex; 
            } else {                    # % pre % post
                eval($pre);
            }
        }
        return $post;
    }
    
    function _query($sql) {
        $post = $this->_parse_sqlex($sql);
        
        $this->_info("[SQL] query: $sql"); 
        $rs = parent::_query($sql);
        
        if ($rs === false)
            $this->_warn("[SQL] query failed: $sql");
        elseif ($rs === true)
            return null; 
        elseif (isset($post)) {
            if (is_string($post))
                eval($post);
            elseif (is_object($post))
                $rs = $post->post($rs); 
        }
        return $rs; 
    }
    
    function _fast_query($sql) {
        $post = $this->_parse_sqlex($sql);
        
        $this->_info("[SQL] fast query: $sql"); 
        $rs = parent::_fast_query($sql);
        
        if ($rs === false)
            $this->_warn("[SQL] fast query failed: $sql");
        elseif ($rs === true)
            return null; 
        elseif (isset($post)) {
            if (is_string($post))
                eval($post);
            elseif (is_object($post))
                $rs = $post->post($rs); 
        }
        return $rs; 
    }
    
    function _test($sql, $max_rows = 3) {
        $rs = $this->_fast_query($sql);
        if ($rs === false)
            return false;
        $appx_rows = 0;
        while ($appx_rows < $max_rows) {
            $row = $this->_fetch_row($rs);
            if ($row === false)
                break;
            $appx_rows++;
        }
        $this->_free_result($rs);
        return $appx_rows; 
    }
    
    function _update($sql) {
        $post = $this->_parse_sqlex($sql);
        
        $this->_info("[SQL] update: $sql"); 
        $ret = parent::_fast_query($sql);
        
        if ($ret === false)
            $this->_warn("[SQL] update failed: $sql");
        
        if ($ret === true)
            $ret = $this->_affected_rows();
        else
            $ret = 0;
        
        if (isset($post)) {
            if (is_string($post))
                eval($post);
            elseif (is_object($post))
                $ret = $post->post($ret); 
        }
        return $ret;
    }
    
    function _execute($sql) {
        $post = _parse_sqlex($sql);
        
        $this->_info("[SQL] execute: $sql"); 
        $ret = parent::_query($sql);
        
        if ($ret === false)
            $this->_warn("[SQL] execute failed: $sql");

        if (isset($post)) {
            if (is_string($post))
                eval($post);
            elseif (is_object($post))
                $ret = $post->post($ret); 
        }
        return $ret;
    }
    
    # _eval(SQL)                   	0 0
    # _eval(SQL, null)              : :     [][]
    # _eval(SQL, null, null)        : :     [][]
    # _eval(SQL, null, row)         row :   []
    # _eval(SQL, col, null)         : col   []
    # _eval(SQL, 1, 1)              0 0
    # _eval(SQL, 1, rows)           * 0     []
    # _eval(SQL, cols, 1)           0 *     []
    # _eval(SQL, cols, rows)        * *     [][]
    function _eval($sql, $cols = false, $rows = false, $mat = false) {
        if (($rs = $this->_fast_query($sql)) === false)
            return false; 
        if (($row = $this->_fetch_row($rs)) === false)
            return false; 

        $v = null;
        
        if ($cols === false) {          # (def, def)    0 0
            $v = $row[0]; 
        } elseif ($cols === null) {
            if ($rows === null || $rows === false) { # (nul, nul) : :
                if ($this->_num_fields($rs) == 1 && ! $mat) {
                    do {
                        $v[] = $row[0]; 
                    } while ($row = $this->_fetch_row($rs));
                } else {
                    do {
                        $v[] = $row; 
                    } while ($row = $this->_fetch_row($rs));
                }
                if (count($v) == 1 && ! $mat)
                    $v = $v[0];
            } else {                    # (nul, 0..N)   row :
                while ($rows--) {
                    $row = $this->_fetch_row($rs);
                    if ($row === false) break; 
                }
                if ($row !== false)
                    $v = $row; 
            }
        } elseif ($rows === null || $rows === false) {
            do {                        # (0..N, nul)  	: col
                $v[] = $row[$cols]; 
            } while ($row = $this->_fetch_row($rs)); 
        } elseif ($cols == 1 && ! $mat) {
            if ($rows == 1)             # (1, 1)    	0 0
                $v = $row[0]; 
            else                        # (1, 2..N)		* 0
                do {
                    $v[] = $row[0];
                    $row = $this->_fetch_row($rs); 
                    if ($row === false) break; 
                } while (--$rows);      # XXX: fetch 1 extra rows than required
        } elseif ($rows == 1 && ! $mat) { # (2..N, 1)  	0 *
            $v = array_splice($row, 0, $cols); 
        } elseif ($cols > 0 && $rows > 0) { # (1..N, 1..N) * *
            do {
                $v[] = array_splice($row, 0, $cols); 
                $row = $this->_fetch_row($rs);
                if ($row === false) break; 
            } while (--$rows); 
        } elseif (($cols == 0 && $rows >= 0) || ($cols > 0 && $rows == 0)) {
            $v = array();
        } else {
            die("Unexpected");
        }
        
        $this->_free_result($rs); 
        return $v; 
    }
    
    function _eval_set($sql, $cols = false, $rows = false, $mat = false) {
        $v = $this->_eval($sql, $cols, $rows, $mat);
        if ($v == null)
            return '';
        assert(is_array($v));
        if (count($v) == 0)
            return '';
        if (! is_array($v[0]))
            return $this->_sql_set($v); 
        foreach ($v as $i)
            $sets[] = $this->_sql_set($i);
        return $sets; 
    }
    
    function _default_keys($tbl_rs) {
        if (is_string($tbl_rs)) {
            $result = $this->_fast_query("select * from $tbl_rs where 1=2"); 
            if (! $result)
                return $this->_err("[UTBL] Failed to get structure: table $tbl_rs"); 
        } else {
            $result = $tbl_rs; 
        }
        $n = $this->_num_fields($result);
        for ($i = 0; $i < $n; $i++) {
            $flags = $this->_field_flags($result, $i);
            if (strpos($flags, 'primary_key') !== false)
                $keys[] = $this->_field_name($result, $i);
        }
        if (is_null($keys))
            $keys[] = $this->_field_name($result, 0);
        if (is_string($tbl_rs))
            $this->_free_result($result); 
        return $keys;
    }

    function _where_keys($tbl_rs, $values, $keys = null) {
        if (is_string($tbl_rs)) {
            $result = $this->_fast_query("select * from $tbl_rs where 1=2"); 
            if (! $result)
                return $this->_err("[UTBL] Failed to get structure: table $tbl_rs"); 
        } else {
            $result = $tbl_rs; 
        }
        $n = $this->_num_fields($result);
        for ($i = 0; $i < $n; $i++) {
            $name = $this->_field_name($result, $i);
            $type = $this->_field_type($result, $i); 
            $flags = $this->_field_flags($result, $i);
            
            # using default keys ?
            if (is_null($keys) && strpos($flags, 'primary_key') !== false)
                $def_keys[] = $this->_field_name($result, $i);
            
            # escape field value by field type
            if (isset($values[$name]))
                $sql_values[$name] = $this->_sql_encode($values[$name], $type);
        }
        if (is_null($keys)) {           # using default primary keys
            $keys = $def_keys;
            if (is_null($keys))         # if no primary key defined
                $keys[] = $this->_field_name($result, 0);
        }
        if (is_string($tbl_rs))
            $this->_free_result($result);
        
        $sql = '';
        foreach ($keys as $key) {
            if ($sql != '') $sql .= ' and '; 
            $sql .= $key . '=' . $sql_values[$key]; 
        }
        return $sql;
    }
    
    function _update_table($table, $values, $keys = null, $method = null) {
        $result = $this->_fast_query("select * from $table where 1=2"); 
        if (! $result)                  # using error cause?
            return $this->_err("[UTBL] Failed to get structure: table $table"); 
        
        $n = $this->_num_fields($result);
        for ($i = 0; $i < $n; $i++) {
            $name = $this->_field_name($result, $i);
            $type = $this->_field_type($result, $i);
            $names[] = $name; 
            # $types[$name] = $type;
            if (! is_null($values[$name]))
                $sql_values[$name] = $this->_sql_encode($values[$name], $type); 
        }
        
        if (is_string($keys))
            $keys = phpx_list_parse($keys); 
        if (is_null($keys))
            $keys = $this->_default_keys($result);
            
        foreach ($keys as $key)
            $is_key[$key] = true;
        
        $this->_free_result($result); 
        
        # the where_keys is needed for auto-choose and update
        $where_keys = $this->_where_keys($table, $values, $keys); 
        
        if ($where_keys == '') {
            if ($method != 'insert') {  # update, delete, <null for detect> ?
                if ($method == 'update' || $method == 'delete')
                    return $this->_err("[UTBL] key has not been specified"); 
                else
                    # always do insert if no where-keys
                    $method = 'insert'; 
            }
        }
        
        if (is_null($method)) {
            # auto-detect
            $anyrows = $this->_test("select * from $table where $where_keys"); 
            if ($anyrows) {
                if ($sql_values != null)
                    $method = 'update'; 
                else
                    $method = 'delete'; 
            } else {
                $method = 'insert'; 
            }
        }
        
        if ($method == 'update') {
            foreach ($names as $name) {
                if (in_array($name, $keys)) continue; 
                $value = $sql_values[$name]; 
                if (is_null($value)) continue; 
                if ($S1 != '') $S1 .= ', '; 
                $S1 .= "$name=$value"; 
            }
            if ($S1 == '')
                return $this->_err("[UTBL] The fields list to update is empty: table $table"); 
            if (! $this->_update("update $table set $S1 where $where_keys"))
                return $this->_warn("[UTBL] Failed to update table: table $table"); 
        } elseif ($method == 'insert') {
            foreach ($names as $name) {
                $value = $sql_values[$name]; 
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
            if (! $this->_update("insert into $table($S2) values($S3)"))
                return $this->_warn("[UTBL] failed to insert into table: table $table"); 
        } elseif ($method == 'delete') {
            if (! $this->_update("delete from $table where $where_keys"))
                return $this->_warn("[UTBL] failed to delete from table: table $table"); 
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