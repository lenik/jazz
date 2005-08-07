m4_include(`config-database.m4')
M4X_BEGIN()
m4x_lazydef(`DB_DIALECT', `mysql')
M4X_END()
<?
/*
 * M4X___ORIGINAL_FILE__
 */

/* Common-PHP
 *
 * Database Access Interface
 * 
 * $Id: dbi.m4,v 1.6 2005-08-07 13:02:46 dansei Exp $
 * $Log: not supported by cvs2svn $
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
_RequireOnce('dbi/DB_DIALECT.php'); 

class phpx_dbi extends `phpx_dbi_'DB_DIALECT {
    var $_server = 'DB_SERVER'; 
    var $_user = 'DB_USER'; 
    var $_password = 'DB_PASSWORD'; 
    var $_link = NULL; 
    
    function _Reuse($dbi) {
    	$this->_link = $dbi->_link; 
    }
    
    function _Query($sql) {
        if (is_null($this->_link))
            $this->_Connect(); 
            
        $this->_debug && logger("SQL: $sql", false); 
        $ret = parent::_Query($sql); 
        
        if ($this->_debug) {
            if ($ret) logger_end(" => succeeded, $ret"); 
            else logger_end(" => failed, ", $this->_Error()); 
        }
        return $ret; 
    }
}
?>