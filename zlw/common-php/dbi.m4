<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: dbi.m4,v 1.2 2005-07-31 04:32:48 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/07/30 12:10:22  dansei
 * initial
 *
 */
include(`../config/config-database.m4')dnl
defu(`DB_DIALECT', `mysql')dnl

require_once 'dbi/DB_DIALECT.php'; 
require_once 'string.php'; 

class DBI extends `DBI_'DB_DIALECT {
    var $_server = 'DB_SERVER'; 
    var $_user = 'DB_USER'; 
    var $_password = 'DB_PASSWORD'; 
    var $_debug = FALSE; 
    
    function query($sql) {
        if ($this->_debug)
            echo "[", time_format(time()), "] - SQL: ", $sql, "\n"; 
        return parent::query($sql); 
    }
}
?>
