package net.bodz.bas.repr.form;

import java.util.LinkedHashMap;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.ParseException;

public class PathFieldMap
        extends LinkedHashMap<String, PathField> {

    private static final long serialVersionUID = 1L;

    PathFieldAccessOptons optons;

    public void parseAndAdd(IFormDecl formDecl, String... pathProperties)
            throws NoSuchPropertyException, ParseException {
        if (formDecl == null)
            throw new NullPointerException("formDecl");

        PathFieldList pathFieldList = new PathFieldList();

        pathFieldList.parseAndAdd(formDecl, pathProperties);

        for (PathField pathField : pathFieldList)
            put(pathField.getPath(), pathField);
    }

}
