import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";
import { Organization_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";
import { Plan_TYPE } from "lily-violet/src/net/bodz/violet/schema/plan/PlanTypeInfo";

import _FabOrder_stuff_Validators from "./_FabOrder_stuff_Validators";

export class _FabOrder_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabodr";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_PLAN_ID = "plan";
    static readonly FIELD_CUSTOM_ORG_ID = "custom_org";
    static readonly FIELD_CUSTOM_ID = "custom";
    static readonly FIELD_CLERK_ID = "clerk";
    static readonly FIELD_NITEM = "nitem";
    static readonly FIELD_TOTAL_QUANTITY = "sum_qty";
    static readonly FIELD_TOTAL_AMOUNT = "sum_amount";
    static readonly FIELD_TASK_COUNT = "ntask";
    static readonly FIELD_PROCESS_COUNT = "nproc";
    static readonly FIELD_TRACK_COUNT = "ntrack";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_PLAN_ID = 19;
    static readonly N_CUSTOM_ORG_ID = 10;
    static readonly N_CUSTOM_ID = 10;
    static readonly N_CLERK_ID = 10;
    static readonly N_NITEM = 10;
    static readonly N_TOTAL_QUANTITY = 20;
    static readonly N_TOTAL_AMOUNT = 20;
    static readonly N_TASK_COUNT = 10;
    static readonly N_PROCESS_COUNT = 10;
    static readonly N_TRACK_COUNT = 10;

    readonly validators = new _FabOrder_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.fab.FabOrder"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            nitem: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateNitem }),
            totalQuantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalQuantity }),
            totalAmount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalAmount }),
            taskCount: property({ type: INT, precision: 10, validator: this.validators.validateTaskCount }),
            processCount: property({ type: INT, precision: 10, validator: this.validators.validateProcessCount }),
            trackCount: property({ type: INT, precision: 10, validator: this.validators.validateTrackCount }),

            clerk: property({ type: Person_TYPE, validator: this.validators.validateClerk }),
            clerkId: property({ type: INT, precision: 10 }),

            plan: property({ type: Plan_TYPE, validator: this.validators.validatePlan }),
            planId: property({ type: LONG, precision: 19 }),

            customOrg: property({ type: Organization_TYPE, validator: this.validators.validateCustomOrg }),
            customOrgId: property({ type: INT, precision: 10 }),

            custom: property({ type: Person_TYPE, validator: this.validators.validateCustom }),
            customId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabOrder_stuff_TypeInfo();

}

export default _FabOrder_stuff_TypeInfo;

export const _FabOrder_stuff_TYPE = _FabOrder_stuff_TypeInfo.INSTANCE;
