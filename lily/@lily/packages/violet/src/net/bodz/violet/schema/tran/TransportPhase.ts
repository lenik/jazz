import TransportPhaseTypeInfo from "./TransportPhaseTypeInfo";
import _TransportPhase_stuff from "./_TransportPhase_stuff";

export class TransportPhase extends _TransportPhase_stuff {

    static _typeInfo: TransportPhaseTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TransportPhaseTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TransportPhase;
