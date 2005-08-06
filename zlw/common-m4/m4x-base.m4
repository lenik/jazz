m4_pushdef(`M4X_DIVERT', m4_divnum)m4_dnl
m4_divert(-1)

# M4 Utilities
#
# M4 Language Extension
#
# $Id: m4x-base.m4,v 1.4 2005-08-06 14:08:48 dansei Exp $
# $Log: not supported by cvs2svn $
# Revision 1.3  2005/08/06 10:33:44  dansei
# nearly rewrite.
#
# Revision 1.2  2005/08/05 06:03:30  dansei
# dev pack.
#
# Revision 1.1  2005/07/29 14:21:45  dansei
# initial
#

m4_ifdef(`m4x_once_m4_ext', , `
m4_define(`m4x_once_m4_ext', 1)

# re-quote
m4_define(`m4x_quote', ``$@'')

m4_define(`m4x_concat', 
    `m4_ifelse($#, 0, , $#, 1, ``$1'', 
        ``$1'm4x_concat(m4_shift($@))')')

# diverting for space
m4_define(`M4X_BEGIN', m4x_concat(
    `m4_pushdef(`M4X_DIVERT', m4_divnum)', 
    `m4_divert(-1)'))

m4_define(`M4X_END', m4x_concat(
    `m4_undivert(-1)', 
    `m4_divert(M4X_DIVERT)', 
    `m4_popdef(`M4X_DIVERT')m4_dnl'))

m4_define(`M4X___ORIGINAL_FILE__', 
    `warning:  this file is automatically generated by m4 scripts.  please do not edit this file, you may lose all your changes in this file after the original file is re-built.  ')

# special definer
m4_define(`m4x_lazydef', 
    `m4_ifdef(`$1', , 
        `m4_define(`$1', `$2')')')

# list op
m4_define(`_m4x_for', 
    `$4`'m4_ifelse($1, `$3', , m4x_concat(
        `m4_define(`$1', m4_incr($1))', 
        `_m4x_for(`$1', `$2', `$3', `$4')'))')

m4_define(`m4x_for', m4x_concat(
    `m4_pushdef(`$1', `$2')', 
    `_m4x_for(`$1', `$2', `$3', `$4')', 
    `m4_popdef(`$1')'))

# m4x_find(key, list...) 
m4_define(`_m4x_find', 
    `m4_ifelse($#, 0, , $#, 1, , $#, 2, , 
        $#, 3, `m4_ifelse(`$2', `$3', $1)', 
        `m4_ifelse(`$2', `$3', $1, 
            `_m4x_find(m4_incr($1), `$2', m4_shift(m4_shift(m4_shift($@))))')')')

m4_define(`m4x_find', 
    `m4_ifelse($#, 0, , $#, 1, , `_m4x_find(0, $@)')')

# m4x_ifexist(key, `list...', exist, [not-exist<key, `list...', ...>])
m4_define(`m4x_ifexist', 
    `m4_ifelse(m4x_find(`$1', $2), `', 
        `m4_ifelse($#, 3, , $#, 4, `$4', 
            `m4x_ifexist($4, `$5', m4_shift(m4_shift(m4_shift(m4_shift(m4_shift($@))))))')', 
        `$3')')

')

m4_undivert(-1)m4_dnl
m4_divert(M4X_DIVERT)m4_dnl
m4_popdef(`M4X_DIVERT')m4_dnl