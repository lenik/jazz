<?
/*
 * warning:  this file is automatically generated by m4 scripts.  please do not edit this file, you may lose all your changes in this file after the original file is re-built.  
 */

/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: dbi.php,v 1.8 2005-08-08 08:05:40 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.6  2005/08/07 13:02:46  dansei
 * refactor complete.
 *
 * Revision 1.5  2005/08/05 14:34:10  dansei
 * devpack: change to  php-data-object framework
 *
 * Revision 1.4  2005/08/05 06:03:31  dansei
 * dev pack.
 *
 * Revision 1.3  2005/08/03 14:42:17  dansei
 * dev pack.
 *
 * Revision 1.2  2005/07/31 04:32:48  dansei
 * iter.1-1
 *
 * Revision 1.1  2005/07/30 12:10:22  dansei
 * initial
 *
 */
require '_Phpfixes.php'; 
_RequireOnce('string.php'); 
_RequireOnce('dbi/mysql.php'); 

class phpx_dbi extends phpx_dbi_mysql {
    var $_server = 'localhost'; 
    var $_user = 'root'; 
    var $_password = 'l.'; 
    var $_link = NULL; 
    
    function _reuse($dbi) {
    	$this->_link = $dbi->_link; 
    }
    
    function _query($sql) {
        if (is_null($this->_link))
            $this->_connect(); 
            
        $this->_debug && logger("SQL: $sql", false); 
        $ret = parent::_query($sql); 
        
        if ($this->_debug) {
            if ($ret) logger_end(" => succeeded, $ret"); 
            else logger_end(" => failed, ", $this->_error()); 
        }
        return $ret; 
    }
}
?>