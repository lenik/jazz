<?php

class sqltree {
    protected $dbi; 
    protected $table; 
    protected $Bname;
    protected $Dname;
    protected $PRname;
    
    function sqltree($dbi, $table, $Bname = 'b', $Dname = 'd', $PRname = null) {
        $this->dbi = $dbi; 
        $this->table = $table; 
        $this->Bname = $Bname;
        $this->Dname = $Dname;
        $this->PRname = $PRname;
    }

    function add($node, $parent) {
        $dbi = $this->dbi; 
        $table = $this->table; 
        $Bname = $this->Bname; 
        $Dname = $this->Dname;
        $PRname = $this->PRname;

        if (isset($PRname)) {
            $sql = "insert into $table($Bname, $Dname, $PRname)"
                . " values($parent, $node, 0)";
            $dbi->_query($sql); 
            $sql = "insert into $table($Bname, $Dname, $PRname)"
                . " select $Bname, $node, $PRname+1 from $table where $Dname=$parent"; 
            $dbi->_query($sql);
            
        } else {
            $sql = "insert into $table($Bname, $Dname)"
                . " values($parent, $node)";
            $dbi->_query($sql); 
            $sql = "insert into $table($Bname, $Dname)"
                . " select $Bname, $node from $table where $Dname=$parent"; 
            $dbi->_query($sql);
        }
    }

    function remove($node, $recursive = false) {
        if ($recursive) {
            # parents union node
            $B = "select $Bname from $table where $Dname=$node union select $node";
            # children union node
            $D = "select $Dname from $table where $Bname=$node union select $node"; 
            $sql = "delete from $table where $Bname in ($B) and $Dname in ($D)";
            $dbi->_query($sql); 
        } else {
            # parents -> node
            $sql = "delete from $table where $Bname=$node";
            # node -> children
            $sql = "delete from $table where $Dname=$node"; 
        }
    }

    function move($node, $parent) {
        # $old_parent = $dbi->_evaluate
        $sql = "select $Bname from $table where $Dname=$node and $PRname=1"; 
        select $Bname from $table where $Dname=$node
    }

    function build_tree($root_node) {
        die('not implemented, yet');
    }

    function dump_tree($tree) {
        die('not implemented, yet');
    }
}

?>