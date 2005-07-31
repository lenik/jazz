<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: dbi.php,v 1.3 2005-07-31 04:32:48 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/07/30 12:10:22  dansei
 * initial
 *
 */

require_once 'dbi/mysql.php'; 
require_once 'string.php'; 

class DBI extends DBI_mysql {
    var $_server = 'localhost'; 
    var $_user = 'root'; 
    var $_password = 'l.'; 
    var $_debug = FALSE; 
    
    function query($sql) {
        if ($this->_debug)
            echo "[", time_format(time()), "] - SQL: ", $sql, "\n"; 
        return parent::query($sql); 
    }
}
?>
