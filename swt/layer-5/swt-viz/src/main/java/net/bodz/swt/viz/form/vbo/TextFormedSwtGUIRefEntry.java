package net.bodz.swt.viz.form.vbo;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.gui.dom1.DecoratedGUIElement;
import net.bodz.bas.rtx.QueryException;
import net.bodz.bas.typer.std.IFormatter;
import net.bodz.bas.typer.std.IParser;
import net.bodz.swt.viz.ISwtControlStyleDeclaration;
import net.bodz.swt.viz.ISwtGUIRefEntry;

public class TextFormedSwtGUIRefEntry<T>
        extends DecoratedGUIElement
        implements ISwtGUIRefEntry<String> {

    private static final long serialVersionUID = 1L;

    private IParser<T> parser;
    private IFormatter<T> formatter;

    public TextFormedSwtGUIRefEntry(ISwtGUIRefEntry<T> _orig, IParser<T> parser, IFormatter<T> formatter) {
        super(_orig);

        if (parser == null)
            throw new NullPointerException("parser");
        if (formatter == null)
            throw new NullPointerException("formatter");

        this.parser = parser;
        this.formatter = formatter;
    }

    @Override
    public ISwtGUIRefEntry<T> getWrapped() {
        return (ISwtGUIRefEntry<T>) super.getWrapped();
    }

    /** ⇱ Implementation Of {@link Ref}. */
    ;

    @Override
    public Class<String> getValueType() {
        return String.class;
    }

    @Override
    public String get() {
        T value = getWrapped().get();

        if (formatter == null)
            return value.toString();
        else
            return formatter.format(value);
    }

    @Override
    public void set(String text) {
        T value;
        try {
            value = parser.parse(text);
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        getWrapped().set(value);
    }

    @Override
    public void remove() {
        getWrapped().remove();
    }

    /** ⇱ Implementation Of {@link IRefEntry}. */
    ;

    @Override
    public Object query(Object specification)
            throws QueryException {
        return getWrapped().query(specification);
    }

    @Override
    public Object query(String specificationId)
            throws QueryException {
        return getWrapped().query(specificationId);
    }

    @Override
    public <spec_t> spec_t query(Class<spec_t> specificationType)
            throws QueryException {
        return getWrapped().query(specificationType);
    }

    /** ⇱ Implementation Of {@link ISwtGUIRefEntry}. */
    ;

    @Override
    public ISwtControlStyleDeclaration getStyle() {
        return getWrapped().getStyle();
    }

}