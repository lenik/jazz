<?php

require_once dirname(__FILE__) . '/lang.php'; 
require_once dirname(__FILE__) . '/graph.php'; 

# [type [.name] ] user-friendly [: detail...]
# name is used for internationalization. 
global $PHPX_ERROR_FORMAT; 
$PHPX_ERROR_FORMAT = '/^\s*(?:\[(\w+)(?:\.(\w+))?\]\s*)?(.+?)(?::\s+(.+))?$/'; 

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
    var $errors; 
    var $mark; 
    
    function phpx_error_manager($provider = NULL) {
        global $PHPX_EM_NEXT; 
        if ($provider == NULL)
            $provider = 'EM/' . $PHPX_EM_NEXT++; 
        $this->name = $provider; 
        phpx_error_manager_register($this); 
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

function phpx_error_manager_register($manager) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->add($manager); 
}

function phpx_error_manager_unregister($manager) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->remove($manager); 
}

function &phpx_error_manager_get($provider) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->get($provider); 
}

function phpx_error_manager_connect_leaves($term_manager) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->connect_leaves($term_manager); 
}

function phpx_error_manager_connect($from, $to) {
    global $PHPX_EM_REGISTRY; 
    return $PHPX_EM_REGISTRY->connect($from, $to); 
}

function phpx_error_dispatch($provider, $name = NULL, $type = 'error', $text = NULL, 
        $source = NULL, $next = NULL) {
    $next = $provider; 
    while ($next != NULL) {
        $em = phpx_error_manager_get($provider); 
        $error = new phpx_error(); 
        $em->add($error); 
        $next = phpx_error_manager_connect($next); 
    }
}

?>