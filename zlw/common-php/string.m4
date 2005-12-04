m4_include(`config.m4')
<?
/*
 * M4X___ORIGINAL_FILE__
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
                                  return date(TIME_FORMAT, $time + $PHPX_TIMEZONE); }
function time_format($time)     { return date(TIME_FORMAT, $time); }
function time_format_0($time)   { return gmdate(TIME_FORMAT, $time); }
function time_of($str)          { return strtotime($str); }
function time_of_0($str_0)      { global $PHPX_TIMEZONE; 
                                  return strtotime($str) + $PHPX_TIMEZONE; }

function parse_id($id = '') {
    if ($id == '')
        $id = '$Id: string.m4,v 1.7.4.1 2005-12-04 02:37:03 dansei Exp $'; 
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