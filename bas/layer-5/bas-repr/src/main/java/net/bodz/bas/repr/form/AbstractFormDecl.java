package net.bodz.bas.repr.form;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

import net.bodz.bas.c.reflect.NoSuchPropertyException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.ui.dom1.MutableUiElement;

public abstract class AbstractFormDecl
        extends MutableUiElement
        implements
            IFormDecl {

    private static final long serialVersionUID = 1L;

    @Override
    public Collection<IFormProperty> getProperties() {
        return getProperties(PropertyFilter.TRUE);
    }

    @Override
    public Collection<PropertyGroup> getPropertyGroups() {
        return getPropertyGroups(PropertyFilter.TRUE);
    }

    @Override
    public Collection<PropertyGroup> getPropertyGroups(PropertyFilter filter) {
        Map<PropertyCategory, PropertyGroup> map;
        map = new TreeMap<PropertyCategory, PropertyGroup>(PropertyGroupComparator.INSTANCE);

        for (IFormProperty fieldDecl : getProperties(filter)) {
            PropertyCategory category = fieldDecl.getCategory();
            if (category == null)
                category = PropertyCategory.NULL;

            PropertyGroup group = map.get(category);
            if (group == null)
                map.put(category, group = new PropertyGroup(this, category));

            group.add(fieldDecl);
        }
        return map.values();
    }

    @Override
    public List<PropertyChain> resolvePattern(String path)
            throws NoSuchPropertyException, ParseException {
        List<PropertyChain> list = new ArrayList<>();

        int dot = path.indexOf('.');
        String head, remaining;
        if (dot == -1) {
            head = path;
            remaining = null;
        } else {
            head = path.substring(0, dot);
            remaining = path.substring(dot + 1);
        }

        if (head.contains("*")) {
            Pattern pattern = null;
            if (!head.equals("*")) {
                String regex = head.replace("*", ".*");
                regex = regex.replace("?", ".");
                pattern = Pattern.compile("^" + regex + "$");
            }

            for (IFormProperty member : this.getProperties()) {
                // regex only match readable properties.
                if (!member.getProperty().isReadable())
                    continue;

                String name = member.getName();
                if (pattern != null)
                    if (!pattern.matcher(name).matches())
                        continue;

                PropertyChain chain = member.resolvePropertyChain(remaining);
                list.add(chain);
            }

        } else {
            IFormProperty member = this.getProperty(head);
            if (member == null)
                throw new NoSuchPropertyException("Bad head: " + head);

            PropertyChain chain = member.resolvePropertyChain(remaining);
            list.add(chain);
        }
        return list;
    }

}
