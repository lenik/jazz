package net.bodz.violet.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.site.json.IMutableJsonResponse;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.t.base.CoMessageIndex;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiaryParty;

@ObjectType(Diary.class)
public class DiaryIndex
        extends CoMessageIndex<Diary, DiaryMask> {

    public static final String SCHEMA = "diary";

    @Override
    protected void save(IVariantMap<String> q, Diary obj, IMutableJsonResponse resp) {
        super.save(q, obj, resp);

        // Update diary parties.
        long diaryId = obj.id();
        List<DiaryParty> parties = obj.getParties();
        if (parties != null) {
            DiaryPartyMapper partyMapper = getDataContext().requireMapper(DiaryPartyMapper.class);

            DiaryPartyMask forThisDiary = new DiaryPartyMask();
            forThisDiary.setDiaryId(diaryId);
            // partyMapper.deleteFor(forThisDiary);
            Map<Long, DiaryParty> olds = new HashMap<>();
            for (DiaryParty old : partyMapper.filter(forThisDiary, null))
                olds.put(old.id(), old);

            for (DiaryParty party : obj.getParties()) {
                if (party.id() == null) {
                    partyMapper.insert(party);
                } else {
                    DiaryParty old = olds.remove(party.id());
                    if (!party.partialEquals(old))
                        partyMapper.update(party);
                }
            }
            for (DiaryParty miss : olds.values())
                partyMapper.delete(miss.id());
        }

    }

}
