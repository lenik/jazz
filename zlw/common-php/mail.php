<?php

/* Common-PHP
 *
 * SMTP Class
 */
 
require_once dirname(__FILE__) . '/ext/phpmailer.php'; 

if (! defined('SMTP_SERVER'))
    define('SMTP_SERVER', 'localhost'); 
if (! defined('SMTP_LOGIN'))
    define('SMTP_LOGIN', 'root'); 
if (! defined('SMTP_PASSWORD'))
    define('SMTP_PASSWORD', ''); 
if (! defined('SMTP_AUTH'))
    define('SMTP_AUTH', 'true'); 
if (! defined('MAIL_FROM'))
    define('MAIL_FROM', 'root@localhost'); 
if (! defined('MAIL_NAME'))
    define('MAIL_NAME', 'Root'); 
if (! defined('MAIL_CHARSET'))
    define('MAIL_CHARSET', 'UTF-8'); 
if (! defined('MAIL_REPLY'))
    define('MAIL_REPLY', 'root@localhost'); 

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