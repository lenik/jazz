package net.bodz.bas.ui.css3;

public interface ICss3StyleDeclaration
        extends ICss3Properties {

    /**
     * Get the parent style class.
     * 
     * @return Parent style class whose properties are inherited by default. Returns
     *         <code>null</code> if no parent.
     */
    ICss3StyleDeclaration getParent();

    String get(String key, int maxInherits);

    ICss3StyleDeclaration NULL = new NullCss3StyleDeclaration();

}