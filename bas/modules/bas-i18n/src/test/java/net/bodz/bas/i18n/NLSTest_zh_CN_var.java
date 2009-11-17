package net.bodz.bas.i18n;

import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class NLSTest_zh_CN_var
        extends ResourceBundle {

    Properties properties;

    public NLSTest_zh_CN_var() {
        properties = new Properties();
        properties.setProperty( "key3", "zh_CN_var3" );
    }

    @SuppressWarnings ( "unchecked")
    @Override
    public Enumeration<String> getKeys() {
        Enumeration<?> keys = properties.keys();
        return (Enumeration<String>) keys;
    }

    @Override
    protected Object handleGetObject( String key ) {
        return properties.get( key );
    }

}
