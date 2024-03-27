import StorePhaseTypeInfo from "./StorePhaseTypeInfo";
import _StorePhase_stuff from "./_StorePhase_stuff";

export class StorePhase extends _StorePhase_stuff {

    static _typeInfo: StorePhaseTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = StorePhaseTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default StorePhase;
