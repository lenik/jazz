<?
/* Common-PHP
 *
 * Database Access
 */
class phpx_dbi {
    var $_dialect = 'mysql'; 
    var $_host; 
    var $_user; 
    var $_password; 
    var $_database; 
    var $_persist; 
    var $_debug = false; 
    var $_link; 
    
	function phpx_dbi($host, $user, $password, $database, 
	        $connect = true, $persist = true, $debug = false) {
        $this->_host = $host; 
        $this->_user = $user; 
        $this->_password = $password; 
        $this->_database = $database; 
        $this->_persist = $persist; 
        $this->_debug = $debug; 
        if ($connect)
            $this->_connect(); 
    }
    
    function _reuse($dbi) {
        if (! is_null($dbi)) {
        	$this->_link = $dbi->_link; 
        	$this->_debug = $dbi->_debug; 
        }
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
        
        $funcname = $persist ? 'mysql_pconnect' : 'mysql_connect'; 
        $this->_debug && logger('Connect: ', join('|', $passargs)); 
        $this->_link = call_user_func_array($funcname, $passargs); 
        $this->_debug && logger('Connect-Return: ', $this->_link ? "succeeded, $this->_link" : 'failed'); 
        
        # set connection character set.
        if ($this->_link)
            mysql_query("set names 'utf8'", $this->_link); 
        
        if ($this->_database)
            mysql_select_db($this->_database); 
            
        return $this->_link; 
    }
    
    function _close() {
        if (is_null($this->_link))
            error_log("Already closed"); 
        $this->_debug && logger('Connect-Close: ', $this->_link); 
        mysql_close($this->_link); 
        $this->_link = NULL; 
    }
    
    function _affected_rows()       { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_affected_rows',        $args); }
    function _change_user()         { $args = func_get_args(); if (func_num_args() == 3) $args[] = $this->_link; 
                                      return call_user_func_array('mysql_change_user',          $args); }
    function _client_encoding()     { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_client_encoding',      $args); }
    function _create_db()           { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_create_db',            $args); }
    function _data_seek()           { $args = func_get_args(); 
                                      return call_user_func_array('mysql_data_seek',            $args); }
    function _db_name()             { $args = func_get_args(); 
                                      return call_user_func_array('mysql_db_name',              $args); }
    function _db_query()            { $args = func_get_args(); if (func_num_args() == 2) $args[] = $this->_link; 
                                      return call_user_func_array('mysql_db_query',             $args); }
    function _drop_db()             { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_drop_db',              $args); }
    function _errno()               { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_errno',                $args); }
    function _error()               { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_error',                $args); }
    function _escape_string()       { $args = func_get_args(); 
                                      return call_user_func_array('mysql_escape_string',        $args); }
    function _fetch_array()         { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_array',          $args); }
    function _fetch_assoc()         { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_assoc',          $args); }
    function _fetch_field()         { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_field',          $args); }
    function _fetch_lengths()       { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_lengths',        $args); }
    function _fetch_object()        { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_object',         $args); }
    function _fetch_row()           { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_row',            $args); }
    function _field_flags()         { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_flags',          $args); }
    function _field_len()           { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_len',            $args); }
    function _field_name()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_name',           $args); }
    function _field_seek()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_seek',           $args); }
    function _field_table()         { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_table',          $args); }
    function _field_type()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_type',           $args); }
    function _free_result()         { $args = func_get_args(); 
                                      return call_user_func_array('mysql_free_result',          $args); }
    function _get_client_info()     { $args = func_get_args(); 
                                      return call_user_func_array('mysql_get_client_info',      $args); }
    function _get_host_info()       { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_get_host_info',        $args); }
    function _get_proto_info()      { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_get_proto_info',       $args); }
    function _get_server_info()     { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_get_server_info',      $args); }
    function _info()                { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_info',                 $args); }
    function _insert_id()           { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_insert_id',            $args); }
    function _list_dbs()            { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_list_dbs',             $args); }
    function _list_fields()         { $args = func_get_args(); $args[] = $this->_link;  #2
                                      return call_user_func_array('mysql_list_fields',          $args); }
    function _list_processes()      { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_list_processes',       $args); }
    function _list_tables()         { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_list_tables',          $args); }
    function _num_fields()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_num_fields',           $args); }
    function _num_rows()            { $args = func_get_args(); 
                                      return call_user_func_array('mysql_num_rows',             $args); }
    function _ping()                { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_ping',                 $args); }
    function _real_escape_string()  { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_real_escape_string',   $args); }
    function _result()              { $args = func_get_args(); 
                                      return call_user_func_array('mysql_result',               $args); }
    function _select_db()           { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_select_db',            $args); }
    function _stat()                { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_stat',                 $args); }
    function _tablename()           { $args = func_get_args(); 
                                      return call_user_func_array('mysql_tablename',            $args); }
    function _thread_id()           { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_thread_id',            $args); }
    function _unbuffered_query()    { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_unbuffered_query',     $args); }
    
    function _query($sql) {
        $this->_debug && logger("SQL: $sql", false); 
        $ret = mysql_query($sql, $this->_link); 
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
}
?>