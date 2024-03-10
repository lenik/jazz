import PlanTag from "./PlanTag";
import PlanTypeInfo from "./PlanTypeInfo";
import _Plan_stuff from "./_Plan_stuff";

export class Plan extends _Plan_stuff {

    static _typeInfo: PlanTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    tags?: Set<PlanTag>

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Plan;
