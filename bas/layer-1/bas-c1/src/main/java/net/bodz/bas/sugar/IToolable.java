package net.bodz.bas.sugar;

public interface IToolable {

    /**
     * Mixin:
     * 
     * You should implementation this method as:
     * 
     * <pre>
     * return new Tooling&lt;H&gt;(this);
     * </pre>
     */
    Tooling tooling();

}
