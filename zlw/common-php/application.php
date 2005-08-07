<?

/* $_APPLICATION support in PHP
 *
 * session.save_path is used to save serialized application data
 *
 * unlike $_SESSION, $_APP should be imported by global ($_APP) in procedures. 
 */

class phpx_cached_object {
    var $name; 
    var $save_path; 
    
    function phpx_cached_object($name) {
        $this->name = $name; 
        $this->save_path = session_save_path() . "/$name.cache"; 
    }
    
    function import($assoc) {
        foreach($assoc as $member=>$value) {
            $this->$member = $value; 
        }
    }
    
    function export(&$assoc) {
        foreach($this as $member=>$value) {
            $assoc[$member] = $value; 
        }
    }
    
    function is_cached() {
        return file_exists($this->save_path); 
    }
    
    function load() {
        if ($this->is_cached()) {
            $file = fopen($this->save_path, "r"); 
            $data = fread($file, filesize($this->save_path)); 
            $object = unserialize($data); 
            $this->import($object); 
        }
    }
    
    function save() {
        $file = fopen($this->save_path, "w"); 
        fwrite($file, serialize($this)); 
        fclose($file); 
    }
    
    function remove() {
        if ($this->is_cached)
            unlink($this->save_path); 
    }
}

function phpx_cached_get($name) {
    $cached = new phpx_cached_object($name); 
    $cached->load(); 
    $assoc = array(); 
    $cached->export($assoc); 
    return $assoc; 
}

function phpx_cached_set($name, $assoc) {
    $cached = new phpx_cached_object($name); 
    $cached->import($assoc); 
    $cached->save(); 
}

global $PHPX_APP; 
global $PHPX_APPNAME; 

function phpx_application_start($name = 'default') {
    global $PHPX_APP; 
    global $PHPX_APPNAME; 
    $PHPX_APP = phpx_cached_get($name); 
    $PHPX_APPNAME = $name; 
}

function phpx_application_end() {
    global $PHPX_APP; 
    global $PHPX_APPNAME; 
    phpx_cached_set($PHPX_APPNAME, $PHPX_APP); 
    $PHPX_APP = NULL; 
    $PHPX_APPNAME = NULL; 
}

function phpx_application_destroy() {
    global $PHPX_APP; 
    global $PHPX_APPNAME; 
    if ($PHPX_APP) {
        $PHPX_APP->remove(); 
        unset($PHPX_APP); 
    }
}

?>