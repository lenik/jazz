import PlanDoTagTypeInfo from "./PlanDoTagTypeInfo";
import _PlanDoTag_stuff from "./_PlanDoTag_stuff";

export class PlanDoTag extends _PlanDoTag_stuff<PlanDoTag> {

    static _typeInfo: PlanDoTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanDoTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PlanDoTag;
