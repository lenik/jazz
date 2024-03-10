import PlanDoParameterTypeInfo from "./PlanDoParameterTypeInfo";
import _PlanDoParameter_stuff from "./_PlanDoParameter_stuff";

export class PlanDoParameter extends _PlanDoParameter_stuff<PlanDoParameter> {

    static _typeInfo: PlanDoParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanDoParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PlanDoParameter;
