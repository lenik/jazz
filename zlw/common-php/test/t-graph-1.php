<?php
    include '../graph.php'; 
    
    function testref(&$n1, &$n2) {
    	$n1->next = 'Next 1'; 
    	$n2->next = 'Next 2'; 
    	if ($n1->next != $n2->next)
    		echo "::Next Break\n"; 
    }
    
    $g = new phpx_graph(); 
    
    $a = new phpx_node('Node A'); 
    $b = new phpx_node('Node B'); 
    
    # --------------
    
    $g->add($a); 
    $g->connect($a, $b); 
    
    echo "Test A\n"; 
    testref($a, $a); 
    
    echo "Test B\n"; 
    testref($b, $b); 
?>