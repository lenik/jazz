m4_include(`m4x-base.m4')
M4X_BEGIN()
m4x_lazydef(`name', `value')
M4X_END()
Start:

`M4X___ORIGINAL_FILES__' = 
    "M4X___ORIGINAL_FILE__"

`m4x_lazydef test' = 
    "test for a <name>"

`m4x_concat test' = 
    "m4x_concat()"
    "m4x_concat(arg1)"
    "m4x_concat(arg1, 
        arg2)"
    "m4x_concat(`2-names: ', name, name)"

`dumpdef: m4x_for' = 
    "m4_dumpdef(`m4x_for')"

`dumpdef: _m4x_for' = 
    "m4_dumpdef(`_m4x_for')"

`dumpdef: _m4x_find' = 
    "m4_dumpdef(`_m4x_find')"
    
`m4x_for test' = 
    "m4x_for(`i', 1, 10, `(i)')"
    "m4x_for(`i', 1, 3, 
        `m4x_for(`j', 1, 10, `(i, j)')m4x_nl')"

`_m4x_find test' = 
    "_m4x_find(0, keyX)"
    "_m4x_find(0, keyX, arg0)"
    "_m4x_find(0, key0, key0)"
    "_m4x_find(0, keyX, arg0, arg1, arg2)"
    "_m4x_find(0, key2, arg0, arg1, key2)"
    "_m4x_find(0, key3, arg0, arg1, arg2, key3, arg4, arg5)"
    "_m4x_find(0, keyX, arg0, arg1, arg2, arg3, arg4, arg5)"

`m4x_find test' = 
    "m4x_find(keyX)"
    "m4x_find(keyX, arg0)"
    "m4x_find(key0, key0)"
    "m4x_find(keyX, arg0, arg1, arg2)"
    "m4x_find(key2, arg0, arg1, key2)"
    "m4x_find(key3, arg0, arg1, arg2, key3, arg4, arg5)"
    "m4x_find(keyX, arg0, arg1, arg2, arg3, arg4, arg5)"

`m4x_ifexist test' = 
    "m4x_ifexist(keyX)"
    "m4x_ifexist(key0, `key0', exist)"
    "m4x_ifexist(keyX, `arg0')"
    "m4x_ifexist(keyX, `arg0, arg1, arg2', exist, not-exist)"
    "m4x_ifexist(key2, `arg0, arg1, key2', exist, not-exist)"
    "m4x_ifexist(key2, `arg0, arg1, key2, arg3', exist, not-exist)"
    "m4x_ifexist(keyX, `arg0, arg1, arg2, arg3', exist, not-exist)"
    "m4x_ifexist(key3, `arg0, arg1, arg2, key3, arg4, arg5', exist)"
    "m4x_ifexist(keyX, `arg0, arg1, arg2, arg3, arg4, arg5', exist)"
    "m4x_ifexist(keyX, `arg0, arg1, arg2, arg3, arg4, arg5', exist)"

`m4x_ifexist test(rec)' = 
    "m4x_ifexist(
        a, `b, c, d', found-a, 
        b, `x, c, d', found-b, 
        c, `b, c, d', found-c, 
        all-failure)"

End.