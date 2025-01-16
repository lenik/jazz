import CoParameterTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoParameterTypeInfo";

import _PlanParameterType_stuff_Validators from "./_PlanParameterType_stuff_Validators";

export class _PlanParameterType_stuff_TypeInfo extends CoParameterTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "planparm";

    readonly validators = new _PlanParameterType_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanParameterType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _PlanParameterType_stuff_TypeInfo;

export const _PlanParameterType_stuff_TYPE = _PlanParameterType_stuff_TypeInfo.INSTANCE;
