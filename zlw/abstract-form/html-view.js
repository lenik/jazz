
/**
 * Type/Constraints Checking
 * Safe Transaction (Submit Once)
 */
function zlw_af_form_submit(ev) {
    var form = ev.currentTarget; 
    while (form != null) {
        // assert form.tagName; 
        if (form.tagName.toLowerCase() == "form")
            break; 
        form = form.parentElement; 
    }
    // assert form

    for (var i in form.elements) {
        var elm = form.elements[i]; 
        if (elm.af_type != null) {
            // type checking
            var func = eval("zlw_af_tc_" + elm.af_type); 
            if (func != null) {
                try {
                    func(elm.af_typep, elm.value); 
                } catch (e) {
                    alert("Type Checking Failure: \n[" + elm.af_type + "] " + e); 
                    elm.focus(); 
                    return false; 
                }
            }
        }
        if (elm.af_constraints != null) {
            // constraints checking
            try {
                zlw_af_cc_list(elm.af_constraints, elm, elm.value); 
            } catch (e) {
                alert("Constraint Checking Failure: \n" + e); 
                elm.focus(); 
                return false; 
            }
        }
    }
    
    // disable all controls
    for (var i in form.elements) {
        var elm = form.elements[i]; 
        elm.disabled = true; 
    }
    return true; 
}

function zlw_af_tc_number(typep, value) {
    // langex::replaceAll, string.trim != ""
    if (1 * value != value)
        throw "Not a number. "; 
    return true; 
}

function zlw_af_tc_int(typep, value) {
    var i = parseInt(value); 
    if (i != value)
        throw "Not an integer. "; 
    return true; 
}

function zlw_af_tc_id(typep, value) {
    var reWord = /^[a-z_][a-z0-9_]*$/i; 
    if (! reWord.test(value))
        throw "Not an identifier. "; 
    return true; 
}

function zlw_af_tc_word(typep, value) {
    var reWord = /^(\w|[\-\.])+$/; 
    if (! reWord.test(value))
        throw "Not a word. "; 
    return true; 
}

function zlw_af_tc_date(typep, value) {
    try {
        var date = Date.parse(value); 
        if (date == null)
            throw "Illegal date format"; 
    } catch (e) {
        throw "Illegal date format: " + e; 
    }
    return true; 
}

function zlw_af_tc_time(typep, value) {
    var reTime = /^(\d+):(\d+)(?::(\d+)(?:\.(\d*))?)?$/; 
    var m = value.match(reTime); 
    if (m == null)
        throw "Illegal time format"; 
    if (m[1] >= 24)
        throw "Illegal time format: Hour"; 
    if (m[2] >= 60)
        throw "Illegal time format: Minute"; 
    if (m[3] != null && (m[3] >= 60))
        throw "Illegal time format: Second"; 
    return true; 
}

function zlw_af_tc_email(typep, value) {
    var reEmail = /^\s*([a-z0-9_\-\.]+)\@([a-z0-9\-]+(?:\.[a-z0-9\-]+)*)\s*$/i; 
    var m = value.match(reEmail); 
    if (m == null)
        throw "Illegal e-mail format"; 
    var account = m[1]; 
    var domain = m[2]; 
    return true; 
}

function zlw_af_cc_list(constraints, variant, value) {
    for (var i = 0; i < constraints.length; i++) {
        var constraint = constraints[i]; 
        zlw_af_cc_item(constraint, variant, value); 
    }
    return true; 
}

function zlw_af_cc_item(constraint, variant, value) {
    var func = eval("zlw_af_cc_" + constraint.model); 
    try {
        func(constraint, variant, value); 
    } catch (e) {
        if (constraint.reason != null) {
            var errstr = e.split(":", 2); 
            e = ""; 
            e += errstr[0] + "\n"; 
            if (constraint.name != null)
                e += "[" + constraint.name + "] "; 
            e += constraint.reason; 
        }
        if (constraint.level == "warning") {
            if (confirm(e))
                return true; 
        }
        throw e; 
    }
    return true; 
}

function zlw_af_cc_range(constraint, variant, value) {
    // min min-exclusive max max-exclusive
    var n; 
    if (value == null)
        n = 0; 
    else if (variant.af_type == 'string')
        n = value.length(); 
    else
        n = 1 * value; 
    
    if (constraint.min != null)
        if (constraint.min_excluded) {
            if (n <= constraint.min)
                throw "Too small: Less than (or equal to) " + constraint.min; 
        } else {
            if (n < constraint.min)
                throw "Too small: Less than " + constraint.min; 
        }
    if (constraint.max != null)
        if (constraint.max_excluded) {
            if (n >= constraint.max)
                throw "Too large: Greater than (or equal to) " + constraint.max; 
        } else {
            if (n > constraint.max)
                throw "Too large: Greater than " + constraint.max; 
        }
    return true; 
}

function zlw_af_cc_pattern(constraint, variant, value) {
    // regex case-insensitive dot-all multiline comment
    var s = "" + value; 
    var flags = ''; 
    if (constraint.case_insensitive) flags += "i"; 
    // NOT SUPPORTED.  if (constraint.dot_all) flags += "s"; 
    if (constraint.multiline) flags += "m"; 
    // NOT SUPPORTED.  if (constraint.comment) flags += "x"; 
    var re = new RegExp(constraint.regex, flags); 
    if (! re.test(s))
        throw "Illegal Syntax: Doesn't match the pattern " + constraint.regex; 
    return true; 
}

function zlw_af_cc_and(constraint, variant, value) {
    // item[]
    for (var i in constraint.item) {
        var item = constraint.item[i]; 
        try {
            zlw_af_cc_item(item, variant, value); 
        } catch (e) {
            throw e; 
        }
    }
    return true; 
}

function zlw_af_cc_or(constraint, variant, value) {
    // item[]
    var e_first = null; 
    for (var i in constraint.item) {
        var item = constraint.item[i]; 
        try {
            zlw_af_cc_item(item, variant, value); 
            return true; 
        } catch (e) {
            if (e_first == null)
                e_first = e; 
        }
    }
    throw "(May be) " + e_first; 
}

function zlw_af_cc_xor(constraint, variant, value) {
    // item[]
    var e_only = null; 
    for (var i in constraint.item) {
        var item = constraint.item[i]; 
        try {
            zlw_af_cc_item(item, variant, value); 
        } catch (e) {
            if (e_only != null)
                throw "Illegal Value: Too many of Xor-Constraints"; 
            e_only = e; 
        }
    }
    if (e_only == null)
        throw "Illegal Value: None of Xor-Constraints"; 
    return true; 
}

function zlw_af_cc_not(constraint, variant, value) {
    // regular
    try {
        zlw_af_cc_item(constraint.regular, variant, value); 
    } catch (e) {
        return true; 
    }
    throw "Illegal Value: Not(Reversed)-Constraint"; 
}
