<?
/* Common-PHP
 *
 * SMTP Class
 * 
 * $Id: smtp.php,v 1.3 2005-07-31 02:30:34 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/07/30 05:20:10  dansei
 * initial
 *
 */
require("ext/phpmailer.php"); 

class SMTP extends PHPMailer {
    var $Mailer     = "smtp"; 
    var $Host       = "localhost"; 
    var $Username   = "mlv"; 
    var $Password   = "no"; 
    var $SMTPAuth   = true; 
    var $From       = "root"; 
    var $FromName   = "root"; 
    var $CharSet    = "UTF-8"; 
}

?>