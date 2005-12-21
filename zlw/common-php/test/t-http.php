<?php

require_once dirname(__FILE__) . '/../http.php';

if ($_GET['abc'] == '1') {
    ?>
    <html><body>hello! Successful redirected!</body></html>
    <?php
} else {
    phpx_redirect_relative('t-http.php?abc=1', array('def'=>2) ); 
}
?>