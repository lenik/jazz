import { ARRAY, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";

import { PlanDo_TYPE } from "./PlanDoTypeInfo";
import { Plan_TYPE } from "./PlanTypeInfo";
import _PlanDo_stuff_Validators from "./_PlanDo_stuff_Validators";

export class _PlanDo_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plando";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_VOTE_COUNT = "nvote";
    static readonly FIELD_PLAN_ID = "plan";
    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_CHANGES = "changes";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_VOTE_COUNT = 10;
    static readonly N_PLAN_ID = 19;
    static readonly N_PARENT_ID = 19;
    static readonly N_CHANGES = 2147483647;

    readonly validators = new _PlanDo_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.plan.PlanDo"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            voteCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteCount }),
            changes: property({ type: ARRAY(STRING), validator: this.validators.validateChanges }),

            plan: property({ type: Plan_TYPE, nullable: false, validator: this.validators.validatePlan }),
            planId: property({ type: LONG, nullable: false, precision: 19 }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: LONG, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _PlanDo_stuff_TypeInfo();

}

export default _PlanDo_stuff_TypeInfo;

export const _PlanDo_stuff_TYPE = _PlanDo_stuff_TypeInfo.INSTANCE;
