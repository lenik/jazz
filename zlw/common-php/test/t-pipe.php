Pipe Comm
-------------------
<?php
	$h = popen('php t-server.php', 'r'); 
    while (! feof($h)) {
        $line = fgets($h); 
        echo ".. $line"; 
    }
    pclose($h); 
?>
-------------------
Pipe End.