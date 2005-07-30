dnl M4 Utilities
dnl
dnl DDL Helper - MySQL Dialects
dnl
dnl $Id: ddl-mysql.m4,v 1.2 2005-07-30 05:54:57 dansei Exp $
dnl $Log: not supported by cvs2svn $
dnl Revision 1.1  2005/07/29 14:21:45  dansei
dnl initial
dnl
dnl
include(`m4-ext.m4')dnl
define(`UUID', `char(32)')dnl
define(`UTF8', `ifelse($1, `', `varchar(255)', `char(eval(3*$1))')')dnl
define(`SHA1', `char(40)')dnl
define(`EMAIL', `char(35)')dnl
define(`DWORD', `int')dnl
define(`UTIME', `int')dnl
