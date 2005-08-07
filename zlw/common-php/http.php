<?

function phpx_noslashes($url) {
    if (get_magic_quotes_gpc())
        $url = stripslashes($url); 
    return $url; 
}

function phpx_url_relative($url, $https = NULL) {
    if (is_null($https)) {
        $ret = 'http'; 
        if ($_SERVER['HTTPS'] == 'on')
            $ret .= 's'; 
    } else {
        $ret = $https ? 'https' : 'http'; 
    }
    $ret .= "://$_SERVER[HTTP_HOST]";
    
    # http://server/dir
    $dir = dirname($_SERVER['PHP_SELF']); 
    if (substr($dir, 0, 1) != '/')
        $dir = "/$dir"; 
    if (substr($dir, -1) == '/')
        $dir = substr($dir, 0, -1); 
    $ret .= $dir; 
    
    # http://host/dir/url
    if (substr($url, 0, 1) != '/')
        $url = "/$url"; 
    
    return "$ret$url"; 
}

function phpx_url_full($url, $https = NULL) {
    # url-absolute
    if ($right = strstr($url, '://')) {
        if (is_null($https))
            return $url; 
        else
            return ($https ? 'https' : 'http') . $right; 
    }
    
    # url-full
    if (is_null($https)) {
        $ret = 'http'; 
        if ($_SERVER['HTTPS'] == 'on')
            $ret .= 's'; 
    } else {
        $ret = $https ? 'https' : 'http'; 
    }
    $ret .= "://$_SERVER[HTTP_HOST]";
    
    # http://host/url
    if (substr($url, 0, 1) != '/')
        $url = "/$url"; 
    
    return "$ret$url"; 
}

function phpx_url_arguments($url, $args) {
    if ($args == NULL)
        return $url; 
    
    $trail = ''; 
    foreach ($args as $key=>$value) {
        if ($trail != '')
            $trail .= '&'; 
        $trail .= "$key=".urlencode($value); 
    }
    if ($trail == '')
        return $url; 
        
    if (strchr($url, '?'))
        return "$url&$trail"; 
        
    return "$url?$trail"; 
}

function phpx_redirect_relative($url, $args = NULL, $https = NULL) {
    $url = phpx_url_relative($url, $https); 
    header("Location: ".phpx_url_arguments($url, $args)); 
    exit; 
}

function phpx_redirect($url, $args = NULL, $https = NULL) {
    $url = phpx_url_full($url, $https); 
    header("Location: ".phpx_url_arguments($url, $args)); 
    exit; 
}

function phpx_using_https($https = true) {
    $current = $_SERVER['HTTPS'] == 'on' ? true : false; 
    if ($current == $https)
        return; 
    phpx_redirect(phpx_url_full($_SERVER['REQUEST_URI'], $https)); 
}

function phpx_httpcall_stack() {
    global $PHPX_CALLSTACK; 
    if (! isset($PHPX_CALLSTACK)) {
        if (isset($_REQUEST['phpx_httpcall_hold']))
            $PHPX_CALLSTACK = unserialize(phpx_noslashes($_REQUEST['phpx_httpcall_hold'])); 
        else
            $PHPX_CALLSTACK = array(); 
    }
    return $PHPX_CALLSTACK; 
}

function phpx_httpcall_input() {
    global $PHPX_CALLSTACK; 
    phpx_httpcall_stack(); 
    if ($PHPX_CALLSTACK)
        return end($PHPX_CALLSTACK);
    return NULL;                # not being called
}

function phpx_httpcall_hold() {
    global $PHPX_CALLSTACK; 
    phpx_httpcall_stack(); 
    return serialize($PHPX_CALLSTACK); 
}

function phpx_httpcall_return($retval) {
    global $PHPX_CALLSTACK; 
    phpx_httpcall_stack(); 
    if ($PHPX_CALLSTACK) {
        $top = count($PHPX_CALLSTACK) - 1; 
        $PHPX_CALLSTACK[$top]['VAL'] = $retval; 
        $ret = $PHPX_CALLSTACK[$top]['RET']; 
        unset($PHPX_CALLSTACK[$top]['RET']); 
        phpx_redirect($ret, array('phpx_httpcall_hold' => serialize($PHPX_CALLSTACK))); 
    }
    # if not being called, then don't return anything.
}

function phpx_httpcall_error($errval) {
    global $PHPX_CALLSTACK; 
    phpx_httpcall_stack(); 
    if ($PHPX_CALLSTACK) {
        $top = count($PHPX_CALLSTACK) - 1; 
        $PHPX_CALLSTACK[$top]['ERR'] = $errval; 
        $ret = $PHPX_CALLSTACK[$top]['RET']; 
        unset($PHPX_CALLSTACK[$top]['RET']); 
        phpx_redirect($ret, array('phpx_httpcall_hold' => serialize($PHPX_CALLSTACK))); 
    }
    # if not being called, then don't return anything.
}

function phpx_httpcall($call_uri, $args = NULL) {
    global $PHPX_CALLSTACK; 
    phpx_httpcall_stack(); 
    
    $addr = $_SERVER['PHP_SELF']; 
    if ($PHPX_CALLSTACK) {
        $top = end($PHPX_CALLSTACK); 
        if (! isset($top['RET'])) {
            # return-back
            array_pop($PHPX_CALLSTACK); 
            return $top; 
        }
    }
    
    $frame = $args; 
    if (is_null($frame))
        $frame = array(); 
    $frame['RET'] = phpx_url_full($addr); 
    array_push($PHPX_CALLSTACK, $frame); 
    
    phpx_redirect($call_uri, array('phpx_httpcall_hold' => serialize($PHPX_CALLSTACK))); 
}

?>