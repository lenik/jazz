<?php
    require_once dirname(__FILE__) . '/phpfixes.php'; 
    require_once 'zlw/common-php/lang.php'; 
    require_once 'zlw/common-php/http.php'; 
    
    $pr_style = $_REQUEST['_pr_style']; 
    $pr_src = $_REQUEST['_pr_src']; 
    $pr_src = phpx_noslashes($pr_src); 
    $pr_params = $_REQUEST['_pr_params']; 
    if (isset($pr_params)) {
        $pr_params = phpx_map_parse($pr_params); 
        foreach ($pr_params as $k=>$v) {
            $_REQUEST[$k] = $v; 
        }
    }
    if ($pr_style == 'html' || $pr_style == 'text') {
        $pr_tmpphp = tempnam('/tmp', 'PHP'); 
        $pr_fh = fopen($pr_tmpphp, 'w'); 
        fwrite($pr_fh, $pr_src); 
        fclose($pr_fh); 
        
        if ($pr_style == 'text')
            header('Content-Type: text/plain'); 
        elseif ($pr_style == 'html')
            header('Content-Type: text/html');
        
        # include will get warnings, rather that require which will die. 
        include $pr_tmpphp; 
        
        foreach (glob(dirname($pr_tmpphp) . '/PHP*') as $tmp) {
            unlink($tmp); 
        }
    } elseif ($pr_style == 'php') {
        # php_parse_and_colorize()...?
        header('Content-Type: text/html'); 
        $pr_src = htmlspecialchars($pr_src); 
        $pr_src = str_replace("\n", "<br>", $pr_src); 
        echo $pr_src; 
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
        function run() {
            var form = document.getElementById("frmScript"); 
            var src = form["_pr_src"]; 
            // ...?
        }
        
        function done() {
            var form = document.getElementById("frmScript"); 
            var src = form["_pr_src"]; 
            src.focus(); 
        }
        
        function set_style() {
            var form = document.getElementById("frmScript"); 
            var src = form["_pr_src"]; 
            var style = form["_pr_style"]; 
            src.focus(); 
        }
        
        function set_sample() {
            var form = document.getElementById("frmScript"); 
            var src = form["_pr_src"]; 
            var tmpl = form["_pr_sample"]; 
            switch (tmpl.value) {
                case "empty": 
                    src.value = "<pre><" + "?php\n\n\n\n?></pre>"; 
                    break; 
                case "dbi": 
                    src.value = "<pre><" + "?php\n\
include_once 'lang.php';\n\
include_once 'test/t-config.php'; \n\
include_once 'dbi.php'; \n\
\n\
$db = phpx_connect_fast(); \n\
\n\
echo $db->_evaluate(\"select '$_REQUEST[test]'\"); \n\
\n\
$db->_close(); \n\
?></pre>"; 
                    break; 
            }
            src.focus(); 
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
<form id="frmScript" method="post" action="?" target="result" onsubmit="javascript: run(); ">
<table border="0" width="100%" height="100%">
    <tr><td colspan="2">
        <h1 title="Your address: <?php echo $remote?>" style="line-height: 10px; margin: 5px;">
            PHP Runner <?php echo phpversion(); ?>
            <span class="address">(<?php echo $server; ?>)</span>
        </h1>
        </td></tr>
    <tr><td width="50%">
        <input type="hidden" name="_pr_mtd" value="text" />
        <textarea name="_pr_src" cols="40" rows="10" class="autosize">&lt;pre&gt;&lt;?php



?&gt;&lt;/pre&gt;</textarea>
        </td><td width="50%">
            <iframe name="result" class="autosize" onload="javascript: done();">
            </iframe>
        </td></tr>
    <tr><td colspan="2" align="center">
        <table width="100%" border="0"><tr>
            <td width="300px">
                <select name="_pr_sample" title="Sample Codes" onchange="javascript: set_sample(); ">
                    <option value="empty">Empty
                    <option value="dbi">Sample DBI
                </select>
                <select name="_pr_style" title="Output Method" onchange="javascript: set_style(); ">
                    <option value="html">As HTML
                    <option value="text">As Plain-Text
                    <option value="php">Code Format
                </select>
                <input type="submit" value="(R)un" accesskey="R"/>
            </td><td width="*">
                <input name="_pr_params" type="text" 
                    value="foo=bar:test=Hello, World!"
                    title="URL Parameters, will be parsed by phpx_map_parse" style="width: 100%">
            </td></tr></table>
        </td></tr>
</table>
</form>
</body>
</html>
<?php
    }
?>