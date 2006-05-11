<?php

# complex object ref

class input {
    public $name; 
    public $value; 
    function input($name, $value) {
        $this->name = $name; 
        $this->value = $value; 
    }
}

class form {
    public $inputs; 
    function add_input(&$input) {
        $name = $input->name;
        $old = &$this->inputs[$name];
        if (is_null($old)) {
            $this->inputs[$name] = &$input;
            return 1; 
        }
        if (! is_array($old)) {
            $new = array(&$old); 
            $this->inputs[$name] = &$new; 
            $old = &$this->inputs[$name]; 
            
        }
        $old[] = &$input; 
        return sizeof($old); 
    }
}

$form = new form(); 
$a = new input('a', '1'); 
$b1 = new input('b', '1'); 
$b2 = new input('b', '2'); 
$c = new input('c', '1'); 
$form->add_input($a); 
$form->add_input($b1); 
$form->add_input($b2); 
$form->add_input($c); 
var_dump($form);

?>