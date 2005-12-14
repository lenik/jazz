<?php

/* Common-PHP
 *
 * String Utilities
 */

if (! defined('NUM_FORMAT'))
    define('NUM_FORMAT', '%1.3f'); 
if (! defined('TIME_FORMAT'))
    define('TIME_FORMAT', 'Y-m-d H:i:s'); 

function num_format($num) {
    if ($num == (int)$num) return $num; 
    else return sprintf(NUMBER_FORMAT, $num); 
}

function &num_of(&$mix) {
    if (! isset($mix)) return 0; 
    if (empty($mix)) return 0; 
    return $mix; 
}

global $PHPX_TIMEOFDAY; 
global $PHPX_TIMEZONE; 
$PHPX_TIMEOFDAY = gettimeofday(); 
$PHPX_TIMEZONE = -60 * $PHPX_TIMEOFDAY['minuteswest']; # +28800 for +8:00

function time_0()               { global $PHPX_TIMEZONE; 
                                  return time() - $PHPX_TIMEZONE; }
function time_0_format($time)   { global $PHPX_TIMEZONE; 
                                  return date(TIME_FORMAT, $time + $PHPX_TIMEZONE); }
function time_format($time)     { return date(TIME_FORMAT, $time); }
function time_format_0($time)   { return gmdate(TIME_FORMAT, $time); }
function time_of($str)          { return strtotime($str); }
function time_of_0($str_0)      { global $PHPX_TIMEZONE; 
                                  return strtotime($str) + $PHPX_TIMEZONE; }

function strand() {
    $args = func_get_args(); 
    foreach ($args as $arg)
        if (! $arg) return ''; 
    return join('', $args); 
}

function strands($sep) {
    $args = func_get_args(); 
    array_shift($args); 
    foreach ($args as $arg)
        if (! $arg) return ''; 
    return join($sep, $args); 
}

function stror() {
    $args = func_get_args(); 
    foreach ($args as $arg)
        if ($arg) return join('', $args); 
    return ''; 
}

function strors($sep) {
    $args = func_get_args(); 
    array_shift($args); 
    foreach ($args as $arg)
        if ($arg) return join($sep, $args); 
    return ''; 
}

function strimp() {
    $args = func_get_args(); 
    $n = func_num_args(); 
    for ($i = 0; $i < $n; $i++)
        if (! $args[$i]) {
            array_splice($args, $i); 
            break; 
        }
    return join('', $args); 
}

function strimps($sep) {
    $args = func_get_args(); 
    array_shift($args); 
    $n = func_num_args() - 1; 
    for ($i = 0; $i < $n; $i++)
        if (! $args[$i]) {
            array_splice($args, $i); 
            break; 
        }
    return join($sep, $args); 
}

function strfirst() {
    $args = func_get_args(); 
    foreach ($args as $arg)
        if ($arg) return $arg; 
    return false; 
}

function strlast() {
    $args = func_get_args(); 
    $n = func_num_args(); 
    for ($i = $n - 1; $i >= 0; $i--)
        if ($arg = $args[$i])
            return $arg; 
    return false; 
}

# strpass(str, pass1, pass2, ..., passN, default)
function strpass($str) {
    $args = func_get_args(); 
    $n = func_num_args(); 
    # the last argument is default value
    for ($i = 1; $i < $n - 1; $i++) {
        if ($str == $args[$i])
            return $str; 
    }
    return $args[$n - 1]; 
}
?>