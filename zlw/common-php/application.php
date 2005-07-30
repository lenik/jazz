<?

/* $_APPLICATION support in PHP
 *
 * session.save_path is used to save serialized application data
 *
 * unlike $_SESSION, $_APP should be imported by global ($_APP) in procedures. 
 */

function application_start($name = "default") {
    global $save_path;
    global $_APP;
    
    $save_path = session_save_path() . "/$name.app";
    
    if (file_exists($save_path)) {
        $file = fopen($save_path, "r");
        assert($file); 
        $data = fread($file, filesize($save_path));
        fclose($file);
        $_APP = unserialize($data);
        return TRUE; 
    }
    
    return FALSE; 
}

function application_end() {
    global $save_path;
    global $_APP; 

    if (! $save_path) {
        error_log("application hasn't been started");
        return FALSE; 
    }
    
    $file = fopen($save_path, "w");
    assert($file);
    fwrite($file, serialize($_APP));
    fclose($file);
    
    return TRUE; 
}

function application_destroy() {
    global $save_path;
    global $_APP; 

    if (! $save_path) {
        error_log("application hasn't been started");
        return FALSE;
    }
    
    if (file_exists($save_path)) {
        unlink($save_path);
    }

    unset($_APP);
    return TRUE; 
}

?>
