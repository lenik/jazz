<?php

require_once dirname(__FILE__) . '/../common-php/http.php'; 

global $ZLW_AF_NSURI; 
$ZLW_AF_NSURI = 'http://www.bodz.net/xml/zlw/abstract-form'; 

# userdir: /commons/user/dir1       /../..
# afdir:   /commons/dir2/zlw/af     /dir2/zlw/af
# homedir: ../../dir2/zlw/af
function zlw_af_homedir($req = null) {
    $afdir = dirname(__FILE__); 
    $afdir = str_replace('\\', '/', $afdir); 
    # URL-include, always using absolute url. 
    if (strpos($afdir, '//') !== false)
        return $afdir;                  # see t-dirname
    $userdir = realpath($_SERVER['SCRIPT_FILENAME']); 
    $userdir = str_replace('\\', '/', $userdir); 
    $userdir = dirname($userdir); 
    
    $afsegs = explode('/', $afdir); 
    $usersegs = explode('/', $userdir); 
    $max = min(count($afsegs), count($usersegs)); 
    while ($max--) {
        if ($afsegs[0] != $usersegs[0])
            break; 
        array_shift($afsegs); 
        array_shift($usersegs); 
    }
    
    $rel = ''; 
    while (count($usersegs)) {
        array_shift($usersegs); 
        $rel .= '../'; 
    }
    while (count($afsegs))
        $rel .= array_shift($afsegs) . '/'; 
    
    if ($rel == '')
        $rel = '.'; 
    elseif (substr($rel, -1) == '/')
        $rel = substr($rel, 0, -1); 
    return $rel; 
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