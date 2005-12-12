
/* Transaction (Submit Once) */
function zlw_af_form_submit(ev) {
    var form = ev.currentTarget; 
    while (form != null) {
        // assert form.tagName; 
        if (form.tagName.toLowerCase() == "form")
            break; 
        form = form.parentElement; 
    }
    
    // assert form

    // checking constraints
    var formId = form.id; 
    
    var formValidate = eval("form_" + formId + "_validate"); 
    if (formValidate != null)
        if (! formValidate())
            return false; 
    
    // disable all controls
    for (var i in form.elements) {
		var element = form.elements[i]; 
        element.disabled = true; 
    }
    return true; 
}


/* Constraints */

function zlw_af_cons_and(a, b) {
}

function zlw_af_cons_or(a, b) {
}

