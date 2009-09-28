package net.bodz.mda.mim;

public interface BoundaryType {

    BoundaryMatcher getStartMatcher();

    BoundaryMatcher getEndMatcher();

}
