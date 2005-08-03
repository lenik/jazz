<?
include(`config.m4')dnl
/*
 * __ORIGINAL_FILE__
 */

/* Common-PHP
 *
 * SMTP Class
 * 
 * $Id: mail.m4,v 1.1 2005-08-05 06:03:32 dansei Exp $
 * $Log: not supported by cvs2svn $
 * Revision 1.1  2005/07/30 05:20:10  dansei
 * initial
 *
 */
require '_Phpfixes.php'; 
_RequireOnce("ext/phpmailer.php"); 

class Mail extends PHPMailer {
    var $Mailer     = 'smtp'; 
    var $Host       = 'SMTP_SERVER'; 
    var $Username   = 'SMTP_LOGIN'; 
    var $Password   = 'SMTP_PASSWORD'; 
    var $SMTPAuth   = SMTP_AUTH; 
    var $From       = 'MAIL_FROM'; 
    var $FromName   = 'MAIL_NAME'; 
    var $CharSet    = 'MAIL_CHARSET'; 
    var $ReplyTo    = array('MAIL_REPLY'); 
}

?>