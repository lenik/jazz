<?
include(`config.m4')dnl
/*
 * __ORIGINAL_FILE__
 */

/* Common-PHP
 *
 * String Utilities
 * 
 * $Id: string.m4,v 1.5 2005-08-05 06:03:31 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.4  2005/08/03 14:42:17  dansei
 * dev pack.
 *
 * Revision 1.3  2005/07/31 10:52:06  dansei
 * move back sql-section again
 *
 * Revision 1.2  2005/07/31 02:30:34  dansei
 * includes fix and dev. pack
 *
 * Revision 1.1  2005/07/30 05:20:11  dansei
 * initial
 *
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

function time_format($time)     { return date(TIME_FORMAT, $time); }
function time_format_0($time)   { return gmdate(TIME_FORMAT, $time); }
function time_of($str)          { return strtotime($str); }
function time_of_0($str_0) {
    $timeofday = gettimeofday(); 
    $gmtadj = -60 * $timeofday['minuteswest']; 
    return strtotime($str) + $gmtadj; 
}

function parse_id($id = '') {
    if ($id == '')
        $id = '$Id: string.m4,v 1.5 2005-08-05 06:03:31 dansei Exp $'; 
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

function logger() {
    $args = func_get_args(); 
    $endup = true; 
    if (is_bool($args[count($args)-1]))
        $endup = array_pop($args); 
    echo '<pre style=\'color:#808080\'>[', time_format(time()), '] - ', join('', $args); 
    if ($endup) echo "\n</pre>\n"; 
    return true; 
}

function logger_end() {
    $args = func_get_args(); 
    echo join('', $args), "\n</pre>\n"; 
    return true; 
}

function highlight() {
    $args = func_get_args(); 
    $on = true; 
    if (is_bool($args[count($args)-1]))
        $on = array_pop($args); 
    if ($on)
        return "<span class='Highlight'>".join('', $args).'</span>'; 
    return ''; 
}

?>
