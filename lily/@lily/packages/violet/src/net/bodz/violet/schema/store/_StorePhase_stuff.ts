import CoPhase from "lily-basic/src/net/bodz/lily/concrete/CoPhase";

import _StorePhase_stuff_TypeInfo from "./_StorePhase_stuff_TypeInfo";

export class _StorePhase_stuff extends CoPhase {

    static _typeInfo: _StorePhase_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _StorePhase_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }
}

export default _StorePhase_stuff;
