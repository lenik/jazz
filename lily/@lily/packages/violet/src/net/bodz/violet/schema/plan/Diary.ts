import type { List } from "skel01-core/src/lang/basetype";

import DiaryParty from "./DiaryParty";
import DiaryTypeInfo from "./DiaryTypeInfo";
import _Diary_stuff from "./_Diary_stuff";

export class Diary extends _Diary_stuff {

    static _typeInfo: DiaryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    parties?: List<DiaryParty>

    constructor(o?: any) {
        super(o);
    }
}

export default Diary;
