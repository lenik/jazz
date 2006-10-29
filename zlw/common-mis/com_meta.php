<?php

# page info

function count_records($db, $tables = null) {
    if (is_null($tables)) {
        # list all tables
    } elseif (is_string($tables)) {
        $tables = phpx_list_parse($tables);
    }
    $touches = 0; 
    foreach ($tables as $table) {
        $count = $db->_eval("select count(*) from $table");
        # write count to csm::records
        $db->_update_table('com_records',
                           $count);
        $touches++; 
    }
    return $touches; 
}

function get_table_size($db, $table) {
    $size = $db->_eval("select size from com_table_size where tbl='$table'");
    return $size;
}
?>