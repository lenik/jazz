<?
/* Common-PHP
 *
 * SQL Utilities
 * 
 * $Id: sql.php,v 1.1 2005-07-31 02:30:34 dansei Exp $
 * $Log: not supported by cvs2svn $
 */

require 'string.php'; 

function random_uuid() {
    $uuid = ''; 
    for ($i = 0; $i < 8; $i++)
        $uuid .= sprintf("%04x", rand(0, 0xFFFF)); 
    return $uuid; 
}

function QQ($str)               { return "\"$str\""; }
function Q($str)                { return "'$str'"; }
function C($str)                { return "$str,"; }
function QQC($str)              { return "\"$str\","; }
function QC($str)               { return "'$str',"; }

function sql_escape($str) {
    /* kill control-code and escape ' quote */
    $str = preg_replace("/[[:cntrl:]]/", "", $str);
    $str = preg_replace("/'/", "''", $str);
    return $str;
}

function sql_descape($str) {
    $str = preg_replace("/''/", "'", $str);
    return $str;
}

function build_sql_value($val) {
    if (is_string($val))
        return Q(sql_escape($val)); 
    return $val; 
}

/* key=value, key=value, ... */
function build_pairs($assoc, $separator = ', ', $delim = '=') {
    $pairs = ''; 
    foreach ($assoc as $k=>$v) {
        if ($v == NULL) continue; 
        if ($pairs)
            $pairs .= $separator; 
        $pairs .= "$k$delim".build_sql_value($v); 
    }
    return $pairs; 
}

/* (key, key, ...) values (value, value, ...) */
function build_insert_values($assoc) {
    $keys = ''; 
    $values = ''; 
    foreach ($assoc as $k=>$v) {
        if ($v == NULL) continue; 
        if ($keys) {
            $keys .= ', '; 
            $values .= ', '; 
        }
        $keys .= $k; 
        $values .= build_sql_value($v); 
    }
    return "($keys) values($values)"; 
}

/* import_assoc(this, fetch_assoc()) */
function import_assoc(&$target, $assoc) {
    foreach ($assoc as $member=>$value) {
        if ($value == NULL) continue; 
        $target->$member = $value; 
    }
}

?>
