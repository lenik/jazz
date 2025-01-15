import CoPhase from "lily-basic/src/net/bodz/lily/concrete/CoPhase";

import _TransportPhase_stuff_TypeInfo from "./_TransportPhase_stuff_TypeInfo";

export class _TransportPhase_stuff extends CoPhase {

    static _typeInfo: _TransportPhase_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _TransportPhase_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
    }
}

export default _TransportPhase_stuff;
