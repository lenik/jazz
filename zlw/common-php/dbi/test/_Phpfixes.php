<?php
/* Common-PHP
 *
 * PHP Bug Fixes
 *
 * Copy this fixes file to the same directory of which do includes.
 *
 * (Id: phpfixes.php,v 1.2.4.1 2005/12/04 02:37:03 dansei Exp )
 * (Log: phpfixes.php,v )
 * Revision 1.2.4.1  2005/12/04 02:37:03  dansei
 * updated for vbank project
 *
 * Revision 1.2  2005/08/03 14:42:17  dansei
 * dev pack.
 *
 * Revision 1.1  2005/07/31 02:19:37  dansei
 * this become the major version of phpfixes instead of _Phpfixes.
 * (now the _Phpfixes.php is for dispatch, and without version header)
 */
if (! function_exists('_Require')) {

$_PARAMSTACK = array(); 
$_PARAM = null; 

function _EnterIfNecessary($target) {
    $dir = null; 
    
    # begin with / ./ ../
    if (substr($target, 0, 1) == '/'
            || substr($target, 0, 2) == './'
            || substr($target, 0, 3) == '../') {
        # skip include-path
        if (file_exists($target))
            $dir = dirname($target); 
    } else {
        # search include-path
        $incs = split(PATH_SEPARATOR, get_include_path()); 
        foreach ($incs as $inc) {
            if (substr($inc, -1) == '/')        # trim trailing .../
                $inc = substr($inc, 0, -1); 
            if (file_exists("$inc/$target")) {  # got
                $target = "$inc/$target"; 
                $dir = dirname($target); 
                break; 
            }
        }
    }
    
    if (is_null($dir))
        return null; 

    $cwd = getcwd(); 
    chdir($dir); 
    return $cwd; 
}

function _LeaveIfNecessary($dir) {
    if ($dir)
        chdir($dir); 
}

function _IncludeError($target) {
    die("file not existed: $target, current directory: ".getcwd()
        .", include-path: ".get_include_path()); 
}

function _Require($target, $must_exist = true, $param = null) {
    global $_PARAMSTACK; 
    global $_PARAM; 
    $leave = _EnterIfNecessary($target); 
    if (is_null($leave)) {
        if ($must_exist)
            _IncludeError($target); 
        else
            return null; 
    }
    # $path = array(getcwd(), basename($target)); 
    $_PARAMSTACK[] = $_PARAM = $param; 
    require basename($target); 
    array_pop($_PARAMSTACK); 
    $_PARAM = $_PARAMSTACK[sizeof($_PARAMSTACK) - 1]; 
    _LeaveIfNecessary($leave); 
    return true; 
}

function _RequireOnce($target, $must_exist = true, $param = null) {
    global $_PARAMSTACK; 
    global $_PARAM; 
    $leave = _EnterIfNecessary($target); 
    if (is_null($leave)) {
        if ($must_exist)
            _IncludeError($target); 
        else
            return null; 
    }
    # $path = array(getcwd(), basename($target)); 
    $_PARAMSTACK[] = $_PARAM = $param; 
    require_once basename($target); 
    array_pop($_PARAMSTACK); 
    $_PARAM = $_PARAMSTACK[sizeof($_PARAMSTACK) - 1]; 
    _LeaveIfNecessary($leave); 
    return true; 
}

function _Include($target, $must_exist = true, $param = null) {
    global $_PARAMSTACK; 
    global $_PARAM; 
    $leave = _EnterIfNecessary($target); 
    if (is_null($leave)) {
        if ($must_exist)
            _IncludeError($target); 
        else
            return null; 
    }
    # $path = array(getcwd(), basename($target)); 
    $_PARAMSTACK[] = $_PARAM = $param; 
    include basename($target); 
    array_pop($_PARAMSTACK); 
    $_PARAM = $_PARAMSTACK[sizeof($_PARAMSTACK) - 1]; 
    _LeaveIfNecessary($leave); 
    return true; 
}

function _IncludeOnce($target, $must_exist = true, $param = null) {
    global $_PARAMSTACK; 
    global $_PARAM; 
    $leave = _EnterIfNecessary($target); 
    if (is_null($leave)) {
        if ($must_exist)
            _IncludeError($target); 
        else
            return null; 
    }
    # $path = array(getcwd(), basename($target)); 
    $_PARAMSTACK[] = $_PARAM = $param; 
    include_once basename($target); 
    array_pop($_PARAMSTACK); 
    $_PARAM = $_PARAMSTACK[sizeof($_PARAMSTACK) - 1]; 
    _LeaveIfNecessary($leave); 
    return true; 
}

}
?>