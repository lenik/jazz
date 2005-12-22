<?php

class phpx_node {
    var $name; 
    var $next; 
}

class phpx_graph {
    var $nodes; 
    var $def = NULL; 
    
    function add(&$node) {
        assert($node != NULL); 
        if (array_key_exists($node->name, $this->nodes))
            return false; 
        $nodes[$node->name] = &$node; 
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
    
    function connected(&$node) {
        assert($node != NULL); 
        
        $set = array(); 
        while ($node != NULL) {
            assert(array_key_exists($node, $this->nodes)); 
            assert(! array_key_exists($node, $set)); 
            $set[$node->name] = &$node; 
            $node = &$node->next; 
        }
        return $set; 
    }
    
    function connect_leaves($term) {
        assert($term != NULL); 
        
        if (! $this->add($term))
            return false; 
            
        foreach ($this->nodes as $name=>$node) {
            if ($node->next == NULL) {
                # Leaf. 
                # Avoid loopping. 
                $connected = $this->connected($node); 
                if (! array_key_exists($term->name, $connected))
                    $node->next = $term; 
            }
        }
        return true; 
    }
    
    function connect($from, $to) {
        assert($from != NULL); 
        assert(array_key_exists($from->name, $this->nodes)); 
        
        if ($to != NULL) {
            assert(array_key_exists($to->name, $this->nodes)); 
            # recursive check.
            $connected = $this->connected($from); 
            if (array_key_exists($to, $connected))
                return false; 
        }
        
        $from->next = $to; 
        return true; 
    }
}
