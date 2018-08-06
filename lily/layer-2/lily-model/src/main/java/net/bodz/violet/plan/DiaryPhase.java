package net.bodz.violet.plan;

import javax.persistence.Table;

import net.bodz.lily.template.CoPhase;

@Table(name = "diaryphase")
public class DiaryPhase
        extends CoPhase {

    private static final long serialVersionUID = 1L;

    public DiaryPhase() {
        super();
    }

    public DiaryPhase(DiaryPhase parent) {
        super(parent);
    }

}
