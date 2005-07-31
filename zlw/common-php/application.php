<?

/* $_APPLICATION support in PHP
 *
 * session.save_path is used to save serialized application data
 *
 * unlike $_SESSION, $_APP should be imported by global ($_APP) in procedures. 
 */

class cached_object {
    var $name; 
    var $save_path; 
    
    function cached_object($name) {
        $this->name = $name; 
        $this->save_path = session_save_path() . "/$name.cache"; 
    }
    
    function import($object) {
        foreach($object as $member=>$value) {
            $this->$member = $value; 
        }
    }
    
    function export(&$object) {
        foreach($this as $member=>$value) {
            $object->$member = $value; 
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

$_APP = NULL; 

function application_start() {
    global $_APP; 
    $_APP = new cached_object("default"); 
    $_APP->load(); 
}

function application_end() {
    $_APP->save(); 
    $_APP = NULL; 
}

function application_destroy() {
    if ($_APP) {
        $_APP->remove(); 
        unset($_APP); 
    }
}

function cached_get($name, $object) {
    $cached = new cached_object($name); 
    $cached->load(); 
    return $cached; 
}

function cached_set($name, $object) {
    $cached = new cached_object($name); 
    $cached->import($object); 
    $cached->save(); 
}

?>
