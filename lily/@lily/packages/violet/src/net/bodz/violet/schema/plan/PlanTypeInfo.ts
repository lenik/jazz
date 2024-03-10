import { SET } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import PlanTag from "./PlanTag";
import PlanValidators from "./PlanValidators";
import _Plan_stuff_TypeInfo from "./_Plan_stuff_TypeInfo";

export class PlanTypeInfo extends _Plan_stuff_TypeInfo {

    readonly validators = new PlanValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.Plan"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            tags: property({ type: SET(PlanTag.TYPE), validator: this.validators.validateTags }),
        });
    }

    static readonly INSTANCE = new PlanTypeInfo();

}

export default PlanTypeInfo;
