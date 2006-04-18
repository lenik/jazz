m4_include(`pdo-member.m4')
<?
/*
 * M4X___ORIGINAL_FILE__
 */

require '_Phpfixes.php'; 
_RequireOnce('t-config.php'); 
_RequireOnce('../pdo.php'); 

class person extends phpx_data_object {
    pdo_member(`name', NOTNULL, CDATA, GET, PRIMARY); 
    # pdo_primary_key(`name'); 
    
    pdo_member(`age', RAW, VERIFY); 
    pdo_default(`age', 0); 
    
    pdo_member(`address', CDATA); 
    
    function get_name() {
        return 'I am always Jim\'TOMMY!'; 
    }
    
    function vrf_age() {
        $age = $this->get('age'); 
        if ($age > 100)
            return "Age could not be greater than 1000!!"; 
        return null; 
    }
    
    function dump() {
        return 'Person(name='.$this->get('name').', age='.$this->get('age').')'; 
    }
    
}

$p = new person; 
echo 'name: ', $p->get('name'), "\n"; 
$p->put('name', null);

echo "--------------------- format: \n";
echo $p->_format_debug(); 

?>