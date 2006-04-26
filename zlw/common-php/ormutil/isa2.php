<?php

class sqltree {
    protected $dbi; 
    protected $table; 
    protected $Bname;                   # Base
    protected $Dname;                   # Derived
    protected $Iname;                   # Indirection
    
    function sqltree($dbi, $table, $Bname = 'b', $Dname = 'd', $Iname = null) {
        $this->dbi = $dbi; 
        $this->table = $table; 
        $this->Bname = $Bname;
        $this->Dname = $Dname;
        $this->Iname = $Iname;
    }
    
    function hasA($b, $d) {
        return $this->isA($d, $b);
    }
    
    function isA($d, $b) {
        $dbi = $this->dbi; 
        $table = $this->table; 
        $Bname = $this->Bname; 
        $Dname = $this->Dname; 
        $Iname = $this->Iname; 
        if (isset($Iname)) {
            $rs = $dbi->_query("select $Iname from $table"
                               . " where $Bname=$b and $Dname=$d");
            assert($rs !== false);      # assert(sql syntax ok)
            $row = $dbi->_fetch_row($rs);
            $dbi->_free_result($rs);
            if ($row !== false)         # not empty
                return $row[0] + 1;     # returns 1 for direct
        } else {
            $rs = $dbi->_query("select count(*) from $table"
                               . " where $Bname=$b and $Dname=$d");
            assert($rs !== false);      # assert(sql syntax ok)
            $row = $dbi->_fetch_row($rs);
            $dbi->_free_result($rs);
            assert($row !== false);     # assert(not empty)
            if ($row[0] == 0) return false;
            assert($row[0] == 1);       # assert(1 row max)
            return true; 
        }
        return false; 
    }
    
    function add($node, $parent) {
        $dbi = $this->dbi; 
        $table = $this->table; 
        $Bname = $this->Bname; 
        $Dname = $this->Dname; 
        $Iname = $this->Iname; 
        
        if (isset($Iname)) {
            # add: new I(parent -> x) = 1
            $sql = "insert into $table($Bname, $Dname, $Iname)"
                . " values($parent, $node, 1)";
            $dbi->_query($sql);
            # add: new I(B+(parent) -> x) = I(B+(parent) -> parent) + 1
            $sql = "insert into $table($Bname, $Dname, $Iname)"
                . " select $Bname, $node, $Iname+1 from $table"
                . " where $Dname=$parent"; 
            $dbi->_query($sql); 
        } else {
            # add: parent -> x
            $sql = "insert into $table($Bname, $Dname)"
                . " values($parent, $node)";
            $dbi->_query($sql);
            # add: B+(parent) -> x
            $sql = "insert into $table($Bname, $Dname)"
                . " select $Bname, $node from $table where $Dname=$parent"; 
            $dbi->_query($sql); 
        }
    }
    
    function remove($node, $recursive = false) {
        $dbi = $this->dbi; 
        $table = $this->table; 
        $Bname = $this->Bname; 
        $Dname = $this->Dname;
        $Iname = $this->Iname;
        
        if ($recursive) {
            # delete: B*(x) -> D*(x)
            $B = "select $Bname from $table where $Dname=$node"
                . " union select $node";
            $D = "select $Dname from $table where $Bname=$node"
                . " union select $node"; 
            $sql = "delete from $table"
                . " where $Bname in ($B) and $Dname in ($D)";
            $dbi->_query($sql); 
        } else {
            if (isset($Iname)) {
                # I( B+(x)->D+(x) )--
                $B = "select $Bname from $table where $Dname=$node";
                $D = "select $Dname from $table where $Bname=$node";
                $sql = "update $table set $Iname=$Iname-1"
                    . " where $Bname in ($B) and $Dname in ($D)"; 
            }
            # delete: B+(x) -> x  (keep B+(x) -> D+(x))
            $sql = "delete from $table where $Dname=$node";
            # delete: x -> D+(x)
            $sql = "delete from $table where $Bname=$node";
        }
    }
    
    function move($node, $parent) {
        $new = $parent; 
        if (isset($Iname)) {
            # B*(o), B*(n), B*(r)
            $Bo = "select $Bname from $table where $Dname=$node"
                . " order by $Iname asc";
            $Bn = "select $Bname from $table where $Dname=$new"
                . " order by $Iname asc union select $new";
            $Bcom = "select L.$Bname as LB, R.$Bname as RB"
                . " from ($Bo) as L, ($Bn) as R where L.$Bname=R.$Bname";
            
            $Idelta = "select ($Bn) - ($Box_N) as Delta";
            
            # { B*(o) - B*(r) }, { B*(n) - B*(r) }
            $Box = "select $Bname from ($Bo) where $Bname not in ($Bcom)";
            $Bnx = "select $Bname from ($Bn) where $Bname not in ($Bcom)";
            
            # D+(x)
            $D = "select $Dname from $table where $Bname=$node";
            $D_inc = "$D union select $node"; 
            
            # delete: { B*(o) - B*(r) } -> D*(x)
            $sql = "delete from $table"
                . " where $Bname in ($Box) and $Dname in ($D_inc)";
            
            # add: { B*(n) - B*(r) } -> D*(x)
            #   => add.1: B*(n) -> x
            #      add.2:    n  -> D+(x)
            #      add.3: B+(n) -> D+(x)

            $Idelta = "select count"; 
            # add.1
            $this->add($node, $new);
            
            # add.2: new I(n->D+(x)) := I(x->D+(x)) + 1
            $sql = "insert into $table($Bname, $Dname, $Iname)"
                . " select $new, $Dname, $Iname+1 from $table"
                . " where $Bname=$node and $Dname in ($D)";
            
            # add.3: new I(B+(n)->D+(x)) :=
            #            I(B+(n)->x    ) +
            #            I(    x->D+(x))
            insert into $table($Bname, $Dname, $Iname)
                select $Bname
                from $table
                where $Bname in ($Bnx) and $Dname in ($D)
        } else {
            $B0 = "select $Dname from $table"
                . " where $Bname in ($B) and $Dname in ($B)"
                . " group by $Bname"
                . " order by count($Dname) desc"
                . " limit 1"; 
        }
        # remove old -> node, add new -> node
        
        $sql = "select $Bname from $table where $Dname=$node and $Iname=1"; 
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