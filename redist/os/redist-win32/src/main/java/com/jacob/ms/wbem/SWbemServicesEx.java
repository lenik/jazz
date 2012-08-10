/**
 * JacobGen generated file --- do not edit
 *
 * (http://www.sourceforge.net/projects/jacob-project */
package com.jacob.ms.wbem;

import com.jacob.com.Dispatch;

public class SWbemServicesEx
        extends ISWbemServicesEx {

    public static final String componentName = "clsid:{62E522DC-8CF3-40A8-8B2E-37D595651E40}";

    public SWbemServicesEx() {
        // super(componentName);
        super("winmgmts:");
    }

    public SWbemServicesEx(Dispatch d) {
        super(d);
    }
}
