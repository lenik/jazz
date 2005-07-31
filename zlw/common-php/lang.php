<?
/* Common-PHP
 *
 * String Utilities
 * 
 * $Id: lang.php,v 1.4 2005-07-31 04:32:48 dansei Exp $
 * $Log: not supported by cvs2svn $
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

function time_format($time)     { return date("Y-m-d H:i:s", $time); }
function time_format_0($time)   { return gmdate("Y-m-d H:i:s", $time); }
function time_of($str)          { return strtotime($str); }
function time_of_0($str_0) {
    $timeofday = gettimeofday(); 
    $gmtadj = -60 * $timeofday['minuteswest']; 
    return strtotime($str) + $gmtadj; 
}

function parse_id($id = '') {
    if ($id == '')
        $id = '$Id: lang.php,v 1.4 2005-07-31 04:32:48 dansei Exp $'; 
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

?>
