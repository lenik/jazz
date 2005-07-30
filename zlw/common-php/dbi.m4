<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: dbi.m4,v 1.1 2005-07-30 12:10:22 dansei Exp $
 * $Log: not supported by cvs2svn $
 */
include(`../config/config-database.m4')dnl
defu(`DB_DIALECT', `mysql')dnl

require 'dbi/DB_DIALECT.php'; 

class DBI extends `DBI_'DB_DIALECT {
    var $server = 'DB_SERVER'; 
    var $user = 'DB_USER'; 
    var $password = 'DB_PASSWORD'; 
}
?>
