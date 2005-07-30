<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: base.php,v 1.1 2005-07-30 12:10:23 dansei Exp $
 * $Log: not supported by cvs2svn $
 */
class DBI_base {
    var $server = 'localhost'; 
    var $user = 'root'; 
    var $password; 
    var $dialect; 
    
    function query1($sql) {
        $result = array(); /* for test if any exist record */
        if ($rs = $this->query($sql)) {
            $result = $this->fetch_row($rs); 
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