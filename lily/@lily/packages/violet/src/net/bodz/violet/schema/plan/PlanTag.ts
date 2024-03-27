import PlanTagTypeInfo from "./PlanTagTypeInfo";
import _PlanTag_stuff from "./_PlanTag_stuff";

export class PlanTag extends _PlanTag_stuff<PlanTag> {

    static _typeInfo: PlanTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PlanTag;
