import DiaryPartyTypeInfo from "./DiaryPartyTypeInfo";
import _DiaryParty_stuff from "./_DiaryParty_stuff";

export class DiaryParty extends _DiaryParty_stuff {

    static _typeInfo: DiaryPartyTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryPartyTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default DiaryParty;
