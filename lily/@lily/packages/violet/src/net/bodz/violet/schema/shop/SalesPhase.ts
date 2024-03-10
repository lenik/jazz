import SalesPhaseTypeInfo from "./SalesPhaseTypeInfo";
import _SalesPhase_stuff from "./_SalesPhase_stuff";

export class SalesPhase extends _SalesPhase_stuff {

    static _typeInfo: SalesPhaseTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = SalesPhaseTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default SalesPhase;
