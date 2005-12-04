<?
/* Common-PHP
 *
 * Database Access Interface
 */
require '_Phpfixes.php'; 
_RequireOnce('string.php'); 
_RequireOnce('dbi/' . DBI_DIALECT . '.php'); 

function sql_connect($persist = true, $debug = false) {
    $dbi = new phpx_dbi(DBI_HOST, DBI_USER, DBI_PASSWORD, DBI_DATABASE, 
        true, $persist, $debug); 
    return $dbi; 
}

?>