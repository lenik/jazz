m4_include(`m4x-base.m4')
M4X_BEGIN()

# M4 Utilities
#
# M4 Commono Utilities
#
# $Id: m4x-util.m4,v 1.2 2005-08-07 12:14:28 dansei Exp $
# $Log: not supported by cvs2svn $
# Revision 1.1  2005/08/06 14:09:12  dansei
# initial, using M4X-framework
#
#

m4_define(`m4x_echo', `$@')

m4_define(`m4x_reverse', 
    `m4_ifelse($#, 0, , $#, 1, ``$1'', `m4x_reverse(m4_shift($@)), `$1'')')

M4X_END()