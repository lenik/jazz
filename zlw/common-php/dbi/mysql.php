<?
/* Common-PHP
 *
 * Database Access
 * 
 * $Id: mysql.php,v 1.5 2005-08-07 13:02:48 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/08/05 14:34:10  dansei
 * devpack: change to  php-data-object framework
 *
 * Revision 1.3  2005/08/03 14:42:18  dansei
 * dev pack.
 *
 * Revision 1.2  2005/07/31 04:32:49  dansei
 * iter.1-1
 *
 * Revision 1.1  2005/07/30 12:10:23  dansei
 * initial
 *
 */
require 'base.php'; 

class phpx_dbi_mysql extends phpx_dbi_base {
    var $_dialect = 'mysql'; 
    var $_persist = FALSE; 
    
    function _Connect() {
        if ($this->_link)
            error_log("Already connected ($link)"); 
            
        $server = $this->_server; 
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
            $server = $args[0]; 
        case 0: 
            while (true) {
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
        
        $funcname = $this->_persist ? 'mysql_pconnect' : 'mysql_connect'; 
            $this->_debug && logger('Connect: ', join('|', $passargs)); 
        $this->_link = call_user_func_array($funcname, $passargs); 
            $this->_debug && logger('Connect-Return: ', $this->_link ? "succeeded, $this->_link" : 'failed'); 
        
        # set connection character set.
        if ($this->_link)
            mysql_query("set names 'utf8'", $this->_link); 
            
        return $this->_link; 
    }
    
    function _Close() {
        if (is_null($this->_link))
            error_log("Already closed"); 
        $this->_debug && logger('Connect-Close: ', $this->_link); 
        mysql_close($this->_link); 
        $this->_link = NULL; 
    }
    
    function _AffectedRows()    	{ $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_affected_rows',        $args); }
    function _ChangeUser()          { $args = func_get_args(); if (func_num_args() == 3) $args[] = $this->_link; 
                                      return call_user_func_array('mysql_change_user',          $args); }
    function _ClientEncoding()      { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_client_encoding',      $args); }
    function _CreateDb()            { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_create_db',            $args); }
    function _DataSeek()            { $args = func_get_args(); 
                                      return call_user_func_array('mysql_data_seek',            $args); }
    function _DbName()              { $args = func_get_args(); 
                                      return call_user_func_array('mysql_db_name',              $args); }
    function _DbQuery()             { $args = func_get_args(); if (func_num_args() == 2) $args[] = $this->_link; 
                                      return call_user_func_array('mysql_db_query',             $args); }
    function _DropDb()              { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_drop_db',              $args); }
    function _Errno()               { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_errno',                $args); }
    function _Error()               { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_error',                $args); }
    function _EscapeString()        { $args = func_get_args(); 
                                      return call_user_func_array('mysql_escape_string',        $args); }
    function _FetchArray()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_array',          $args); }
    function _FetchAssoc()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_assoc',          $args); }
    function _FetchField()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_field',          $args); }
    function _FetchLengths()        { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_lengths',        $args); }
    function _FetchObject()         { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_object',         $args); }
    function _FetchRow()            { $args = func_get_args(); 
                                      return call_user_func_array('mysql_fetch_row',            $args); }
    function _FieldFlags()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_flags',          $args); }
    function _FieldLen()            { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_len',            $args); }
    function _FieldName()           { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_name',           $args); }
    function _FieldSeek()           { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_seek',           $args); }
    function _FieldTable()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_table',          $args); }
    function _FieldType()           { $args = func_get_args(); 
                                      return call_user_func_array('mysql_field_type',           $args); }
    function _FreeResult()          { $args = func_get_args(); 
                                      return call_user_func_array('mysql_free_result',          $args); }
    function _GetClientInfo()       { $args = func_get_args(); 
                                      return call_user_func_array('mysql_get_client_info',      $args); }
    function _GetHostInfo()         { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_get_host_info',        $args); }
    function _GetProtoInfo()        { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_get_proto_info',       $args); }
    function _GetServerInfo()       { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_get_server_info',      $args); }
    function _Info()                { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_info',                 $args); }
    function _InsertId()            { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_insert_id',            $args); }
    function _ListDbs()             { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_list_dbs',             $args); }
    function _ListFields()          { $args = func_get_args(); $args[] = $this->_link;  #2
                                      return call_user_func_array('mysql_list_fields',          $args); }
    function _ListProcesses()       { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_list_processes',       $args); }
    function _ListTables()          { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_list_tables',          $args); }
    function _NumFields()           { $args = func_get_args(); 
                                      return call_user_func_array('mysql_num_fields',           $args); }
    function _NumRows()             { $args = func_get_args(); 
                                      return call_user_func_array('mysql_num_rows',             $args); }
    function _Ping()                { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_ping',                 $args); }
    function _Query()               { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_query',                $args); }
    function _RealEscapeString()    { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_real_escape_string',   $args); }
    function _Result()              { $args = func_get_args(); 
                                      return call_user_func_array('mysql_result',               $args); }
    function _SelectDb()            { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_select_db',            $args); }
    function _Stat()                { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_stat',                 $args); }
    function _Tablename()           { $args = func_get_args(); 
                                      return call_user_func_array('mysql_tablename',            $args); }
    function _ThreadId()            { $args = func_get_args(); $args[] = $this->_link; 
                                      return call_user_func_array('mysql_thread_id',            $args); }
    function _UnbufferedQuery()     { $args = func_get_args(); $args[] = $this->_link;  #1
                                      return call_user_func_array('mysql_unbuffered_query',     $args); }
}
?>