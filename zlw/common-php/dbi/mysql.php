<?php
/* Common-PHP
 *
 * Database Access - MySQL Dialect
 */
class phpx_dbi_base {
    public $_dialect = 'mysql'; 
    
    function phpx_dbi_base() {
    }
    
    function _affected_rows()       { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_affected_rows',        $args); }
    function _change_user()         { $args = func_get_args(); if (func_num_args() == 3) $args[] = $this->_link; 
                                      return call_user_func_array('mysql_change_user',          $args); }
    function _client_encoding()     { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_client_encoding',      $args); }
    function _close()               { $args = func_get_args(); 
                                      return call_user_func_array('mysql_close',                $args); }
    function _connect()             { $args = func_get_args(); 
                                      return call_user_func_array('mysql_connect',              $args); }
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
    function _pconnect()            { $args = func_get_args(); 
                                      return call_user_func_array('mysql_pconnect',             $args); }
    function _ping()                { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_ping',                 $args); }
    function _query()               { $args = func_get_args(); $args[] = $this->_link;  #1   
                                       return call_user_func_array('mysql_query',               $args); } 
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
    
    function _begin($link = null) {
        if (is_null($link)) 
            $link = $this->_link; 
        return mysql_query('begin', $link); 
    }
    
    function _commit($link = null) {
        if (is_null($link)) 
            $link = $this->_link; 
        return mysql_query('commit', $link); 
    }
    
    function _rollback($link = null) {
        if (is_null($link)) 
            $link = $this->_link; 
        return mysql_query('rollback', $link); 
    }
    
    function _sql_encode($value, $type) {
        switch ($type) {
        case 'string': 
        case 'text':
        case 'longtext':
            return "'" . $this->_escape_string($value) . "'"; 
        case 'blob':
        case 'longblob':
            return false; 
        }
        return $value; 
    }
}
?>