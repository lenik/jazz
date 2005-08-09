m4_include(`m4x-base.m4')
M4X_BEGIN()

# M4 Utilities
#
# DDL Helper - MySQL Dialects
#
# $Id: ddl-mysql.m4,v 1.5 2005-08-09 01:21:39 dansei Exp $
# $Log: not supported by cvs2svn $
# Revision 1.4  2005/08/06 14:10:01  dansei
# apply new M4X framework
#
# Revision 1.3  2005/08/05 06:03:30  dansei
# dev pack.
#
# Revision 1.2  2005/07/30 05:54:57  dansei
# add type UTIME
#
# Revision 1.1  2005/07/29 14:21:45  dansei
# initial
#

m4_define(`UUID', `char(32)')

# auto-id key
m4_define(`AIK', `m4_ifelse($1, `', `bigint', `int($1)') `auto_increment primary key'')

# auto-id key, no reference. 
m4_define(`AIK_LEAF', `m4_ifelse($1, `', `bigint', `int($1)') `auto_increment primary key'')

# Multi-lingual string
# UTF-8: m4_define(`STR', `m4_ifelse($1, `', `varchar(255)', `char(eval(3*$1))')')
m4_define(`MSTR', `m4_ifelse($1, `', `varchar(255)', `char($1)')')

m4_define(`DWORD', `int')
m4_define(`UTIME', `int')

m4_define(`EMAIL', `char(50)')
m4_define(`SHA1', `char(40)')

M4X_END()