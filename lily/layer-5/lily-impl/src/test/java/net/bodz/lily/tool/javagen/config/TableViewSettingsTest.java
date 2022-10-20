package net.bodz.lily.tool.javagen.config;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.api.ElementHandlerException;
import net.bodz.bas.fmt.rst.RstFn;

public class TableViewSettingsTest {

    public static void main(String[] args)
            throws ElementHandlerException, IOException, ParseException {
        TableViewSettings tvc = new TableViewSettings();
        ColumnSettings foo = tvc.resolveColumn("foo");
        foo.description = "foo bar";
        ColumnSettings age = tvc.resolveColumn("age");
        age.description = "how old are you?";
        String rst = RstFn.toString(tvc);
        System.out.println(rst);
        System.out.println("-----------");

        TableViewSettings renew = new TableViewSettings();
        RstFn.loadFromRst(renew, rst);
        String other = RstFn.toString(renew);
        System.out.println(other);
    }

}
