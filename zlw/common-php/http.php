<?

function noslashes($url) {
    if (get_magic_quotes_gpc())
        $url = stripslashes($url); 
    return $url; 
}

function url_relative($url, $https = NULL) {
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

function url_full($url, $https = NULL) {
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

function url_add_arguments($url, $args) {
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

function redirect_relative($url, $args = NULL, $https = NULL) {
    $url = url_relative($url, $https); 
    header("Location: ".url_add_arguments($url, $args)); 
    exit; 
}

function redirect($url, $args = NULL, $https = NULL) {
    $url = url_full($url, $https); 
    header("Location: ".url_add_arguments($url, $args)); 
    exit; 
}

function using_https($https = true) {
    $current = $_SERVER['HTTPS'] == 'on' ? true : false; 
    if ($current == $https)
        return; 
    redirect(url_full($_SERVER['REQUEST_URI'], $https)); 
}

function httpcall_stack() {
    global $_CALLSTACK; 
    if (! isset($_CALLSTACK)) {
        if (isset($_REQUEST['httpcall_hold']))
            $_CALLSTACK = unserialize(noslashes($_REQUEST['httpcall_hold'])); 
        else
            $_CALLSTACK = array(); 
    }
    return $_CALLSTACK; 
}

function httpcall_input() {
    global $_CALLSTACK; 
    httpcall_stack(); 
    if ($_CALLSTACK)
        return end($_CALLSTACK);
    return NULL;                # not being called
}

function httpcall_hold() {
    global $_CALLSTACK; 
    httpcall_stack(); 
    return serialize($_CALLSTACK); 
}

function httpcall_return($retval) {
    global $_CALLSTACK; 
    httpcall_stack(); 
    if ($_CALLSTACK) {
        $top = count($_CALLSTACK) - 1; 
        $_CALLSTACK[$top]['VAL'] = $retval; 
        $ret = $_CALLSTACK[$top]['RET']; 
        unset($_CALLSTACK[$top]['RET']); 
        redirect($ret, array('httpcall_hold' => serialize($_CALLSTACK))); 
    }
    # if not being called, then don't return anything.
}

function httpcall_error($errval) {
    global $_CALLSTACK; 
    httpcall_stack(); 
    if ($_CALLSTACK) {
        $top = count($_CALLSTACK) - 1; 
        $_CALLSTACK[$top]['ERR'] = $errval; 
        $ret = $_CALLSTACK[$top]['RET']; 
        unset($_CALLSTACK[$top]['RET']); 
        redirect($ret, array('httpcall_hold' => serialize($_CALLSTACK))); 
    }
    # if not being called, then don't return anything.
}

function httpcall($call_uri, $args = NULL) {
    global $_CALLSTACK; 
    httpcall_stack(); 
    
    $addr = $_SERVER['PHP_SELF']; 
    if ($_CALLSTACK) {
        $top = end($_CALLSTACK); 
        if (! isset($top['RET'])) {
            # return-back
            array_pop($_CALLSTACK); 
            return $top; 
        }
    }
    
    $frame = $args; 
    if (is_null($frame))
        $frame = array(); 
    $frame['RET'] = url_full($addr); 
    array_push($_CALLSTACK, $frame); 
    
    redirect($call_uri, array('httpcall_hold' => serialize($_CALLSTACK))); 
}

?>