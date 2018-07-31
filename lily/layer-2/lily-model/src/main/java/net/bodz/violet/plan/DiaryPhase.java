package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.model.base.CoCode;

@Table(name = "diaryphase")
public class DiaryPhase
        extends CoCode<DiaryPhase> {

    private static final long serialVersionUID = 1L;

    public DiaryPhase() {
        super();
    }

    public DiaryPhase(DiaryPhase parent) {
        super(parent);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(128);
        sb.append("diaryPhase: ...");
        return sb.toString();
    }

}
