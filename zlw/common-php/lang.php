<?php

# .section. number format

if (! defined('PHPX_NUM_FORMAT'))
    define('PHPX_NUM_FORMAT', '%1.3f'); 
if (! defined('PHPX_TIME_FORMAT'))
    define('PHPX_TIME_FORMAT', 'Y-m-d H:i:s'); 

function phpx_num_format($num) {
    if ($num == (int)$num) return $num; 
    else return sprintf(PHPX_NUMBER_FORMAT, $num); 
}

function &phpx_num_of(&$mix) {
    if (! isset($mix)) return 0; 
    if (empty($mix)) return 0; 
    return $mix; 
}

# .section. date/time utilities

global $PHPX_TIMEOFDAY; 
global $PHPX_TIMEZONE; 
$PHPX_TIMEOFDAY = gettimeofday(); 
$PHPX_TIMEZONE = -60 * $PHPX_TIMEOFDAY['minuteswest']; # +28800 for +8:00

# timestamp@TZ
function phpx_time_0() {
    global $PHPX_TIMEZONE; 
    return time() - $PHPX_TIMEZONE; 
}

# timestamp@TZ => timestr@Local
function phpx_time_0_format($time, $format = PHPX_TIME_FORMAT) {
    global $PHPX_TIMEZONE; 
    return date($format, $time + $PHPX_TIMEZONE); 
}

# timestamp@TZ => timestr@TZ
function phpx_time_format($time, $format = PHPX_TIME_FORMAT) {
    return date($format, $time); 
}

# timestamp@Local => timestr@TZ
function phpx_time_format_0($time, $format = TIME_FORMAT) {
    global $PHPX_TIMEZONE; 
    return date($format, $time - $PHPX_TIMEZONE); 
}

# timestr@Local => timestamp@TZ
function phpx_time_0_of($str) {
    global $PHPX_TIMEZONE; 
    return strtotime($str) - $PHPX_TIMEZONE; 
}

# timestr@TZ => timestamp@TZ
function phpx_time_of($str) {
    return strtotime($str); 
}

# timestr@TZ => timestamp@Local
function phpx_time_of_0($str_0) {
    global $PHPX_TIMEZONE; 
    return strtotime($str_0) + $PHPX_TIMEZONE; 
}

# .section. simple serialization

function phpx_list_format($list) {
    $string = ''; 
    if ($list)
        foreach ($list as $item) {
            if ($string != '')
                $string .= ':'; 
            $escaped = str_replace("\n", '\n', addslashes($item)); 
            if (strpos($item, ':') !== false)
                $string .= '"' . $escaped . '"'; 
            else
                $string .= $escaped; 
        }
    return $string; 
}

function &phpx_list_parse($string) {
    # string: item:item:...
    # item: ... "..."
    $segs = explode(':', $string); 
    $nsegs = sizeof($segs); 
    if ($nsegs == 1 && $segs[0] == '') return null;
    
    $item = ''; 
    # "A : B" : C  ==>  "A:B" : C
    for ($i = 0; $i < $nsegs; $i++) {
        $item .= $segs[$i]; 
        if (substr($item, 0, 1) == '"') {
            if (substr($item, -1) != '"' || substr($item, -2) == '\"') {
                $item .= ':'; 
                continue;   # concat($i, $i+1)
            }
            $item = eval('return ' . $item . ';'); 
        } else {
            $item = eval('return "' . $item . '";'); 
        }
        $list[] = $item; 
        $item = ''; 
    }
    return $list; 
}

function phpx_map_format($map) {
    $string = ''; 
    if ($map)
        foreach ($map as $name=>$value) {
            if ($string != '')
                $string .= ':'; 
            $escaped = str_replace("\n", '\n', addslashes($value)); 
            if (strpos($value, ':') !== false)
                $string .= "$name=\"$escaped\""; 
            else
                $string .= "$name=$escaped"; 
        }
    return $string; 
}

function &phpx_map_parse($string) {
    # string: name=value:...
    # value: ... "..."
    $segs = explode(':', $string); 
    $nsegs = sizeof($segs); 
    
    $entry = ''; 
    # A="B : C" : D  ==>  A="B:C" : D
    for ($i = 0; $i < $nsegs; $i++) {
        $entry .= $segs[$i]; 
        if (strpos($entry, '=') !== false) {
            list($name, $value) = explode('=', $entry, 2);
        } else {
            $name = $entry; 
            $value =''; 
        }
        if ($name == '') continue; 
        if (substr($value, 0, 1) == '"') {
            if (substr($value, -1) != '"' || substr($value, -2) == '\"') {
                $value .= ':'; 
                continue;   # concat($i, $i+1)
            }
            $value = eval('return ' . $value . ';'); 
        } else {
            $value = eval('return "' . $value . '";'); 
        }
        $entry = ''; 
        $map[$name] = $value; 
    }
    return $map; 
}

# .section. logic string operators

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
        if (($arg = $args[$i]) !== null)
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

# .section. object reflect helper

# BUG  $rv maybe contains recursive references. 
function phpx_or(&$lv, &$rv) {
    if ($lv == null)
        return $lv = $rv; 
    if ($rv == null)
        return $lv; 
    $lt = gettype($lv); 
    $rt = gettype($rv); 
    if ($lt != $rt)
        return $lv; 
    switch ($lt) {
    case 'object': 
        foreach ($rv as $k=>$v) {
            if ($lv->$k === null)
                $lv->$k = $v; 
            elseif ($lv->$k !== $v)
                phpx_or($lv->$k, $v); 
        }
        break; 
    case 'array': 
        $rs = sizeof($rv); 
        for ($i = 0; $i < $rs; $i++) {
            if ($lv[$i] === null)
                $lv[$i] = $rv[$i]; 
            elseif ($lv[$i] != $rv[$i])
                phpx_or($lv[$i], $rv[$i]); 
        }
        break; 
    }
    return $lv; 
}

function phpx_list_args($args) {
    if (sizeof($args) == 0)
        return null; 
    foreach ($args as $arg) {
        if (is_array($arg))
            foreach ($arg as $ar)
                $list[] = $ar; 
        else
            $list[] = $arg; 
    }
    return $list; 
}

function phpx_map_args($args) {
    if (($n = sizeof($args)) == 0)
        return null; 
    for ($i = 0; $i < $n; $i++)
        if (is_array($args[$i]))
            foreach ($args[$i] as $k=>$v)
                $map[$k] = $v; 
        elseif ($i + 1 < $n)
            $map[$args[$i]] = $args[$i + 1]; 
        else
            die("Invalid argument numbers"); 
    return $map; 
}

?>