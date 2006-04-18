<?php
/* Common-PHP
 *
 * HTTP Functions
 */

function phpx_transient() {
    header('Cache-Control: no-cache'); 
}

# remove slashes only if magic-quotes is enabled
function phpx_noslashes($value) {
    if (get_magic_quotes_gpc()) {
        switch (gettype($value)) {
        case 'string': 
            $value = stripslashes($value); 
            break; 
        case 'array': 
            $newv = array(); 
            foreach ($value as $k=>$v) {
                $newv[$k] = phpx_noslashes($v); 
            }
            $value = $newv; 
            break; 
        }
    }
    return $value; 
}

function phpx_this_host($https = null) {
    if (is_null($https)) {
        $proto = 'http'; 
        if ($_SERVER['HTTPS'] == 'on')
            $proto .= 's'; 
    } else {
        $proto = $https ? 'https' : 'http'; 
    }
    return "$proto://$_SERVER[HTTP_HOST]";
}

function phpx_this_url($https = null) {
    $url = $_SERVER['REQUEST_URL']; 
    if (strstr($url, '://')) {
        return $url; 
    }
    
    $host = phpx_this_host($https); 
    if (substr($url, 0, 1) != '/') {
        $url = "/$url"; 
    }
    return "$host$usl"; 
}

# concat this-url with specfied relative-url
function phpx_url_relative($url = '', $https = null) {
    $ret = phpx_this_host($https); 
    
    # http://server/dir/this [?...]
    $self = $_SERVER['PHP_SELF']; 
    if ($url == '' || substr($url, 0, 1) == '?')
        return "$ret$self$url"; 
    
    # http://server/dir
    $dir = dirname($self); 
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

# concat this-url with specfied full-url
function phpx_url_full($url, $https = null) {
    # url-absolute
    if ($right = strstr($url, '://')) {
        if (is_null($https))
            return $url; 
        else
            return ($https ? 'https' : 'http') . $right; 
    }
    
    # url-full
    $ret = phpx_this_host($https); 
    
    # http://host/url
    if (substr($url, 0, 1) != '/')
        $url = "/$url"; 
    
    return "$ret$url"; 
}

function phpx_url_arguments($url, $args) {
    if ($args == null)
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

function phpx_redirect_relative($url = '', $args = null, $https = null) {
    $url = phpx_url_relative($url, $https); 
    header("Location: ".phpx_url_arguments($url, $args)); 
    exit; 
}

function phpx_redirect($url, $args = null, $https = null) {
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
    return null;                # not being called
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

function phpx_httpcall($call_uri, $args = null) {
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

class phpx_http_buffer {
    var $_bufmode;
    var $_buf; 
    
    function buffer_start() {
        $this->_bufmode = true; 
        $this->_buf = ''; 
    }
    
    function buffer_end() {
        if ($this->_bufmode) {
            $this->_bufmode = false; 
            return $this->_buf; 
        }
        return ''; 
    }
    
    function output() {
        $args = func_get_args(); 
        if ($this->_bufmode)
            $this->_buf = join('', $args); 
        else
            echo join('', $args); 
    }
}

?>