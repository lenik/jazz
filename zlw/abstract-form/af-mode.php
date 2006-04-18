<?php

require_once dirname(__FILE__) . '/../common-php/error.php'; 
require_once dirname(__FILE__) . '/af-object.php'; 

# .section.  error manager

class zlw_af_xmlem extends phpx_error_manager {
    private $ns; 
    
    function zlw_af_xmlem($provider = null, $ns = '') {
        $this->phpx_error_manager($provider); 
        $this->pref = PHPX_EM_TERM; 
        $this->ns = $ns; 
    }
    
    function handler(&$error) {
        $err = $error; 
        if (is_a($err, 'zlw_af_error'))
            ; 
        elseif (is_a($err, 'phpx_error')) {
            $cast = new zlw_af_error(null); 
            foreach ($err as $k=>$v)
                $cast->$k = $v; 
            $err = $cast; 
        }
        else                            # maybe string-summary or something else.
            $err = new zlw_af_error($err); 
        
        global $ZLW_AF_MODE; 
        if (isset($ZLW_AF_MODE)) {
            return $ZLW_AF_MODE->error_handler($err); 
        } else {
            # default mode, print die message
            die($err->xml($this->ns)); 
        }
    }
}

function zlw_af_enable_xml_error($ns = '') {
    $em = new zlw_af_xmlem(ZLW_AF_XML, $ns); 
    return $em->register(); 
}

function zlw_af_disable_xml_error() {
    return phpx_error_manager_unregister(ZLW_AF_XML); 
}
?>