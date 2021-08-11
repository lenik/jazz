package user.zoo.bean;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.LoaderException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.rst.AbstractRstObject;
import net.bodz.bas.fmt.rst.IRstHandler;
import net.bodz.bas.fmt.rst.IRstOutput;
import net.bodz.bas.fmt.rst.RstFn;
import net.bodz.bas.fmt.rst.obj.RstSource;
import net.bodz.bas.fmt.xml.IObjectXmlLoader;
import net.bodz.bas.fmt.xml.IXmlOutput;
import net.bodz.bas.fmt.xml.IXmlSerializable;
import net.bodz.bas.fmt.xml.XmlFn;
import net.bodz.bas.fmt.xml.xq.IElement;

@RstSource(bean = true)
public class Animal
        extends AbstractRstObject
        implements
            IXmlSerializable {

    private String name;
    private boolean male;
    private int color;
    private int age;

    public Animal() {
    }

    public Animal(String name, boolean male, int color, int age) {
        this();
        this.name = name;
        this.male = male;
        this.color = color;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMale() {
        return male;
    }

    public void setMale(boolean male) {
        this.male = male;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void writeObject(IRstOutput out)
            throws IOException, FormatException {
        RstFn.defaultDump(this, out);
    }

    @Override
    public IRstHandler getElementHandler() {
        return RstFn.getDefaultHandler(this);
    }

    @Override
    public void writeObject(IXmlOutput out)
            throws XMLStreamException, FormatException {
        XmlFn.dump(this, out);
    }

    @Override
    public void readObject(IElement element)
            throws ParseException, LoaderException {
        IObjectXmlLoader loader = XmlFn.getDefaultLoader(this);
        loader.loadXmlToObject(this, element);
    }

}
