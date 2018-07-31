package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.model.base.CoCode;

@Table(name = "diaryparm")
public class DiaryParameter
        extends CoCode<DiaryParameter> {

    private static final long serialVersionUID = 1L;

    public DiaryParameter() {
        super();
    }

    public DiaryParameter(DiaryParameter parent) {
        super(parent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryParameter: ...");
        return sb.toString();
    }

}
