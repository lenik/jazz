<?php


# %% page($page, $page_size) %
class phpx_querymode_page {
    private $page;
    private $page_size;
    
    function phpx_querymode_page($page = 0, $page_size = 10) {
        $this->page = $page;
        $this->page_size = $page_size; 
    }
    
    function pre() {
        $size = $this->page_size;
        $offset = $this->page * $this->page_size; 
        return "\$sql .= ' limit $offset, $size'; ";
    }
    
    function post() {
        return null; 
    }
}

?>