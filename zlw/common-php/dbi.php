<?
/* Common-PHP
 *
 * Database Access Interface
 */
require '_Phpfixes.php'; 
_RequireOnce('string.php'); 
_RequireOnce('dbi/' . DBI_DIALECT . '.php'); 

function sql_connect($host = DBI_HOST, 
                     $user = DBI_USER, 
                     $password = DBI_PASSWORD, 
                     $database = DBI_DATABASE, 
                     $persist = true, 
                     $debug = false) {
    $dbi = new phpx_dbi($host, $user, $password, $database, $persist); 
    if ($debug)
        $dbi->_debug = true; 
    $dbi->_connect(); 
    return $dbi; 
}

?>