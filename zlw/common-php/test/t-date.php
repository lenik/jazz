<?

    function dtc_time($time)        { return date("Y-m-d H:i:s", $time); }
    function dtc_gmtime($time)      { return gmdate("Y-m-d H:i:s", $time); }
    function dtc_gmstrtotime($str)  {
        $timeofday = gettimeofday(); 
        $gmtadj = -60 * $timeofday['minuteswest']; 
        return strtotime($str) + $gmtadj; 
    }
    
    $now = time(); 
    
    $local1 = dtc_time($now); 
    $gm1 = dtc_gmtime($now); 
    echo "local: $local1\n"; 
    echo "gm:    $gm1\n"; 
    
    $t = strtotime($local1); 
    $local = dtc_time($t); 
    $gm = dtc_gmtime($t); 
    echo "local(local):  $local\n"; 
    echo "gm(local):     $gm\n"; 
    
    $t = dtc_gmstrtotime($gm1); 
    $local = dtc_time($t); 
    $gm = dtc_gmtime($t); 
    echo "local(gm):     $local\n"; 
    echo "gm(gm):        $gm\n"; 
?>