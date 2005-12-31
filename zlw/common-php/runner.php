<?php
    require_once dirname(__FILE__) . '/http.php'; 
    
    $view = $_REQUEST['view']; 
    if ($view == 'output') {
        $src = $_REQUEST['src']; 
        $src = phpx_noslashes($src); 
        $tmpphp = tempnam('/tmp', 'PHP'); 
        $fh = fopen($tmpphp, 'w'); 
        fwrite($fh, $src); 
        fclose($fh); 
        
        # include will get warnings, rather that require which will die. 
        include $tmpphp; 
        
        foreach (glob(dirname($tmpphp) . '/PHP*') as $tmp) {
            unlink($tmp); 
        }
    } else {
?>
<html id="html" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>PHP Runner</title>
    <style type="text/css">
    	.autosize { width: 100%; height: 100%; overflow: visible }
    	.error { color: red }
    	.duration { color: green }
    	.address { font-size: medium; color: green }
    </style>
<!-- Event Impl. -->
    <script language="javascript">
        function show_output() {
            // Not implemented. 
        }
    </script>
</head>
<body>
<?php
        $name = $_SERVER['SERVER_NAME']; 
        $addr = $_SERVER['SERVER_ADDR']; 
        $port = $_SERVER['SERVER_PORT']; 
        $server = "$name/$addr:$port"; 
        $remote = $_SERVER['REMOTE_ADDR']; 
?>
<h1 title="Your address: <?php echo $remote?>">PHP Runner <?php echo phpversion(); ?> <span class="address">(<?php echo $server; ?>)</span></h1>
<form id="frmScript" method="post" action="?" target="result">
<table border="0" width="100%" height="90%">
<tr><td width="50%">
      <input type="hidden" name="view" value="output" />
      <textarea name="src" cols="40" rows="10" class="autosize">&lt;pre&gt;&lt;?php



?&gt;&lt;/pre&gt;</textarea>
</td>
<td width="50%">
<iframe name="result" class="autosize">
</iframe>
</td></tr>
<tr>
<td colspan="2" align="center">
    <input type="reset" value="Reset" />&nbsp;
    <input type="button" value="(O)utput" accesskey="O" onClick="show_output()" />&nbsp;
    <input type="submit" value="(R)un" accesskey="R"/>
</td>
</tr>
</table>
</form>
</body>
</html>
<?php
    }
?>