<?php

function phpx_parse_id($id = '') {
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

function phpx_random_uuid() {
    $uuid = ''; 
    for ($i = 0; $i < 8; $i++)
        $uuid .= sprintf("%04x", rand(0, 0xFFFF)); 
    return $uuid; 
}

function phpx_logger() {
    $args = func_get_args(); 
    $cont = false; 
    if (is_bool($args[count($args)-1]))
        $cont = array_pop($args); 
    echo '<pre style=\'color:#808080\'>[', time_format(time()), '] - ', join('', $args); 
    if (! $cont) echo "\n</pre>\n"; 
    return true; 
}

function phpx_logger_end() {
    $args = func_get_args(); 
    echo join('', $args), "\n</pre>\n"; 
    return true; 
}

?>