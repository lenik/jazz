<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: base.php,v 1.4 2005-07-31 10:52:27 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.3  2005/07/31 04:32:49  dansei
 * iter.1-1
 *
 * Revision 1.2  2005/07/31 02:30:35  dansei
 * includes fix and dev. pack
 *
 * Revision 1.1  2005/07/30 12:10:23  dansei
 * initial
 *
 */
class DBI_base {
    var $_server = 'localhost'; 
    var $_user = 'root'; 
    var $_password; 
    var $_dialect; 
    
    function row($sql) {
        $result = array(); /* for test if any exist record */
        if ($rs = $this->query($sql)) {
            $result = $this->fetch_row($rs); 
            $this->free_result($rs); 
        }
        return $result; 
    }
    
    function assoc($sql) {
        $result = array(); /* for test if any exist record */
        if ($rs = $this->query($sql)) {
            $result = $this->fetch_assoc($rs); 
            $this->free_result($rs); 
        }
        return $result; 
    }
    
    function evaluate($sql) {
        $result = false; 
        if ($rs = $this->query($sql)) {
            if ($row = $this->fetch_row($rs)) {
                $result = $row[0]; 
            }
            $this->free_result($rs); 
        }
        return $result; 
    }
    
    /* import_assoc(this, fetch_assoc()) */
    function import($assoc, $ignores = NULL) {
        if ($assoc) {
            foreach ($assoc as $member=>$value) {
                if (is_null($value)) continue; 
                if ($ignores)
                    if ($ignores[$member]) continue; 
                $this->$member = $value; 
            }
        }
    }
    
    function build_sql_value($val) {
        if (is_string($val))
            return Q($this->escape_string($val)); 
        return $val; 
    }
    
    /* key=value, key=value, ... */
    function build_pairs($assoc, $ignores = NULL, $separator = ', ', $delim = '=') {
        $pairs = ''; 
        foreach ($assoc as $k=>$v) {
            if (substr($k, 0, 1) == '_') continue; 
            if (is_null($v)) continue; 
            if ($ignores)
                if ($ignores[$k]) continue; 
            if ($pairs)
                $pairs .= $separator; 
            $pairs .= "$k$delim".$this->build_sql_value($v); 
        }
        return $pairs; 
    }
    
    function build_debug_info($assoc, $ignores = NULL) {
        return build_pairs($assoc, $ignores, "\n", " = "); 
    }
    
    /* (key, key, ...) values (value, value, ...) */
    function build_insert_values($assoc, $ignores = NULL) {
        $keys = ''; 
        $values = ''; 
        foreach ($assoc as $k=>$v) {
            if (substr($k, 0, 1) == '_') continue; 
            if (is_null($v)) continue; 
            if ($ignores)
                if ($ignores[$k]) continue; 
            if ($keys) {
                $keys .= ', '; 
                $values .= ', '; 
            }
            $keys .= $k; 
            $values .= $this->build_sql_value($v); 
        }
        return "($keys) values($values)"; 
    }
}
?>