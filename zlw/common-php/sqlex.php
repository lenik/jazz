<?php

class phpx_sqlex {
    protected $dbi;
    function phpx_sqlex($dbi) {
        $this->dbi = $dbi;
    }
    function pre($sql) {
        return $sql;                    # do nothing about sql
    }
    function post($result) {
        return $result;                 # do nothing about result
    }
}

function phpx_sqlex_register($name, $clsname) {
    global $PHPX_SQLEX_REGISTRY;
    $PHPX_SQLEX_REGISTRY[$name] = $clsname; 
}

function phpx_sqlex_unregister($name) {
    global $PHPX_SQLEX_REGISTRY;
    unset($PHPX_SQLEX_REGISTRY[$name]);
}

# callname ::= name [ '(' args ')' ]
function phpx_sqlex_get($callname, $dbi = null) {
    global $PHPX_SQLEX_REGISTRY;
    if (($parg = strpos($callname, '(')) !== false) {
        $name = trim(substr($callname, 0, $parg)); 
        $args = '($dbi, ' . trim(substr($callname, $parg + 1)); 
    } else {
        $name = trim($callname);
        $args = '($dbi)';
    }
    $clsname = $PHPX_SQLEX_REGISTRY[$name];
    # opt: sqlex-pool
    $object = eval("return new $clsname$args; "); 
    return $object; 
}

# .section.  built-in sqlexes

# %% page($page, $page_size, $view_size) %
class phpx_sqlex_page extends phpx_sqlex {
    private $page;
    private $page_size;
    private $view_size; 
    private $total;                     # appx.
    
    function phpx_sqlex_page($dbi, $page = 0, $page_size = 10,
                                 $view_size = null) {
        parent::phpx_sqlex($dbi); 
        $this->page = $page;
        $this->page_size = $page_size;
        if (is_null($view_size))
            $view_size = $page_size; 
        $this->view_size = $view_size; 
    }
    
    function pre($sql) {
        $offset = $this->page * $this->page_size; 
        return $sql . " limit $offset, $this->view_size"; 
    }

    function post($result) {
        # refresh counts?
        return $result; 
    }
}

phpx_sqlex_register('page', 'phpx_sqlex_page');

?>