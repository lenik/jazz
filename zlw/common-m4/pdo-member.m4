m4_include(`m4x-util.m4')
M4X_BEGIN()

# M4 Utilities
#
# PDO Implement Helper
#
# $Id: pdo-member.m4,v 1.3 2005-08-07 12:14:28 dansei Exp $
# $Log: not supported by cvs2svn $
# Revision 1.2  2005/08/06 14:09:40  dansei
# reinitial using new M4X-framework
#
# Revision 1.1  2005/08/05 14:34:11  dansei
# devpack: change to  php-data-object framework
#

# ADD_PREFIXES(prefix, args...)
m4_define(`pdo_addprefixes', 
    `m4_ifelse($#, 0, , $#, 1, , $#, 2, ``$1$2'', 
        ``$1$2', pdo_addprefixes(`$1', m4_shift(m4_shift($@)))')')

# MEMBER(name, pdt-opts)
m4_define(`pdo_member_type', 
    `m4_ifelse($#, 0, 0, $#, 1, 
        `m4_ifelse(`$1', `', 0, `pdo_addprefixes(`PDT_', `$1')')', 
        `m4x_join(` | ', pdo_addprefixes(`PDT_', $@))')')

m4_define(`pdo_member', 
    `var $pdt_$1 = pdo_member_type(m4_shift($@))')

M4X_END()