import CoTagTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _PlanDoTag_stuff_Validators from "./_PlanDoTag_stuff_Validators";

export class _PlanDoTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plandotag";

    readonly validators = new _PlanDoTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDoTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _PlanDoTag_stuff_TypeInfo;
