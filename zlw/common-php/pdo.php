<?
require '_Phpfixes.php'; 
_RequireOnce('dbi.php'); 

define("PDT_RAW", 1); 
define("PDT_CDATA", 2); 
define("PDT_PCDATA", 4); 
define("PDT_GET", 0x100); 
define("PDT_PUT", 0x200); 
define("PDT_NOTNULL", 0x2000); 
define("PDT_VERIFY", 0x4000); 
/*
 * Members::
 *  pdt_<name>          REQUIRED  declare field options
 *  pdv_<field-name>    (AUTO)    define field value, may be declared with default value
 *  get_<name>()        OPTIONAL  property get method, if pdt_ & PDT_GET
 *  put_<name>($val)    OPTIONAL  property put method, if pdt_ & PDT_PUT
 *  vrf_<name>          OPTIONAL  check function for this field, if pdt_ & PDT_VERIFY
 *                                check evaluate: $this->vrf_name($this->pdv_name)
 *
 * Wrappers::
 *  get($name)
 *  put($name, $val)
 */
class PhpDataObject extends DBI {
    
    function PhpDataObject($dbi = NULL) {
        if (! is_null($dbi))
            $this->_Reuse($dbi); 
    }
    
    function get_type($name) {
        $pdt = $this->{"pdt_$name"}; 
        assert(! is_null($pdt)); 
        return $pdt; 
    }
    
    function get($name, $def = NULL) {
        $pdt = $this->get_type($name); 
        if ($pdt & PDT_GET)
            $val = $this->{"get_$name"}(); 
        else
            $val = $this->{"pdv_$name"}; 
        if (is_null($val) && ! is_null($def))
            return $def; 
        return $def; 
    }
    
    function getx($name, $def = NULL) {
        $pdt = $this->get_type($name); 
        $val = $this->get($name, $def); 
        if ($pdt & PDT_RAW)
            return strval($val); 
        if ($pdt & PDT_CDATA)
            return Q($this->_EscapeString($val)); 
        return Q($val); 
    }
    
    function put($name, $value, $force = false) {
        $pdt = $this->get_type($name); 
        $vrf = ($pdt & PDT_VERIFY) && !$force; 
        if ($vrf)
            $old_val = $this->get($name); 
        if ($pdt & PDT_PUT)
            $this->{"put_$name"}($value); 
        else
            $this->{"pdv_$name"} = $value; 
    }
    
    function _Verify($ignores = NULL) {
        foreach ($this as $memberf=>$value) {
            if (is_null($value)) continue; 
            if (substr($memberf, 0, 4) != 'pdt_') continue; 
            $member = substr($member, 4); 
            if ($ignores)
                if ($ignores[$member]) continue; 
            $pdt = $this->$memberf; 
            if ($pdt & PDT_NOTNULL) {
                if (is_null($this->get($member)))
                    return "NOT-NULL: $member"; 
            }
            if ($pdt & PDT_VERIFY) {
                if (! $this->{"vrf_$member"}($this->get($member)))
                    return "NOT-VERIFY: $member"; 
            }
        }
        return NULL; 
    }
    
    function _Import($assoc, $nulls = false) {
        if ($assoc) {
            foreach ($assoc as $member=>$value) {
                if (is_null($value) && ! $nulls) continue; 
                $this->put($member, $value); 
            }
        }
    }
    
    function _Export(&$ret_assoc, $nulls = false) {
        foreach ($this as $memberf=>$value) {
            if (substr($memberf, 0, 4) != 'pdt_') continue; 
            $member = substr($memberf, 4); 
            $value = $this->get($member); 
            if (is_null($value) && ! $nulls) continue; 
            $ret_assoc->$member = $value; 
        }
    }
    
    /* key=value, key=value, ... */
    function _Format($eq = '=', $sep = ', ', $nulls = false) {
        $buffer = ''; 
        foreach ($this as $memberf=>$value) {
            if (is_null($value) && ! $nulls) continue; 
            if (substr($memberf, 0, 4) != 'pdt_') continue; 
            $member = substr($memberf, 4); 
            if ($buffer)
                $buffer .= $sep; 
            $buffer .= "$member$eq".$this->get_escaped($member); 
        }
        return $buffer; 
    }
    
    function _FormatDebug() {
        return $this->_Format(" = ", "\n", true); 
    }
    
    /* (key, key, ...) values (value, value, ...) */
    function _FormatValues() {
        $keys = ''; 
        $values = ''; 
        foreach ($assoc as $k=>$v) {
            if (substr($k, 0, 1) == '_') continue; 
            if (is_null($v)) continue; 
            if ($ignores)
                if ($ignores[$k]) continue; 
            if ($keys) {
                $keys .= ', '; 
                $values .= ', '; 
            }
            $keys .= $k; 
            $values .= $this->_BuildSqlValue($v); 
        }
        return "($keys) values($values)"; 
    }
}
?>