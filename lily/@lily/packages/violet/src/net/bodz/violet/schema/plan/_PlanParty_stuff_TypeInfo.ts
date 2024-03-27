import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";
import Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";

import Plan from "./Plan";
import _PlanParty_stuff_Validators from "./_PlanParty_stuff_Validators";

export class _PlanParty_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "plan_party";

    static readonly FIELD_PLAN_ID = "plan";
    static readonly FIELD_PERSON_ID = "person";
    static readonly FIELD_ORG_ID = "org";

    static readonly N_PLAN_ID = 19;
    static readonly N_PERSON_ID = 10;
    static readonly N_ORG_ID = 10;

    readonly validators = new _PlanParty_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.plan.PlanParty"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            person: property({ type: Person.TYPE, validator: this.validators.validatePerson }),
            personId: property({ type: INT, precision: 10 }),

            plan: property({ type: Plan.TYPE, nullable: false, validator: this.validators.validatePlan }),
            planId: property({ type: LONG, nullable: false, precision: 19 }),

            org: property({ type: Organization.TYPE, validator: this.validators.validateOrg }),
            orgId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _PlanParty_stuff_TypeInfo();

}

export default _PlanParty_stuff_TypeInfo;
