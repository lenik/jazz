include(`m4x-util.m4')
M4X_BEGIN()

# M4 Utilities
#
# PDO Implement Helper
#
# $Id: pdo-member.m4,v 1.2 2005-08-06 14:09:40 dansei Exp $
# $Log: not supported by cvs2svn $
# Revision 1.1  2005/08/05 14:34:11  dansei
# devpack: change to  php-data-object framework
#

# ADD_PREFIXES(prefix, args...)
m4_define(`ADD_PREFIXES', 
    `m4_ifelse($#, 0, , $#, 1, , $#, 2, ``$1$2'', 
        ``$1$2'ADD_PREFIXES(`$1', m4_shift(m4_shift($@)))')')

# MEMBER(name, pdt-opts)
m4_define(`MEMBER_TYPE', 
    m4_ifelse($#

m4_define(`MEMBER', `m4x_join(`; m4x_nl', m4x_stripnulls
    `var $pdt_$1 = MEMBER_TYPE(m4_shift($@))', 
    

M4X_END()