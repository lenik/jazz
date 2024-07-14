import PlanDoParameterTypeTypeInfo from "./PlanDoParameterTypeTypeInfo";
import _PlanDoParameterType_stuff from "./_PlanDoParameterType_stuff";

export class PlanDoParameterType extends _PlanDoParameterType_stuff<PlanDoParameterType> {

    static _typeInfo: PlanDoParameterTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanDoParameterTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PlanDoParameterType;
