<?php

class phpx_node {
    public $name; 
    public $next; 
    
    function phpx_node($name = null) {
        $this->name = $name; 
    }
}

class phpx_graph {
    public $nodes = array(); 
    private $def = null; 
    
    function add(&$node) {
        assert($node != null); 
        if (array_key_exists($node->name, $this->nodes))
            return false; 
        $this->nodes[$node->name] = &$node; 
        return true; 
    }
    
    function remove($name) {
        assert ($name != null); 
        
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
        return $this->def; 
    }
    
    function connected($start) {
        assert($start != null); 
        
        $set = array(); 
        $node = $start; 
        while ($node != null) {
            assert(array_key_exists($node->name, $this->nodes)); 
            assert(! array_key_exists($node->name, $set)); 
            $set[$node->name] = true; # &$node; 
            $node = $node->next; 
        }
        return $set; 
    }
    
    function connect(&$from, &$to) {
        assert($from != null); 
        assert(array_key_exists($from->name, $this->nodes)); 
        
        if ($to != null) {
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
