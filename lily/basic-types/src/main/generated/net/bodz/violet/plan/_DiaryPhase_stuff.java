package net.bodz.violet.plan;

import net.bodz.lily.entity.IdType;
import net.bodz.lily.template.CoPhase;

@IdType(Integer.class)
public abstract class _DiaryPhase_stuff
        extends CoPhase {

    private static final long serialVersionUID = 1L;

    public static final String SCHEMA_NAME = "violet";
    public static final String TABLE_NAME = "diaryphase";

    public void initNotNulls() {
    }

}
