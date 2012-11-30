package net.bodz.bas.gui.css3;

public interface ICss3StyleClass
        extends ICss3Properties {

    /**
     * Get the parent style class.
     * 
     * @return Parent style class whose properties are inherited by default. Returns
     *         <code>null</code> if no parent.
     */
    ICss3StyleClass getParent();

}
