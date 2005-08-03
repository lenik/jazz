dnl M4 Utilities
dnl
dnl DDL Helper - MySQL Dialects
dnl
dnl $Id: ddl-mysql.m4,v 1.3 2005-08-05 06:03:30 dansei Exp $
dnl $Log: not supported by cvs2svn $
dnl Revision 1.2  2005/07/30 05:54:57  dansei
dnl add type UTIME
dnl
dnl Revision 1.1  2005/07/29 14:21:45  dansei
dnl initial
dnl
dnl
include(`m4-ext.m4')dnl
define(`UUID', `char(32)')dnl
dnl
dnl auto-id key
define(`AIK', `ifelse($1, `', `bigint', `int($1)') `auto_increment primary key'')dnl
dnl
dnl auto-id key, no reference. 
define(`AIK_LEAF', `ifelse($1, `', `bigint', `int($1)') `auto_increment primary key'')dnl
dnl
dnl Multi-lingual string
dnl UTF-8: define(`STR', `ifelse($1, `', `varchar(255)', `char(eval(3*$1))')')dnl
define(`MSTR', `ifelse($1, `', `varchar(255)', `char($1)')')dnl
dnl
define(`DWORD', `int')dnl
define(`UTIME', `int')dnl
dnl
define(`EMAIL', `char(35)')dnl
define(`SHA1', `char(40)')dnl
