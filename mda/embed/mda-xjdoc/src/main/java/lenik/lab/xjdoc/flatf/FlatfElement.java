package lenik.lab.xjdoc.flatf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FlatfElement
        implements IFlatfElement, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private final List<IFlatfAttribute> attributes;

    public FlatfElement(String name) {
        this(name, new ArrayList<IFlatfAttribute>());
    }

    public FlatfElement(String name, List<IFlatfAttribute> attributes) {
        if (name == null)
            throw new NullPointerException("name");
        if (attributes == null)
            throw new NullPointerException("attributes");
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null)
            throw new NullPointerException("name");
        this.name = name;
    }

    @Override
    public List<IFlatfAttribute> getAttributes() {
        return attributes;
    }

    @Override
    public int getAttributeCount() {
        return attributes.size();
    }

    @Override
    public String getAttributeName(int index) {
        return attributes.get(index).getName();
    }

    @Override
    public String getAttributeText(int index) {
        return attributes.get(index).getText();
    }

    @Override
    public void addAttribute(String name, String text) {
        FlatfAttribute entry = new FlatfAttribute(name, text);
        attributes.add(entry);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(attributes.size() * 80);
        for (IFlatfAttribute entry : getAttributes()) {
            sb.append(entry.getName());
            sb.append(" = ");
            sb.append(entry.getText());
            sb.append('\n');
        }
        return sb.toString();
    }

}
