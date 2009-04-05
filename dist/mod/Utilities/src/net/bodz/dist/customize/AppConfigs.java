package net.bodz.dist.customize;

import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;

public class AppConfigs {

    static ClassLoader clJRE;
    static ClassLoader clSWT; 
    static ClassLoader clConfig; 
    static URL urlJRE; 
    static URL urlSWT; 
    static URL urlConfigCL; 
    static URL urlConfig; 
    
    static Logger logger = Logger.getLogger(AppConfigs.class); 
    
    static {
        clJRE = ClassLoader.getSystemClassLoader();
        clSWT = SWT.class.getClassLoader(); 
        clConfig = AppConfigs.class.getClassLoader(); 
        urlJRE = clJRE.getResource(""); 
        urlSWT = SWT.class.getResource("");
        urlConfigCL = clConfig.getResource(""); 
        urlConfig = AppConfigs.class.getResource(""); 
    }
    
    public static String dirname(URL url) {
        assert url != null; 
        if ("jar".equals(url.getProtocol())) {
            String s = url.getPath(); 
            if (s.startsWith("file:"))  // file:/...jar!/...
                s = s.substring(5); 
            int index = s.lastIndexOf("!/"); 
            assert index >= 0; 
            s = s.substring(0, index);  // /.../dir/name.jar
            index = s.lastIndexOf('/'); 
            if (index != 0)             // /.../dir
                s = s.substring(0, index); 
            return s; 
        }
        return url.getPath();
    }

    public static String getJREPath() {
        if (urlJRE == null)
            throw new RuntimeException("Can't determine the path of JRE"); 
        return dirname(urlJRE); 
    }

    public static String getSWTPath() {
        if (urlSWT == null)
            throw new RuntimeException("Can't determine the path of SWT"); 
        return dirname(urlSWT); 
    }

    public static String getConfigPath() {
        if (urlConfig == null)
            throw new RuntimeException("Can't determine the path of Config"); 
        return dirname(urlConfig); 
    }
    
    public static String getConfigPath(String fileName) {
        return getConfigPath() + "/" + fileName; 
    }
    
    public static void main(String[] args) {
        System.out.println("JREPath: " + getJREPath()); 
        System.out.println("SWTPath: " + getSWTPath()); 
        System.out.println("ConfigPath: " + getConfigPath()); 
    }
}
