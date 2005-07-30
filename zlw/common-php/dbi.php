<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: dbi.php,v 1.1 2005-07-30 12:10:23 dansei Exp $
 * $Log: not supported by cvs2svn $
 */

require 'dbi/mysql.php'; 

class DBI extends DBI_mysql {
    var $server = 'localhost'; 
    var $user = 'root'; 
    var $password = 'l.'; 
}
?>
