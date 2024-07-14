import PlanParameterTypeTypeInfo from "./PlanParameterTypeTypeInfo";
import _PlanParameterType_stuff from "./_PlanParameterType_stuff";

export class PlanParameterType extends _PlanParameterType_stuff<PlanParameterType> {

    static _typeInfo: PlanParameterTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanParameterTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PlanParameterType;
