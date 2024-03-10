import CoTagTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _PlanTag_stuff_Validators from "./_PlanTag_stuff_Validators";

export class _PlanTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plantag";

    readonly validators = new _PlanTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _PlanTag_stuff_TypeInfo;
