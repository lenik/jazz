<?php

    require_once dirname(__FILE__) . '/../phpfixes.php'; 
    require_once 'zlw/common-php/xml.php'; 
    
    phpx_xml_mode(); 
    
    $xml = new phpx_xml(
        phpx_xml_raw('<test>hello</test>'), 'mystyle.xsl', 'gb2312'); 
        
    echo $xml->xml(); 
?>