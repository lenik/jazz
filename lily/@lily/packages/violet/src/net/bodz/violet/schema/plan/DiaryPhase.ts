import DiaryPhaseTypeInfo from "./DiaryPhaseTypeInfo";
import _DiaryPhase_stuff from "./_DiaryPhase_stuff";

export class DiaryPhase extends _DiaryPhase_stuff {

    static _typeInfo: DiaryPhaseTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = DiaryPhaseTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default DiaryPhase;
