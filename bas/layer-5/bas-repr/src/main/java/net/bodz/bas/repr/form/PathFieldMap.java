package net.bodz.bas.repr.form;

import java.util.LinkedHashMap;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.ParseException;

public class PathFieldMap
        extends LinkedHashMap<String, PathField> {

    private static final long serialVersionUID = 1L;

    private IFormDecl formDecl;
    private FieldDeclBuilder formFieldBuilder = new FieldDeclBuilder();

    public PathFieldMap(IFormDecl formDecl) {
        if (formDecl == null)
            throw new NullPointerException("formDecl");
        this.formDecl = formDecl;
    }

    public FieldDeclBuilder getFormFieldBuilder() {
        return formFieldBuilder;
    }

    public void setFormFieldBuilder(FieldDeclBuilder formFieldBuilder) {
        this.formFieldBuilder = formFieldBuilder;
    }

    public void parse(String spec, String... pathProperties)
            throws NoSuchPropertyException, ParseException {
        PathFieldsBuilder builder = new PathFieldsBuilder(formFieldBuilder);

        for (char c : spec.toCharArray()) {
            switch (c) {
            case 'n': // TODO
                builder.fromPropertyPaths(formDecl, "index");
                break;
            case 'i':
                builder.fromPropertyPaths(formDecl, "id");
                break;
            case 's':
                builder.fromPropertyPaths(formDecl, "priority", "creationDate", "lastModifiedDate", "flags", "state");
                break;
            case 'a':
                builder.fromPropertyPaths(formDecl, "accessMode", "ownerUser", "ownerGroup");
                break;
            case '*':
                builder.fromPropertyPaths(formDecl, pathProperties);
                break;
            default:
                throw new IllegalArgumentException("Bad column group specifier: " + c);
            }
        }

        for (PathField pathField : builder.getFields())
            put(pathField.getPath(), pathField);
    }

}
