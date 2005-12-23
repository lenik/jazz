<?php

class phpx_node {
    var $name; 
    var $next; 
    
    function phpx_node($name = NULL) {
        $this->name = $name; 
    }
}

class phpx_graph {
    var $nodes = array(); 
    var $def = NULL; 
    
    function add(&$node) {
        assert($node != NULL); 
        if (array_key_exists($node->name, $this->nodes))
            return false; 
        $this->nodes[$node->name] = &$node; 
        return true; 
    }
    
    function remove($node) {
        assert ($node != NULL); 
        
        if (gettype($node) == 'object')
            $name = $node->name; 
        else
            $name = $node; 
        
        if (! array_key_exists($name, $this->nodes))
            return false; 
        
        unset($this->nodes[$name]); 
        
        # clear connection
        foreach ($this->nodes as $n) {
            if ($n->next->name == $name)
                unset($n->next); 
        }
        
        return true; 
    }
    
    function &get($name) {
        if (array_key_exists($name, $this->nodes))
            return $this->nodes[$name]; 
        return $def; 
    }
    
    function connected($start) {
        assert($start != NULL); 
        
        $set = array(); 
        $node = $start; 
        while ($node != NULL) {
            assert(array_key_exists($node->name, $this->nodes)); 
            assert(! array_key_exists($node->name, $set)); 
            $set[$node->name] = true; # &$node; 
            $node = $node->next; 
        }
        return $set; 
    }
    
    function connect(&$from, &$to) {
        assert($from != NULL); 
        assert(array_key_exists($from->name, $this->nodes)); 
        
        if ($to != NULL) {
            # add if not existed. 
            if (! array_key_exists($to->name, $this->nodes))
                $this->add($to); 
                
            # recursive check.
            $connected = $this->connected($from); 
            if (array_key_exists($to->name, $connected))
                return false; 
        }
        
        $from->next = &$to; 
        return true; 
    }
}
