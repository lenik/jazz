package net.bodz.bas.gui.css3;

public interface ICss3StyleDeclaration
        extends ICss3StyleProperties {

    /**
     * Get the parent style class.
     * 
     * @return Parent style class whose properties are inherited by default. Returns
     *         <code>null</code> if no parent.
     */
    ICss3StyleDeclaration getParent();

    void setParent(ICss3StyleDeclaration parent);

}
