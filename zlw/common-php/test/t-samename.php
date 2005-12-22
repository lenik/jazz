<?php

class Name {
	var $Name = 'hello'; 
	function Name($Name) {
		echo "Name::Name member function, member Name = $this->Name, argument Name: $Name \n"; 
	}
}

function Name($Name) {
	echo "Global::Name function, argument Name: $Name \n"; 
}

$Name = new Name('cstr'); 
$Name->Name('invoke'); 

Name($Name); 
?>