package net.bodz.violet.edu;

import net.bodz.lily.template.CoTag;

public class CourseKitTag
        extends CoTag<CourseKitTag> {

    private static final long serialVersionUID = 1L;

    public CourseKitTag() {
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append(super.toString());
        return sb.toString();
    }

}
