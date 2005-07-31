<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: base.php,v 1.2 2005-07-31 02:30:35 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/07/30 12:10:23  dansei
 * initial
 *
 */
class DBI_base {
    var $server = 'localhost'; 
    var $user = 'root'; 
    var $password; 
    var $dialect; 
    
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
}
?>