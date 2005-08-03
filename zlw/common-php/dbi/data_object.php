<?
class data_object {
    var $_db; 
    
    function data_object($dbi) {
        $this->_db = $dbi; 
    }
    
    function import($assoc, $ignores = NULL) {
        if ($assoc) {
            foreach ($assoc as $member=>$value) {
                if (is_null($value)) continue; 
                if (substr($member, 0, 1) == '_') continue; 
                if ($ignores)
                    if ($ignores[$member]) continue; 
                $this->$member = $value; 
            }
        }
    }
    
    function export(&$target, $ignores = NULL) {
        foreach ($this as $member=>$value) {
            if (is_null($value)) continue; 
            if (substr($member, 0, 1) == '_') continue; 
            if ($ignores)
                if ($ignores[$member]) continue; 
            $target->$member = $value; 
        }
    }
    
}
?>