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
            # LC = B*(old)
            $LC = "select $Bname t from $table where $Dname=$node"
                . " order by $Iname desc"; 
            # RC = B*(new)
            $RC = "select $Bname t from $table where $Dname=$new union $new"
                . " order by $Iname desc"; 
            
            # L = LC - RC = { B*(o) - B*(r) }
            $L = "select t from ($LC) where t not in ($RC)";
            # R = RC - LC = { B*(n) - B*(r) }
            $R = "select t from ($RC) where t not in ($LC)";
            # C = LC int. RC
            $C = "select t from ($LC) where t in ($RC)";
            
            # D+(x), D*(x)
            $D = "select $Dname t from $table where $Bname=$node";
            $D_inc = "$D union select $node"; 
            
            # delete: L -> D*(x)
            $sql = "delete from $table"
                . " where $Bname in ($L) and $Dname in ($D_inc)";
            
            # Idelta = |R| - |L|
            $Idelta = "select ("
                . " (select count(*) from ($R) R) - "
                . " (select count(*) from ($L) L)) delta"; 
            
            # set: I(C -> D*(x)) += Idelta
            $sql = "update $table set $Iname=$Iname+($Idelta)"
                . " where $Bname in ($C) and $Dname in ($D_inc)"; 
            
            # add: new I(n -> x) := 1
            #      new I(R -> x) := I(R -> n) + 1
            $sql = "insert into $table($Bname, $Dname, $Iname)"
                . " select $new, $node, 1 union"
                . " select $Bname, $node, $Iname+1 from $table"
                . " where $Bname in ($R) and $Dname=$new";
            
            # Ri = I(R -> x)
            $Ri = "select $Bname t, $Iname i from $table"
                . " where $Bname in ($R) and $Dname=$node";
            # Di = I(x -> D+(x))
            $Di = "select $Dname t, $Iname i from $table"
                . " where $Bname=$node and $Dname in ($D)"; 
            # add: new I(R -> D+(x)) = I(R -> x) + I(x -> D+(x))
            $sql = "insert into $table($Bname, $Dname, $Iname)"
                . " select R.t $Bname, D.t $Dname, (R.i+D.i) $Iname"
                . " from ($Ri) R, ($Di) D";
        } else {
        }
    }

    function build_tree($root_node) {
        die('not implemented, yet');
    }

    function dump_tree($tree) {
        die('not implemented, yet');
    }
}

?>