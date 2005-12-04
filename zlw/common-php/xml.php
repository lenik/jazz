<?php

/* Common-PHP
 *
 * Dynamic XML Support
 */
 
function phpx_transient() {
    header('Cache-Control: no-cache'); 
}

function phpx_xml_header($encoding = 'utf-8', $version = '1.0') {
    header('Content-Type: text/xml'); 
    $nargs = func_num_args(); 
    if ($nargs > 2) {
        $args = func_get_args(); 
        for ($i = 2; $i < $nargs; $i++)
            header($args[$i]); 
    }
    echo "<?xml version='$version' encoding='$encoding'?>\n"; 
}

function phpx_pi($pi) {
    echo '<?' . str_replace('>', '&gt;', 
                str_replace('<', '&lt;', $pi)) . '?>'; 
}

?>