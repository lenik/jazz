m4_include(`m4x-base.m4')
M4X_BEGIN()

# M4 Utilities
#
# M4 Commono Utilities
#
# $Id: m4x-util.m4,v 1.1 2005-08-06 14:09:12 dansei Exp $
# $Log: not supported by cvs2svn $
#

m4_define(`m4x_nl', `
')

m4_define(`m4x_echo', `$@')

m4_define(`m4x_join', 
    `m4_ifelse($#, 0, , $#, 1, , $#, 2, ``$2'', 
        ``$2'`$1'm4x_join(`$1', m4_shift(m4_shift($@)))')')

m4_define(`m4x_reverse', 
    `m4_ifelse($#, 0, , $#, 1, ``$1'', `m4x_reverse(m4_shift($@)), `$1'')')

m4_define(`m4x_stripnulls', 
    `m4_ifelse($#, 0, , $#, 1, ``$1'', 
        `m4_ifelse(`$1', `', `m4x_stripnulls(m4_shift($@))', 
            `m4_ifelse(m4x_quote(m4x_stripnulls(m4_shift($@))), ``'', `$1', 
                ``$1', m4x_stripnulls(m4_shift($@))')')')')

M4X_END()