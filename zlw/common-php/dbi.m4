<?
include(`../config/config-database.m4')dnl
defu(`DB_DIALECT', `mysql')dnl
/*
 * __ORIGINAL_FILE__
 */

/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: dbi.m4,v 1.4 2005-08-05 06:03:31 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/08/03 14:42:17  dansei
 * dev pack.
 *
 * Revision 1.2  2005/07/31 04:32:48  dansei
 * iter.1-1
 *
 * Revision 1.1  2005/07/30 12:10:22  dansei
 * initial
 *
 */
require '_Phpfixes.php'; 
_RequireOnce('string.php'); 
_RequireOnce('dbi/DB_DIALECT.php'); 
_RequireOnce('dbi/data_object.php'); 

class DBI extends `DBI_'DB_DIALECT {
    var $_server = 'DB_SERVER'; 
    var $_user = 'DB_USER'; 
    var $_password = 'DB_PASSWORD'; 
    
    function query($sql) {
        $this->_debug && logger("SQL: $sql", false); 
        $ret = parent::query($sql); 
        
        if ($this->_debug) {
            if ($ret) logger_end(" => succeeded, $ret"); 
            else logger_end(" => failed, ", $this->error()); 
        }
        return $ret; 
    }
}
?>
