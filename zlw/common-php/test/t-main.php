<?
require_once '_Phpfixes.php'; 
echo "<PRE>";
echo "Dirname: ", dirname('dir/inc.php'), "\n"; 
echo "Basename: ", basename('dir/inc.php'), "\n"; 
_Require('dir/inc_in_dir.php'); 
echo hello(); 
?>
