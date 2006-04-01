<?php

function &phpx_isa_load($table, $dbi = NULL) {
    if (is_resource($table))
        $result = $table; 
    else {
        if (is_null($dbi))
            $dbi = phpx_connect_fast(); 
        $result = $dbi->_query("select * from `$table`"); 
    }
    while ($row = $dbi->_fetch_row($result)) {
        $sub_id = $row[0]; 
        $sup_id = $row[1]; 
        $sub = &$all[$sub_id]; 
        $sup = &$all[$sup_id]; 
        foreach (array_keys($sup) as $ancient) {
            unset($sub[$ancient]);      # hidden transferences
        }
        $sub[$sup_id] = true; 
    }
    return $all; 
}

# Check for Single-Root Inheritance
function isa_check_sri(&$all) {
    foreach (array_keys($all) as $id) {
        $n_sups = sizeof($all[$id]); 
        if ($n_sups > 1)
            $errs[] = $id; 
    }
    return $errs; 
}

function isa_build_branches($all) {
    foreach ($all as $id=>$sups) {
        $item_g = &$all_g[$id]; 
        foreach (array_keys($sups) as $sup_id) {
            $item_g['sup'][$sup_id] = &$all_g[$sup_id]; 
            $all_g[$sup_id]['sub'][$id] = &$all_g[$id]; 
        }
        $n_sups = sizeof($sups); 
        if ($n_sups == 0) {
            # root of a disconnected branch
            $branches['sub'][$id] = &$item_g; 
        }
    }
    return $branches; 
}

function isa_dump($start, $indent = '', $names = NULL) {
    foreach ($start['sub'] as $id => $sub) {
        $name = is_null($names) ? $id : $names[$id]; 
        echo $indent . $name . "\n"; 
        isa_dump($sub, $indent . "  ", $names); 
    }
}

function isa_save_db($table, $subs, $sup_id = NULL, $sub_f = 'sub', $sup_f = 'sup', $dbi = NULL) {
    if (is_null($dbi))
        $dbi = phpx_connect_fast(); 
    foreach ($subs['sub'] as $id => $sub) {
        if (! is_null($sup_id)) {
            $sql = "insert into `$table`(`$sub_f`, `$sup_f`) values($id, $sup_id)"; 
            $dbi->_query($sql); 
        }
        isa_save_db($table, $sub, $sup_id, $sub_f, $sup_f, $dbi); 
        isa_save_db($table, $sub['sub'], $id, $sub_f, $sup_f, $dbi); 
    }
}

function isa_update_closure($branches) {
    
}

?>