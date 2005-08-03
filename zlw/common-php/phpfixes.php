<?
#
# PHP Bug Fixes
#
# Copy this fixes file to the same directory of which do includes.
#
# $Id: phpfixes.php,v 1.2 2005-08-03 14:42:17 dansei Exp $
# $Log: not supported by cvs2svn $
# Revision 1.1  2005/07/31 02:19:37  dansei
# this become the major version of phpfixes instead of _Phpfixes.
# (now the _Phpfixes.php is for dispatch, and without version header)
#
if (! function_exists('_Require')) {

function _EnterIfNecessary($target) {
    $dir = NULL; 
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
            if (substr($inc, -1) == '/')
                $inc = substr($inc, 0, -1); 
            if (file_exists("$inc/$target")) {
                $target = "$inc/$target"; 
                $dir = dirname($target); 
            }
        }
    }
    
    if (is_null($dir)) 
        die("file not existed: $target, current directory: ".getcwd()
            .", include-path: ".get_include_path()); 

    $cwd = getcwd(); 
    chdir($dir); 
    return $cwd; 
}

function _LeaveIfNecessary($dir) {
    if ($dir) {
        chdir($dir); 
    }
}

function _Require($target) {
    $leave = _EnterIfNecessary($target); 
    $path = array(getcwd(), basename($target)); 
    require basename($target); 
    _LeaveIfNecessary($leave); 
    return $path; 
}

function _RequireOnce($target) {
    $leave = _EnterIfNecessary($target); 
    $path = array(getcwd(), basename($target)); 
    require_once basename($target); 
    _LeaveIfNecessary($leave); 
    return $path; 
}

function _Include($target) {
    $leave = _EnterIfNecessary($target); 
    $path = array(getcwd(), basename($target)); 
    include basename($target); 
    _LeaveIfNecessary($leave); 
    return $path; 
}

function _IncludeOnce($target) {
    $leave = _EnterIfNecessary($target); 
    $path = array(getcwd(), basename($target)); 
    include_once basename($target); 
    _LeaveIfNecessary($leave); 
    return $path; 
}

}
?>