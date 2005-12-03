m4_include(`m4x-util.m4')
M4X_BEGIN()

# M4 Utilities
#
# PDO Implement Helper
#

m4_define(`PDT_RAW',        1)
m4_define(`PDT_CDATA',      2)
m4_define(`PDT_PCDATA',     4)
m4_define(`PDT_NUMBER',     PDT_RAW)
m4_define(`PDT_STRING',     PDT_CDATA)
m4_define(`PDT_CODE',       PDT_PCDATA)

m4_define(`PDT_GET',        256)
m4_define(`PDT_PUT',        512)
m4_define(`PDT_NOTNULL',    8192)
m4_define(`PDT_VERIFY',     16384)
m4_define(`PDT_PRIMARY',    32768)

# pdo_addprefixesprefix, args...)
m4_define(`pdo_addprefixes', 
    `m4_ifelse($#, 0, , $#, 1, , $#, 2, ``$1$2'', 
        ``$1$2', pdo_addprefixes(`$1', m4_shift(m4_shift($@)))')')

m4_define(`pdo_member_type', `m4_eval(m4x_expand(
    m4_ifelse($#, 0, 0, $#, 1, 
        `m4_ifelse(`$1', `', 0, `pdo_addprefixes(`PDT_', `$1')')', 
        `m4x_join(` + ', pdo_addprefixes(`PDT_', $@))')))')

# pdo_member(name, pdt-opts)
m4_define(`pdo_member', 
    `var $pdt_$1 = pdo_member_type(m4_shift($@)) /*m4_shift($@)*/')

m4_define(`pdo_default', `var $pdv_`$1' = `$2'')

M4X_END()