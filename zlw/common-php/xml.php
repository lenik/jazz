<?php

/* Common-PHP
 *
 * Dynamic XML Support
 */

function phpx_xml_header($encoding = 'utf-8', $version = '1.0') {
    global $PHPX_XML_BEGIN; 
    header('Content-Type: text/xml'); 
    $nargs = func_num_args(); 
    if ($nargs > 2) {
        $args = func_get_args(); 
        for ($i = 2; $i < $nargs; $i++)
            header($args[$i]); 
    }
    echo "<?xml version='$version' encoding='$encoding'?>\n"; 
    $PHPX_XML_BEGIN = true; 
}

function phpx_xml_pi($pi) {
    echo '<?' . htmlspecialchars($pi) . '?>'; 
}

function phpx_xml_attr($attr) {
    return '"' . htmlspecialchars($attr) . '"'; 
}

function phpx_xml_text($text) {
    return htmlspecialchars($text, ENT_NOQUOTES); 
}

?>