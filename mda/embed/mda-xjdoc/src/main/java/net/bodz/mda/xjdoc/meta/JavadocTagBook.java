package net.bodz.mda.xjdoc.meta;

import net.bodz.bas.lang.negotiation.INegotiation.IParameter;
import net.bodz.bas.lang.negotiation.NegotiationException;
import net.bodz.mda.xjdoc.util.IImportMapProvider;
import net.bodz.mda.xjdoc.util.ImportMap;

public class JavadocTagBook
        extends TagBook
        implements IImportMapProvider {

    ImportMap importMap;

    /**
     * 
     * In different stages, the type context is different:
     * <ul>
     * <li>For javadoc -> model, the context should be sourceFileImports. (
     * {@link ImportMap#normalize(String) expand} is used)
     * <li>For model -> ff, the context should be classImports. ( {@link ImportMap#add(String)
     * import} is used)
     * <li>For ff -> model, the context should be classImports. (
     * {@link ImportMap#normalize(String) expand} is used)
     * </ul>
     */
    public JavadocTagBook() {
        setTagType("author", DocTagType.INSTANCE.repeat());
        setTagType("exception", DocTagType.INSTANCE.typed());
        setTagType("param", DocTagType.INSTANCE.keyed());
        setTagType("return", DocTagType.INSTANCE);
        setTagType("throws", DocTagType.INSTANCE.typed());
    }

    @Override
    public boolean negotiate(IParameter param)
            throws NegotiationException {
        if (param.is(ImportMap.class)) {
            importMap = (ImportMap) param.getValue();
            return true;
        } else
            return false;
    }

    @Override
    public ImportMap getImportMap() {
        return importMap;
    }

    public void setImportMap(ImportMap importMap) {
        this.importMap = importMap;
    }

}
