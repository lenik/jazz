<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: base.php,v 1.6 2005-08-05 14:34:10 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.5  2005/08/03 14:42:18  dansei
 * dev pack.
 *
 * Revision 1.4  2005/07/31 10:52:27  dansei
 * moved sql-related string operations from string.php to here.
 *
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
require '_Phpfixes.php'; 
_RequireOnce('../string.php'); 

class DBI_base {
    var $_server = 'localhost'; 
    var $_user = 'root'; 
    var $_password; 
    var $_dialect; 
    var $_debug = false; 
    
    function _Row($sql) {
        $result = array(); /* for test if any exist record */
        if ($rs = $this->_Query($sql)) {
            $result = $this->_FetchRow($rs); 
            $this->_FreeResult($rs); 
        }
        return $result; 
    }
    
    function _Assoc($sql) {
        $result = array(); /* for test if any exist record */
        if ($rs = $this->_Query($sql)) {
            $result = $this->_RetchAssoc($rs); 
            $this->_FreeResult($rs); 
        }
        return $result; 
    }
    
    function _Evaluate($sql) {
        $result = false; 
        $ret = $this->_Query($sql); 
        if (is_bool($ret))
            return $ret; 
        if ($row = $this->_FetchRow($ret))
            $result = $row[0]; 
        $this->_FreeResult($ret); 
        return $result; 
    }
}
?>