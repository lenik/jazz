<?
/* Common-PHP
 *
 * Database Access
 * 
 * $Id: mysql.php,v 1.1 2005-07-30 12:10:23 dansei Exp $
 * $Log: not supported by cvs2svn $
 */
require 'base.php'; 

class DBI_mysql extends DBI_base {
    var $dialect = 'mysql'; 
    var $link = NULL; 
    
    function connect() {
        if ($this->link)
            error_log("Already connected ($link)"); 
            
        $server = $this->server; 
        $user = $this->user; 
        $password = $this->password; 
        
        $args = func_get_args(); 
        $passargs = array(); 
        switch (func_num_args()) {
        default: 
        case 3: 
            $password = $args[2]; 
        case 2: 
            $user = $args[1]; 
        case 1: 
            $server = $args[0]; 
        case 0: 
            while (TRUE) {
                if (! $server) break; 
                    $passargs[] = $server; 
                if (!$user) break; 
                    $passargs[] = $user; 
                if (!$password) break; 
                    $passargs[] = $password; 
                if (!func_num_args() > 3)
                    $passargs[] = array_slice($args, 3); 
                break; 
            }
        }
        $this->link = call_user_func_array('mysql_connect', $passargs); 
        return $this->link; 
    }
    
    function disconnect() {
        if ($this->link)
            mysql_close($this->link); 
        else
            error_log("Not connected, yet"); 
    }
    
    function affected_rows()        { $args = func_get_args(); return call_user_func_array('mysql_affected_rows',        $args); }
    function change_user()          { $args = func_get_args(); return call_user_func_array('mysql_change_user',          $args); }
    function client_encoding()      { $args = func_get_args(); return call_user_func_array('mysql_client_encoding',      $args); }
    function create_db()            { $args = func_get_args(); return call_user_func_array('mysql_create_db',            $args); }
    function data_seek()            { $args = func_get_args(); return call_user_func_array('mysql_data_seek',            $args); }
    function db_name()              { $args = func_get_args(); return call_user_func_array('mysql_db_name',              $args); }
    function db_query()             { $args = func_get_args(); return call_user_func_array('mysql_db_query',             $args); }
    function drop_db()              { $args = func_get_args(); return call_user_func_array('mysql_drop_db',              $args); }
    function errno()                { $args = func_get_args(); return call_user_func_array('mysql_errno',                $args); }
    function error()                { $args = func_get_args(); return call_user_func_array('mysql_error',                $args); }
    function escape_string()        { $args = func_get_args(); return call_user_func_array('mysql_escape_string',        $args); }
    function fetch_array()          { $args = func_get_args(); return call_user_func_array('mysql_fetch_array',          $args); }
    function fetch_assoc()          { $args = func_get_args(); return call_user_func_array('mysql_fetch_assoc',          $args); }
    function fetch_field()          { $args = func_get_args(); return call_user_func_array('mysql_fetch_field',          $args); }
    function fetch_lengths()        { $args = func_get_args(); return call_user_func_array('mysql_fetch_lengths',        $args); }
    function fetch_object()         { $args = func_get_args(); return call_user_func_array('mysql_fetch_object',         $args); }
    function fetch_row()            { $args = func_get_args(); return call_user_func_array('mysql_fetch_row',            $args); }
    function field_flags()          { $args = func_get_args(); return call_user_func_array('mysql_field_flags',          $args); }
    function field_len()            { $args = func_get_args(); return call_user_func_array('mysql_field_len',            $args); }
    function field_name()           { $args = func_get_args(); return call_user_func_array('mysql_field_name',           $args); }
    function field_seek()           { $args = func_get_args(); return call_user_func_array('mysql_field_seek',           $args); }
    function field_table()          { $args = func_get_args(); return call_user_func_array('mysql_field_table',          $args); }
    function field_type()           { $args = func_get_args(); return call_user_func_array('mysql_field_type',           $args); }
    function free_result()          { $args = func_get_args(); return call_user_func_array('mysql_free_result',          $args); }
    function get_client_info()      { $args = func_get_args(); return call_user_func_array('mysql_get_client_info',      $args); }
    function get_host_info()        { $args = func_get_args(); return call_user_func_array('mysql_get_host_info',        $args); }
    function get_proto_info()       { $args = func_get_args(); return call_user_func_array('mysql_get_proto_info',       $args); }
    function get_server_info()      { $args = func_get_args(); return call_user_func_array('mysql_get_server_info',      $args); }
    function info()                 { $args = func_get_args(); return call_user_func_array('mysql_info',                 $args); }
    function insert_id()            { $args = func_get_args(); return call_user_func_array('mysql_insert_id',            $args); }
    function list_dbs()             { $args = func_get_args(); return call_user_func_array('mysql_list_dbs',             $args); }
    function list_fields()          { $args = func_get_args(); return call_user_func_array('mysql_list_fields',          $args); }
    function list_processes()       { $args = func_get_args(); return call_user_func_array('mysql_list_processes',       $args); }
    function list_tables()          { $args = func_get_args(); return call_user_func_array('mysql_list_tables',          $args); }
    function num_fields()           { $args = func_get_args(); return call_user_func_array('mysql_num_fields',           $args); }
    function num_rows()             { $args = func_get_args(); return call_user_func_array('mysql_num_rows',             $args); }
    function pconnect()             { $args = func_get_args(); return call_user_func_array('mysql_pconnect',             $args); }
    function ping()                 { $args = func_get_args(); return call_user_func_array('mysql_ping',                 $args); }
    function query()                { $args = func_get_args(); return call_user_func_array('mysql_query',                $args); }
    function real_escape_string()   { $args = func_get_args(); return call_user_func_array('mysql_real_escape_string',   $args); }
    function result()               { $args = func_get_args(); return call_user_func_array('mysql_result',               $args); }
    function select_db()            { $args = func_get_args(); return call_user_func_array('mysql_select_db',            $args); }
    function stat()                 { $args = func_get_args(); return call_user_func_array('mysql_stat',                 $args); }
    function tablename()            { $args = func_get_args(); return call_user_func_array('mysql_tablename',            $args); }
    function thread_id()            { $args = func_get_args(); return call_user_func_array('mysql_thread_id',            $args); }
    function unbuffered_query()     { $args = func_get_args(); return call_user_func_array('mysql_unbuffered_query',     $args); }
}
?>