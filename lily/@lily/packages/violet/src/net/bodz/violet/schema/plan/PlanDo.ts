import PlanDoTypeInfo from "./PlanDoTypeInfo";
import _PlanDo_stuff from "./_PlanDo_stuff";

export class PlanDo extends _PlanDo_stuff {

    static _typeInfo: PlanDoTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanDoTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PlanDo;
