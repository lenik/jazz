m4_include(`m4x-base.m4')
M4X_BEGIN()

# M4 Utilities
#
# DDL Helper - MySQL Dialects
#

# auto-id key
m4_define(`AIK', `m4_ifelse($1, `', `bigint', `int($1)') `auto_increment primary key'')

# auto-id key, no reference. 
m4_define(`AIK_LEAF', `m4_ifelse($1, `', `bigint', `int($1)') `auto_increment primary key'')

# Multi-lingual string
# UTF-8: m4_define(`STR', `m4_ifelse($1, `', `varchar(255)', `char(eval(3*$1))')')
m4_define(`MSTR', `m4_ifelse($1, `', `varchar(255)', `char($1)')')

m4_define(`BITS7', `tinyint')
m4_define(`BITS15', `smallint')
m4_define(`BITS31', `int')
m4_define(`BITS63', `int')
m4_define(`UTIME', `int')
m4_define(`BITS', BITS31)

m4_define(`UUID', `char(32)')

m4_define(`MD5', `char(32)')
m4_define(`SHA1', `char(40)')

M4X_END()