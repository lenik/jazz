<?
/* Common-PHP
 *
 * SMTP Class
 * 
 * $Id: smtp.m4,v 1.1 2005-07-30 05:20:10 dansei Exp $
 * $Log: not supported by cvs2svn $
 */
include(`config.m4')dnl
dnl
require("ext/phpmailer.php"); 

class SMTP extends PHPMailer {
    var $Mailer     = "smtp"; 
    var $Host       = "SMTP_SERVER"; 
    var $Username   = "SMTP_LOGIN"; 
    var $Password   = "SMTP_PASSWORD"; 
    var $SMTPAuth   = SMTP_AUTH; 
    var $From       = "MAIL_FROM"; 
    var $FromName   = "MAIL_NAME"; 
    var $CharSet    = "MAIL_CHARSET"; 
}

?>