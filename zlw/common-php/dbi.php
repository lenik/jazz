<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: dbi.php,v 1.2 2005-07-31 02:30:34 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/07/30 12:10:22  dansei
 * initial
 *
 */

require 'dbi/mysql.php'; 

class DBI extends DBI_mysql {
    var $server = 'localhost'; 
    var $user = 'root'; 
    var $password = 'l.'; 
}
?>
