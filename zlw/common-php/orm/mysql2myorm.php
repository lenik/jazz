<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
</head>

<body>
<h1>Convert Schema In MySQL To MyORM-XML</h1>
<table border="0">
    <tr>
        <td><div align="right">Host</div></td>
        <td>&nbsp;</td>
        <td><label>
            <input name="host" type="text" id="host" value="<?php echo $host; ?>" maxlength="20" />
        </label>        </td>
        <td></td>
    </tr>
    <tr>
        <td><div align="right">Database</div></td>
        <td>&nbsp;</td>
        <td><input name="database" type="text" id="database" value="<?php echo $database; ?>" maxlength="20" /></td>
        <td>&nbsp;</td>
    </tr>
    <tr>
        <td><div align="right">User Name </div></td>
        <td>&nbsp;</td>
        <td><input name="username" type="text" id="username" value="<?php echo $username; ?>" maxlength="20" /></td>
        <td><input type="reset" value="Reset" /></td>
    </tr>
    <tr>
        <td><div align="right">Password</div></td>
        <td>&nbsp;</td>
        <td><input name="password" type="text" id="password" value="<?php echo $password; ?>" maxlength="40" /></td>
        <td><input type="submit" name="Submit" value="Change" /></td>
    </tr>
</table>
<hr />
<pre><?php
	$dbi = phpx_fast_connect(); 
	$tables = $dbi->_list_tables($opt_database); 
	
	while ($rowt = $dbi->_fetch_row($tables)) {
		$tablename = $rowt[0]; 
		$dummy = $dbi->_query("select * from `$tablename` where 1=2"); 
		$nfields = $dbi->_num_fields($dummy); 
		for ($i = 0; $i < $nfields; $i++) {
			
?></pre>
</body>
</html>
