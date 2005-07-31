<?
/*
 * PHP Bug Fixes
 * 
 * Copy this fixes file to the same directory of which do includes.
 * 
 * $Id: phpfixes.php,v 1.1 2005-07-31 02:19:37 dansei Exp $
 * $Log: not supported by cvs2svn $
 */
if (! function_exists('_Require')) {

function _EnterIfNecessary($dir) {
    if ($dir) {
        $cwd = getcwd(); 
        chdir($dir); 
        return $cwd; 
    }
}

function _LeaveIfNecessary($dir) {
    if ($dir) {
        chdir($dir); 
    }
}

function _Require($target) {
    $leave = _EnterIfNecessary(dirname($target)); 
    require basename($target); 
    _LeaveIfNecessary($leave); 
}

function _RequireOnce($target) {
    $leave = _EnterIfNecessary(dirname($target)); 
    require_once basename($target); 
    _LeaveIfNecessary($leave); 
}

function _Include($target) {
    $leave = _EnterIfNecessary(dirname($target)); 
    include basename($target); 
    _LeaveIfNecessary($leave); 
}

function _IncludeOnce($target) {
    $leave = _EnterIfNecessary(dirname($target)); 
    include_once basename($target); 
    _LeaveIfNecessary($leave); 
}

}
?>