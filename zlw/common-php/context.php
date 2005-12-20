<?php
    require 'phpfixes.php'; 
    _RequireOnce('http.php'); 
    
    @session_start(); 
    
    function dump_section($name, $map, $sep = '') {
        echo "<h2>$name" . "</h2>\n"; 
        sort($keys = array_keys($map)); 
        echo "<table border='0'>\n"; 
        foreach ($keys as $k) {
            $v = $map[$k]; 
            $type = gettype($v); 
            echo "<tr><td colspan='2'><strong>$type</strong> <u>$k</u></td></tr>\n"; 
            echo "<tr><td class='indent'><td>"; 
            switch (gettype($v)) {
            case 'boolean': 
            case 'integer': 
            case 'double': 
            case 'string': 
                echo "<pre>" . htmlspecialchars($v) . "</pre>\n"; 
                break; 
            case 'array': 
                echo "<ol>\n"; 
                foreach ($v as $vi) {
                    echo "<li><pre>" . htmlspecialchars($v1) . "</pre></li>\n"; 
                }
                echo "</ol>\n"; 
                break; 
            case 'object': 
                echo "<table border='0'>\n"; 
                foreach ($v as $k1=>$v1) {
                    echo "<tr><td align='right'><u>" . htmlspecialchars($k1) . "</u></td>"; 
                    echo "<td><pre>" . htmlspecialchars($v1) . "</pre></td></tr>"; 
                }
                echo "</table>\n"; 
                break; 
            case 'resource': 
                echo htmlspecialchars($v); 
                break; 
            case 'NULL': 
                echo "<i>NULL</i>"; 
                break; 
            default: 
                echo "<i>" . htmlspecialchars($v) . "</i>"; 
                break; 
            }
		}
		echo "</table>\n"; 
		echo "<hr/>\n"; 
	}
?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html id="html">
  <head>
    <title>Common-PHP: Context</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
  </head>

 <body>
    
    <?php
        dump_section('$_GET', $_GET); 
        dump_section('$_POST', $_POST); 
        dump_section('$_REQUEST', $_REQUEST); 
        dump_section('$_REQUEST (noslashes)', phpx_noslashes($_REQUEST)); 
        dump_section('$_SESSION', $_SESSION); 
        dump_section('$_SERVER', $_SERVER);
        dump_section('$_ENV', $_ENV);
        dump_section('$_COOKIE', $_COOKIE);
    ?>
	
	<script language="vbscript">
		function Q(): Q = """": end function
		function QQ(s): QQ = """" & s & """": end function
	</script>
		<!--
		<tr><td>
			<option name=L01 value=1>Input-Box</option>
			<option name=L01 value=1>Check-Box</option>
			<option name=L01 value=1>Submit</option>
			<option name=L01 value=1>Reset</option>
			<option name=L01 value=1>TextArea</option>
		</td><td>
		</tr>
		-->
	
	<form name=f_control method="get">
		<table border="0">
			<thead><th>type</th><th>name</th><th>control</th><th>cmd</th></thead>
			<tr><td>TEXT</td>		<td>N01</td>	<td><input name=N01 type=text></td>				<td></td></tr>
			<tr><td>CHECK</td>		<td>N02</td>	<td><input name=N02 type=checkbox>CHECK</td>	<td></td></tr>
			<tr><td>SUBMIT</td>		<td>N03</td>	<td><input name=N03 type=submit></td>			<td></td></tr>
			<tr><td>RESET</td>		<td>N04</td>	<td><input name=N04 type=reset></td>			<td></td></tr>
			<tr><td>TEXTAREA</td>	<td>N05</td>	<td><textarea name=N05></textarea></td>			<td></td></tr>
		</table>
	</form>
	<hr>
	
	<form name="f_target" method="get" language="vbscript">
		<script language="vbscript">
			function SetNum(str, num)
				setnum=replace(str, "01", right("000" & num, 2))
			end function
		</script>
		
		<table id="ft_table" name="ft_table" border="0">
			<thead><th>type</th><th>name</th><th>control</th><th>cmd</th></thead>
			<tr><td>SUBMIT</td>
				<td>MS1</td>
				<td><input name=MS1 type=submit></td>
				<td></td></tr>
			<tr><td>RESET</td>
				<td>MS2</td>
				<td><input name=MS2 type=reset> 
					<input type=button value="Test submit" 
						onclick="vbscript:f_target_onsubmit:f_script.TAScript.value=f_target.outerHTML"> </td>
				<td></td></tr>
			<tr><td>TEXT</td>
				<td colspan=2>Action: 
					<input type=text name="MS3" value="context.jsp" size=40></td>
				<td></td></tr>
			<tr><td>TEXT</td>
				<td><input type=text name="M01N" value="Name"></td>
				<td><input type=text name="M01" value="Value"></td>
				<td><input type=button name="A01" value="Add" 
					onclick='vbscript: set r=ft_table.insertrow: base=4: num=ft_table.rows.length-base: r.insertcell.innerhtml=setnum(ft_table.rows(base).cells(0).innerhtml, num): r.insertcell.innerhtml=setnum(ft_table.rows(base).cells(1).innerhtml, num): r.insertcell.innerhtml=setnum(ft_table.rows(base).cells(2).innerhtml, num): r.insertcell.innerhtml=setnum(ft_table.rows(base).cells(3).innerhtml, num)'
				</td></tr>
		</table>
	</form>
	<script language="vbscript">
		function f_target_onsubmit()
			if ft_table.rows(3).cells(1).innerHTML = "Removed" then exit function
			
			f_target.action = f_target.MS3.value
			ft_table.rows(3).cells(1).innerHTML = "Removed"
			for i=4 to ft_table.rows.length-1
				num=right("000" & (i-3), 2)
				thename=f_target("M" & num & "N").value
				merged="<input type=text name=" & QQ(thename) & " value=" & QQ(f_target("M" & num).value) & ">"
				ft_table.rows(i).cells(2).innerHTML = merged
				ft_table.rows(i).cells(1).innerHTML = "Removed"
				ft_table.rows(i).cells(3).innerHTML = "Removed"
			next
		end function
	</script>
	<hr>
	
	<form name=f_script>
		<table border=0>
			<tr><td>
				<textarea name="TAScript" cols=80 rows=5></textarea>
			</td><td>
				<input type=button value="Load HTML" onclick="vbscript: TAScript.value=html.outerHTML"><br>
				<input type=button value="Execute" name="btnExecute">
				<script language="vbscript">
					sub btnExecute_onclick
						set tr = document.selection.createrange
			   			if len(tr.text)>1 then
			   				execute tr.text
			   			else
							execute f_script.TAScript.value
			   			end if
					end sub
				</script>
			</td></tr>
		</table>
	</form>
	
	<?php
	    phpinfo(); 
	?>
	
  </body>
</html>