package net.bodz.violet.edu;

import net.bodz.lily.template.CoTag;

public class CourseTag
        extends CoTag<CourseTag> {

    private static final long serialVersionUID = 1L;

    public CourseTag() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
