<?php

require_once dirname(__FILE__) . '/lang.php'; 
require_once dirname(__FILE__) . '/graph.php'; 

# [type [.name] ] user-friendly [: detail...]
# name is used for internationalization. 
global $PHPX_ERROR_FORMAT; 
$PHPX_ERROR_FORMAT = '/^\s*(?:\[(\w+)(?:\.(\w+))?\]\s*)?(.+?)(?::\s+(.+))?$/'; 

define('PHPX_EM_TERM', 1); 

class phpx_error {
    public $time; 
    public $provider; 
    public $type; 
    public $name; 
    public $text; 
    public $detail; 
    public $source; 
    public $source_status; 
    public $cause; 
    
    function phpx_error($provider, $summary, $source = null, $cause = null) {
        global $PHPX_ERROR_FORMAT; 
        
        $this->time = time(); 
        $this->provider = $provider; 
        
        if (! is_null($summary)) {
            if (! preg_match($PHPX_ERROR_FORMAT, $summary, $matches))
                die("Illegal error-summary syntax: $summary"); 
            $type = $matches[1]; 
            $name = $matches[2]; 
            $text = $matches[3]; 
            $detail = $matches[4]; 
            
            $this->text = $text; 
            if ($type) $this->type = $type; 
            if ($name) $this->name = $name; 
            if ($detail) $this->detail = $detail; 
        }
        
        if ($this->source = $source)
            if (method_exists($source, '_source_status'))
                $this->source_status = $source->_source_status(); 
        
        if ($cause != null) {
            $this->cause = $cause; 
            phpx_or($this, $cause); 
        }
    }
    
    function summary($detail = false) {
        $buf = $this->type; 
        if ($this->name)
            $buf .= '.' . $this->name; 
        if ($buf)
            $buf = "[$buf] "; 
        $buf .= $this->text; 
        if ($detail && $this->detail)
            $buf .= ': ' . $this->detail; 
        return $buf; 
    }
}

class phpx_error_manager extends phpx_node {
    public $pref = null;               # preference
    private $errors; 
    private $mark;                     # for range select
    
    function phpx_error_manager($provider = null, $pref = null) {
        global $PHPX_EM_NEXT; 
        if ($provider == null)
            $provider = 'EM/' . $PHPX_EM_NEXT++; 
        $this->name = $provider; 
        $this->pref = $pref; 
    }
    
    function register() {
        # BUGFIX: PHP $this unusable in constructor. 
        phpx_error_manager_register($this); 
    }
    
    function unregister() {
        phpx_error_manager_unregister($this->name); 
    }
    
    # @final
    function process($summary, $source = null, $cause = null) {
        $e = new phpx_error($this->name, $summary, $source, $cause); 
        $this->errors[] = &$e; 
        
        $next = $this; 
        while ($next != null) {
            $next->handler($e); 
            $next = $next->next; 
        }
    }
    
    # @override
    function handler(&$error) {
        # Default Handler. 
        error_log($error->summary(true)); 
    }
    
    function mark() {
        return $this->mark = sizeof($this->errors); 
    }
    
    function select($cut = false, $mark = null) {
        if ($mark == null)
            $mark = $this->mark; 
        if ($mark == null) {
            $mark = sizeof($this->errors) - 1; 
            if ($mark < 0) $mark = 0; 
        }
        if ($cut)
            return array_splice($this->errors, $mark); 
        else
            return array_slice($this->errors, $mark); 
    }
    
    function dump() {
        foreach ($this->errors as $error) {
            # dumping...
        }
    }
}

global $PHPX_EM_REGISTRY; 
$PHPX_EM_REGISTRY = new phpx_graph(); 

function phpx_error_manager_register(&$em) {
    global $PHPX_EM_REGISTRY; 
    global $PHPX_EM_LAST_TERM; 
    
    if (! $PHPX_EM_REGISTRY->add($em))
        die("Failed to register error-manager $em->name"); 
    
    if ($em->next == null && $PHPX_EM_LAST_TERM != null)
        $em->next = &$PHPX_EM_LAST_TERM; 
        
    if ($em->pref & PHPX_EM_TERM) {
        # connect leaves if exist (except the term self. )
        foreach ($PHPX_EM_REGISTRY->nodes as $name=>$node) {
            if ($node->next == null) {
                # Leaf, Avoid loopping. 
                $connected = $PHPX_EM_REGISTRY->connected($node); 
                if (! array_key_exists($em->name, $connected))
                    $PHPX_EM_REGISTRY->nodes[$name]->next = &$em; 
            }
        }
        $PHPX_EM_LAST_TERM = &$em; 
    }
}

function phpx_error_manager_unregister($manager_name) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->remove($manager_name); 
}

function &phpx_error_manager_get($provider) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->get($provider); 
}

function phpx_error_manager_connect(&$from, &$to) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->connect($from, $to); 
}

function phpx_error($provider, $summary, $source = null, $cause = null) {
    $em = &phpx_error_manager_get($provider); 
    assert($em != null); 
    return $em->process($summary, $source, $cause); 
}

class phpx_error_support {
    private $_debug; 
    private $_em; 
    
    function phpx_error_support($provider) {
        assert(is_string($provider)); 
        $this->_em = &phpx_error_manager_get($provider); 
        if (is_null($this->_em)) {
            $this->_em = new phpx_error_manager($provider); 
            $this->_em->register(); 
        }
    }
    
    function _add_type($type, $summary) {
        global $PHPX_ERROR_FORMAT; 
        if (! preg_match($PHPX_ERROR_FORMAT, $summary, $matches))
            die("Illegal summary syntax: $summary"); 
        if ($name = $matches[1]) {
            if ($matches[2])
                $name .= '.' . $matches[2]; 
            $type .= '.' . $name; 
        }
        if ($type)
            $type = "[$type] "; 
        $text = $matches[3]; 
        if ($detail = $matches[4])
            $text .= ': ' . $detail; 
        return $type . $text; 
    }
    
    function _info($summary) {
        if (! $this->_debug) return true; 
        error_log($summary); 
    }
    
    function _warn($summary) {
        if ($this->_debug) {
            # $summary = $this->_add_type('WARN', $summary); 
            return $this->_em->process($summary, $this); 
        } else {
            error_log($summary); 
        }
    }
    
    function _err($summary) {
        # $summary = $this->_add_type('ERR', $summary); 
        return $this->_em->process($summary, $this); 
    }
}

?>