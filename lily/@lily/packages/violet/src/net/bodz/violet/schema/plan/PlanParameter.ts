import PlanParameterTypeInfo from "./PlanParameterTypeInfo";
import _PlanParameter_stuff from "./_PlanParameter_stuff";

export class PlanParameter extends _PlanParameter_stuff<PlanParameter> {

    static _typeInfo: PlanParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PlanParameter;
