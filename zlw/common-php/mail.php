<?php
/*
 * warning:  this file is automatically generated by m4 scripts.  please do not edit this file, you may lose all your changes in this file after the original file is re-built.  
 */

/* Common-PHP
 *
 * SMTP Class
 */
require '_Phpfixes.php'; 
_RequireOnce("ext/phpmailer.php"); 

class phpx_mail extends PHPMailer {
    var $Mailer     = 'smtp'; 
    var $Host       = 'localhost'; 
    var $Username   = 'root'; 
    var $Password   = ''; 
    var $SMTPAuth   = true; 
    var $From       = 'root@localhost'; 
    var $FromName   = 'Root'; 
    var $CharSet    = 'UTF-8'; 
    var $ReplyTo    = array('root@localhost'); 
    
    function phpx_mail() {
        $this->SetLanguage('zh'); 
    }
}

?>