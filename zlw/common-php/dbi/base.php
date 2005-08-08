<?
/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: base.php,v 1.8 2005-08-08 08:05:40 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.7  2005/08/07 13:02:48  dansei
 * refactor complete.
 *
 * Revision 1.6  2005/08/05 14:34:10  dansei
 * devpack: change to  php-data-object framework
 *
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

abstract class phpx_dbi_base {
    var $_server = 'localhost'; 
    var $_user = 'root'; 
    var $_password; 
    var $_dialect; 
    var $_debug = false; 
    
    function _row($sql) {
        $result = array(); /* for test if any exist record */
        if ($rs = $this->_query($sql)) {
            $result = $this->_fetch_row($rs); 
            $this->_free_result($rs); 
        }
        return $result; 
    }
    
    function _assoc($sql) {
        $result = array(); /* for test if any exist record */
        if ($rs = $this->_query($sql)) {
            $result = $this->_fetch_assoc($rs); 
            $this->_free_result($rs); 
        }
        return $result; 
    }
    
    function _evaluate($sql) {
        $result = false; 
        $ret = $this->_query($sql); 
        if (is_bool($ret))
            return $ret; 
        if ($row = $this->_fetch_row($ret))
            $result = $row[0]; 
        $this->_free_result($ret); 
        return $result; 
    }
}
?>