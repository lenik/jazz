dnl M4 Utilities
dnl
dnl PDO Implement Helper
dnl
dnl $Id: pdo-member.m4,v 1.1 2005-08-05 14:34:11 dansei Exp $
dnl $Log: not supported by cvs2svn $
dnl
include(`m4-ext.m4')dnl
dnl ADD_PREFIXES(prefix, args...)
define(`ADD_PREFIXES', 
    `ifelse($#, 0, , $#, 1, , $#, 2, ``$1$2'', 
        ``$1$2', ADD_PREFIXES(`$1', shift(shift($@)))')')dnl
define(`MEMBER', 
    ADD_PREFIXES(`PDT', shift(shift($@))