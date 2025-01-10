import { SET } from "skel01-core/src/lang/baseinfo";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import Plan from "./Plan";
import { PlanTag_TYPE } from "./PlanTagTypeInfo";
import PlanValidators from "./PlanValidators";
import _Plan_stuff_TypeInfo from "./_Plan_stuff_TypeInfo";

export class PlanTypeInfo extends _Plan_stuff_TypeInfo {

    readonly validators = new PlanValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.Plan"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Plan();
    }

    override preamble() {
        super.preamble();
        this.declare({
            tags: property({ type: SET(PlanTag_TYPE), validator: this.validators.validateTags }),
        });
    }

    static readonly INSTANCE = new PlanTypeInfo();

}

export default PlanTypeInfo;

export const Plan_TYPE = PlanTypeInfo.INSTANCE;
