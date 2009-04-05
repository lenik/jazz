package net.bodz.dist.ins;

public interface Scheme {

    String getCaption();

    String getDescription();

    boolean isIncluded(IComponent component);

    boolean isAutomatic();

}
