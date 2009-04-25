package net.bodz.dist.win32;

import com.jacob.com.ComThread;
import com.jacob.com.Variant;
import com.jacob.ms.wbem.ISWbemObjectSet;
import com.jacob.ms.wbem.SWbemServicesEx;

public class WMIInfo {

    public static void main(String[] args) {
        ComThread.InitSTA();

        SWbemServicesEx services = new SWbemServicesEx();
        ISWbemObjectSet win32Processors = services.instancesOf("Win32_Processor");
        // ISWbemObjectSet win32Processors = new
        // SWbemServices().instancesOf("Win32_Processor");
        int count = win32Processors.getCount();
        System.out.println("Count=" + count);

        String programId = win32Processors.getProgramId();
        System.out.println("Program ID=" + programId);

        Variant newEnum = win32Processors.get_NewEnum();
        newEnum.getDispatch();

        // ISWbemObject item = win32Processors.item("1");
        // String t = item.getObjectText_();
        // System.out.println(t);

        System.out.println(newEnum);

        ComThread.Release();
    }

}
