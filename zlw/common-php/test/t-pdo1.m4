m4_include(`pdo-member.m4')
<?
/*
 * M4X___ORIGINAL_FILE__
 */

require '_Phpfixes.php'; 
_RequireOnce('../pdo.php'); 

class person extends phpx_data_object {
    pdo_member(`name', NOTNULL, STRING, GET); 
    pdo_member(`age', NUMBER, VERIFY); 
    
    function get_name() {
        return 'I am always TOMMY!'; 
    }
    
    function vrf_age() {
        $age = $this->get('age'); 
        if ($age > 100)
            return "Age could not be greater than 1000!!"; 
        return NULL; 
    }
    
    function dump() {
        return 'Person(name='.$this->get('name').', age='.$this->get('age').')'; 
    }
}

var $p = new person; 

?>