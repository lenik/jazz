import CoParameterTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoParameterTypeInfo";

import _PlanDoParameterType_stuff_Validators from "./_PlanDoParameterType_stuff_Validators";

export class _PlanDoParameterType_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plandoparm";

    readonly validators = new _PlanDoParameterType_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDoParameterType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _PlanDoParameterType_stuff_TypeInfo;

export const _PlanDoParameterType_stuff_TYPE = _PlanDoParameterType_stuff_TypeInfo.INSTANCE;
