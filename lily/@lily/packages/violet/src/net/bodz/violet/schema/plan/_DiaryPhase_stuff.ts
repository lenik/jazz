import CoPhase from "@lily/basic/src/net/bodz/lily/concrete/CoPhase";

import _DiaryPhase_stuff_TypeInfo from "./_DiaryPhase_stuff_TypeInfo";

export class _DiaryPhase_stuff extends CoPhase {

    static _typeInfo: _DiaryPhase_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _DiaryPhase_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }
}

export default _DiaryPhase_stuff;
