<?
require '_Phpfixes.php'; 
_RequireOnce('dbi.php'); 

define("PDT_RAW",       1); 
define("PDT_CDATA",     2); 
define("PDT_PCDATA",    4); 
define("PDT_GET",       256); 
define("PDT_PUT",       512); 
define("PDT_NOTNULL",   8192); 
define("PDT_VERIFY",    16384); 
define("PDT_PRIMARY",   32768); 

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
abstract class phpx_data_object extends phpx_dbi {
    
    function phpx_data_object($dbi = NULL) {
        if (! is_null($dbi))
            $this->_Reuse($dbi); 
    }
    
    function getvt($name) {
        $vt = $this->{"pdt_$name"}; 
        assert(! is_null($vt)); 
        return $vt; 
    }
    
    function get($name, $def = NULL) {
        $vt = $this->getvt($name); 
        if ($vt & PDT_GET)
            $val = $this->{"get_$name"}(); 
        else
            $val = $this->{"pdv_$name"}; 
        if (is_null($val) && ! is_null($def))
            return $def; 
        return $val; 
    }
    
    # get sql-escaped value
    function getx($name, $def = NULL) {
        $val = $this->get($name, $def); 
        if (is_null($val))
            return 'null'; 
        $vt = $this->getvt($name); 
        if ($vt & PDT_RAW)
            return strval($val); 
        if ($vt & PDT_CDATA)
            return Q($this->_escape_string($val)); 
        return Q($val); 
    }
    
    function put($name, $value, $force = false) {
        $vt = $this->getvt($name); 
        if (($vt & PDT_NOTNULL) && is_null($value) && !$force) {
            error_log("NOT-NULL: $name"); 
        }
        if (($vt & PDT_VERIFY) && !$force) {
            $err = $this->{"vrf_$name"}($value); 
            if (! is_null($err)) {
                error_log($err); 
                return; 
            }
        }
        if ($vt & PDT_PUT)
            $this->{"put_$name"}($value); 
        else
            $this->{"pdv_$name"} = $value; 
    }
    
    function _verify($ignores = NULL) {
        foreach ($this as $memberf=>$value) {
            if (is_null($value)) continue; 
            if (substr($memberf, 0, 4) != 'pdt_') continue; 
            $member = substr($member, 4); 
            if ($ignores)
                if ($ignores[$member]) continue; 
            $vt = $this->$memberf; 
            if ($vt & PDT_NOTNULL) {
                if (is_null($this->get($member)))
                    return "NOT-NULL: $member"; 
            }
            if ($vt & PDT_VERIFY) {
                if (! $this->{"vrf_$member"}($this->get($member)))
                    return "NOT-VERIFY: $member"; 
            }
        }
        return NULL; 
    }
    
    function _import($assoc, $nulls = false) {
        if ($assoc) {
            foreach ($assoc as $member=>$value) {
                if (is_null($value) && ! $nulls) continue; 
                $this->put($member, $value); 
            }
        }
    }
    
    function _export(&$ret_assoc, $nulls = false) {
        foreach ($this as $memberf=>$value) {
            if (substr($memberf, 0, 4) != 'pdt_') continue; 
            $member = substr($memberf, 4); 
            $value = $this->get($member); 
            if (is_null($value) && ! $nulls) continue; 
            $ret_assoc->$member = $value; 
        }
    }
    
    /* key=value, key=value, ... */
    function _format($eq = '=', $sep = ', ', $nulls = false) {
        $buffer = ''; 
        foreach ($this as $memberf=>$value) {
            if (is_null($value) && ! $nulls) continue; 
            if (substr($memberf, 0, 4) != 'pdt_') continue; 
            $member = substr($memberf, 4); 
            if ($buffer)
                $buffer .= $sep; 
            $buffer .= "$member$eq".$this->getx($member); 
        }
        return $buffer; 
    }
    
    function _format_debug() {
        return $this->_format(" = ", "\n", true); 
    }
    
    /* (key, key, ...) values (value, value, ...) */
    function _format_values() {
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
            $values .= $this->getx($v); 
        }
        return "($keys) values($values)"; 
    }
    
    function _format_updates() {
        return _format('=', ',', true); 
    }
    
}
?>