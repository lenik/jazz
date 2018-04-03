package net.bodz.violet.plan.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.site.file.ItemFile;
import net.bodz.bas.site.file.UploadFn;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.model.mx.CoMessageIndex;
import net.bodz.violet.plan.Diary;
import net.bodz.violet.plan.DiaryParty;
import net.bodz.violet.plan.DiaryProperties;

@ObjectType(Diary.class)
public class DiaryIndex
        extends CoMessageIndex<Diary, DiaryMask> {

    public static final String SCHEMA = "diary";

    @Override
    protected void save(IVariantMap<String> q, Diary obj, AjaxResult result) {
        DiaryProperties properties = obj.getProperties();
        if (properties != null) {
            List<ItemFile> images = properties.getImages();
            UploadFn.submitFiles(images, SCHEMA, lazyId(obj));
        }

        super.save(q, obj, result);

        // Update diary parties.
        long diaryId = obj.getId();
        List<DiaryParty> parties = obj.getParties();
        if (parties != null) {
            DiaryPartyMapper partyMapper = getDataContext().requireMapper(DiaryPartyMapper.class);

            DiaryPartyMask forThisDiary = new DiaryPartyMask();
            forThisDiary.setDiaryId(diaryId);
            // partyMapper.deleteFor(forThisDiary);
            Map<Long, DiaryParty> olds = new HashMap<>();
            for (DiaryParty old : partyMapper.filter(forThisDiary))
                olds.put(old.getId(), old);

            for (DiaryParty party : obj.getParties()) {
                if (party.getId() == null) {
                    partyMapper.insert(party);
                } else {
                    DiaryParty old = olds.remove(party.getId());
                    if (!party.partialEquals(old))
                        partyMapper.update(party);
                }
            }
            for (DiaryParty miss : olds.values())
                partyMapper.delete(miss.getId());
        }

    }

}
