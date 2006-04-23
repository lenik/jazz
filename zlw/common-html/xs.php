<?php

require_once dirname(__FILE__) . '/xs-base.php';
require_once dirname(__FILE__) . '/xs-js.php';
require_once dirname(__FILE__) . '/xs-vbs.php';

# .section.  singleton definition

global $ZLW_XS;

function zlw_xs_lang($lang = null) {
    global $ZLW_XS;
    if (is_null($lang) && ! isset($ZLW_XS))
        $ZLW_XS = new zlw_xs_javascript;
    else {
        $xs = 'zlw_xs_' . $lang; 
        $ZLW_XS = new $xs();
    }
    return $ZLW_XS; 
}

# .section.  global function wrappers

function zlw_xs_quote_var($var, $lang = null) {
    return zlw_xs_lang($lang)->quote_var($var);
}

function zlw_xs_quote_array($var, $lang = null) {
    return zlw_xs_lang($lang)->quote_array($var);
}

function zlw_xs_quote_map($var, $lang = null) {
    return zlw_xs_lang($lang)->quote_map($var);
}

function zlw_xs_quote_scalar($var, $lang = null) {
    return zlw_xs_lang($lang)->quote_scalar($var);
}

function zlw_xs_quote_string($var, $lang = null) {
    return zlw_xs_lang($lang)->quote_string($var);
}

function zlw_xs_quote_bool($var, $lang = null) {
    return zlw_xs_lang($lang)->quote_bool($var);
}

function zlw_xs_quote_numeric($var, $lang = null) {
    return zlw_xs_lang($lang)->quote_numeric($var);
}

function zlw_xs_decl_var($name, $type = null, $init = null) {
    return zlw_xs_lang($lang)->decl_var($name, $type, $init); 
}

function zlw_xs_decl_sub($name, $body, $params = null, $ret = null,
                         $throws = null) {
    return zlw_xs_lang($lang)->decl_sub($name, $body, $params, $ret, $throws);
}

function zlw_xs_exp_assign($lhs, $rhs, $lang = null) {
    return zlw_xs_lang($lang)->exp_assign($lhs, $rhs);
}

function zlw_xs_exp_concat($lhs, $rhs, $lang = null) {
    return zlw_xs_lang($lang)->exp_concat($lhs, $rhs);
}

function zlw_xs_vardump($name, $var, $lang = null) {
    return zlw_xs_lang($lang)->vardump($name, $var);
}

?>