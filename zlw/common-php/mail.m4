m4_include(`config.m4')
<?php
/*
 * M4X___ORIGINAL_FILE__
 */

/* Common-PHP
 *
 * SMTP Class
 */
require '_Phpfixes.php'; 
_RequireOnce("ext/phpmailer.php"); 

class phpx_mail extends PHPMailer {
    var $Mailer     = 'smtp'; 
    var $Host       = 'SMTP_SERVER'; 
    var $Username   = 'SMTP_LOGIN'; 
    var $Password   = 'SMTP_PASSWORD'; 
    var $SMTPAuth   = SMTP_AUTH; 
    var $From       = 'MAIL_FROM'; 
    var $FromName   = 'MAIL_NAME'; 
    var $CharSet    = 'MAIL_CHARSET'; 
    var $ReplyTo    = array('MAIL_REPLY'); 
    
    function phpx_mail() {
        $this->SetLanguage('zh'); 
    }
}

?>