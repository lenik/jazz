package net.bodz.bas.typer.std;

import java.util.HashMap;
import java.util.Map;

public class AttributeDescriptor
        implements
            IAttributeDescriptor {

    String label;
    String description;
    String icon;
    Class<?> type;
    Map<Class<?>, Object> typers;

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    public <T> T getTyper(Class<T> typerClass) {
        if (typers == null)
            return null;
        Object typer = typers.get(typerClass);
        if (typer == null)
            return null;
        return typerClass.cast(typer);
    }

    public synchronized <T> void setTyper(Class<T> typerClass, T typer) {
        if (typers == null)
            typers = new HashMap<>();
        typers.put(typerClass, typer);
    }

}
