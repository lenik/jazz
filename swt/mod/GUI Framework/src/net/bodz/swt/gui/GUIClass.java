package net.bodz.swt.gui;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.Comparator;
import java.util.TreeMap;

public class GUIClass {

    private final GUIClass            prev;
    private Class<?>                  clazz;
    private GUIHint                   hint;

    public TreeMap<Member, GUIMember> members;

    public GUIClass(GUIClass prev, Class<?> clazz) {
        this.prev = prev;
        this.clazz = clazz;
        this.hint = new GUIHint(null, clazz);

        Comparator<Member> comparator = new Comparator<Member>() {

            @Override
            public int compare(Member a, Member b) {
                return sort(a, b);
            }

        };
        this.members = new TreeMap<Member, GUIMember>(comparator);

        importFields();
        importProperties();
        importMethods();
    }

    protected int sort(Member a, Member b) {
        return 0;
    }

    void importFields() {
        for (Field field : clazz.getDeclaredFields()) {
            GUIField guifield = new GUIField(field);
        }
    }

    void importProperties() {

    }

    void importMethods() {

    }

}
