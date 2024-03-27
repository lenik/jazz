import PlanPhaseTypeInfo from "./PlanPhaseTypeInfo";
import _PlanPhase_stuff from "./_PlanPhase_stuff";

export class PlanPhase extends _PlanPhase_stuff {

    static _typeInfo: PlanPhaseTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanPhaseTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PlanPhase;
