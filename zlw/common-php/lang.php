<?
/*
 * warning:  this file is automatically generated by m4 scripts.  please do not edit this file, you may lose all your changes in this file after the original file is re-built.  
 */

/* Common-PHP
 *
 * String Utilities
 */

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
                                  return date("Y-m-d H:i:s", $time + $PHPX_TIMEZONE); }
function time_format($time)     { return date("Y-m-d H:i:s", $time); }
function time_format_0($time)   { return gmdate("Y-m-d H:i:s", $time); }
function time_of($str)          { return strtotime($str); }
function time_of_0($str_0)      { global $PHPX_TIMEZONE; 
                                  return strtotime($str) + $PHPX_TIMEZONE; }

function parse_id($id = '') {
    if ($id == '')
        $id = '$Id: lang.php,v 1.9.4.2 2005-12-04 04:21:50 dansei Exp $'; 
    preg_match(
               '/^ \$ [I][d][:] \s (.*?) \s ([0-9.]+) \s ([0-9\/\\\-]+) \s 
                 ([0-9:]+) \s (.*?) \s (\w+) \s \$ $/x', 
               $id, $segs); 
    return array(
                 'rcs' => $segs[1], 
                 'rev' => $segs[2], 
                 'date' => $segs[3], 
                 'time' => $segs[4], 
                 'author' => $segs[5], 
                 'state' => $segs[6], 
                 ); 
}

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

function xml_header($version = '1.0', $encoding = 'utf-8', $suffix = "\n") {
    echo "<?xml version='$version' encoding='$encoding'?>$suffix"; 
}

function logger() {
    $args = func_get_args(); 
    $cont = false; 
    if (is_bool($args[count($args)-1]))
        $cont = array_pop($args); 
    echo '<pre style=\'color:#808080\'>[', time_format(time()), '] - ', join('', $args); 
    if (! $cont) echo "\n</pre>\n"; 
    return true; 
}

function logger_end() {
    $args = func_get_args(); 
    echo join('', $args), "\n</pre>\n"; 
    return true; 
}

?>