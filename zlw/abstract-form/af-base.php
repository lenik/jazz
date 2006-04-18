<?php

require_once dirname(__FILE__) . '/../common-php/http.php'; 

global $ZLW_AF_NSURI; 
$ZLW_AF_NSURI = 'http://www.bodz.net/xml/zlw/abstract-form'; 

# x:/.../zlw/abstract-form
function zlw_af_homedir($req = null) {
    $inc = dirname(__FILE__); 
    $inc = str_replace('\\', '/', $inc); 
    # URL-include, always using absolute url. 
    if (strpos($inc, '//') !== false)
        return $inc;                   # see t-dirname
    if (is_null($req))
        $req = realpath($_SERVER['SCRIPT_FILENAME']); 
    $req = str_replace('\\', '/', $req); 
    $req = dirname($req); 
    $req_len = strlen($req); 
    # starts-with ? 
    if ($req_len > 0 && substr($inc, 0, $req_len) == $req)
        return '.' . substr($inc, $req_len); 
    return $inc; 
}

global $ZLW_AF_HOME; 
$ZLW_AF_HOME = zlw_af_homedir(); 

function zlw_af_hint_prep($hint) {
    if ($hint == '*this*') {
        $hint = phpx_this_url(); 
        if (($pos = strpos($hint, '?')) !== false) {
            $hint = substr($hint, 0, $pos); 
        }
    }
    return $hint; 
}

?>