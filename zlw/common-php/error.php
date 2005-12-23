<?php

require_once dirname(__FILE__) . '/lang.php'; 
require_once dirname(__FILE__) . '/graph.php'; 

# [type [.name] ] user-friendly [: detail...]
# name is used for internationalization. 
global $PHPX_ERROR_FORMAT; 
$PHPX_ERROR_FORMAT = '/^\s*(?:\[(\w+)(?:\.(\w+))?\]\s*)?(.+?)(?::\s+(.+))?$/'; 

define('PHPX_EM_TERM', 1); 

class phpx_error {
    var $time; 
    var $provider; 
    var $type; 
    var $name; 
    var $text; 
    var $detail; 
    var $source; 
    var $source_status; 
    var $cause; 
    
    function phpx_error($summary, $provider = NULL, $cause = NULL) {
        global $PHPX_ERROR_FORMAT; 
        assert($summary != NULL); 
        
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
        
        $this->time = time(); 
        $this->provider = $provider; 
        if ($cause != NULL) {
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
    var $pref = NULL; 
    var $errors; 
    var $mark; 
    
    function phpx_error_manager($provider = NULL, $pref = NULL) {
        global $PHPX_EM_NEXT; 
        if ($provider == NULL)
            $provider = 'EM/' . $PHPX_EM_NEXT++; 
        $this->name = $provider; 
        $this->pref = $pref; 
    }
    
    function register() {
        # BUGFIX: PHP $this unusable in constructor. 
        phpx_error_manager_register($this); 
    }
    
    function unregister() {
        phpx_error_manager_unregister($this); 
    }
    
    # @final
    function process($summary, $source = NULL, $cause = NULL) {
        $e = new phpx_error($summary, $this->name, $cause); 
        if ($e->source = $source)
            if (method_exists($source, 'source_status'))
                $e->source_status = $source->source_status(); 
                
        $this->errors[] = &$e; 
        
        $next = $this; 
        while ($next != NULL) {
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
    
    function select($cut = false, $mark = NULL) {
        if ($mark == NULL)
            $mark = $this->mark; 
        if ($mark == NULL) {
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
    
    if ($em->next == NULL && $PHPX_EM_LAST_TERM != NULL)
        $em->next = &$PHPX_EM_LAST_TERM; 
        
    if ($em->pref & PHPX_EM_TERM) {
        # connect leaves if exist (except the term self. )
        foreach ($PHPX_EM_REGISTRY->nodes as $name=>$node) {
            if ($node->next == NULL) {
                # Leaf, Avoid loopping. 
                $connected = $PHPX_EM_REGISTRY->connected($node); 
                if (! array_key_exists($em->name, $connected))
                    $node->next = &$em; 
            }
        }
        $PHPX_EM_LAST_TERM = &$em; 
    }
}

function phpx_error_manager_unregister(&$manager) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->remove($manager); 
}

function &phpx_error_manager_get($provider) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->get($provider); 
}

function phpx_error_manager_connect(&$from, &$to) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->connect($from, $to); 
}

function phpx_error($provider, $summary, $source = NULL, $cause = NULL) {
    $em = phpx_error_manager_get($provider); 
    assert($em != NULL); 
    return $em->process($summary, $source, $cause); 
}

?>