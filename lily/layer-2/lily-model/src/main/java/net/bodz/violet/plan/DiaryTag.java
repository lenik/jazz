package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.CoTag;

@Table(name = "diarytag")
public class DiaryTag
        extends CoTag<DiaryTag> {

    private static final long serialVersionUID = 1L;

    public DiaryTag() {
        super();
    }

    public DiaryTag(DiaryTag parent) {
        super(parent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryTag: ...");
        return sb.toString();
    }

}
