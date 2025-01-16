import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";
import { Organization_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/OrganizationTypeInfo";
import { Person_TYPE } from "lily-basic/src/net/bodz/lily/schema/contact/PersonTypeInfo";

import { Plan_TYPE } from "../plan/PlanTypeInfo";
import { SalesCategory_TYPE } from "./SalesCategoryTypeInfo";
import { SalesOrder_TYPE } from "./SalesOrderTypeInfo";
import { SalesPhase_TYPE } from "./SalesPhaseTypeInfo";
import _SalesOrder_stuff_Validators from "./_SalesOrder_stuff_Validators";

export class _SalesOrder_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "saleodr";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_PHASE_ID = "phase";
    static readonly FIELD_PREVIOUS_ORDER_ID = "prev";
    static readonly FIELD_PLAN_ID = "plan";
    static readonly FIELD_CUSTOMER_ORG_ID = "customer_org";
    static readonly FIELD_CUSTOMER_ID = "customer";
    static readonly FIELD_LENGTH = "length";
    static readonly FIELD_TOTAL_QUANTITY = "sum_qty";
    static readonly FIELD_TOTAL_AMOUNT = "sum_amount";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_PHASE_ID = 10;
    static readonly N_PREVIOUS_ORDER_ID = 19;
    static readonly N_PLAN_ID = 19;
    static readonly N_CUSTOMER_ORG_ID = 10;
    static readonly N_CUSTOMER_ID = 10;
    static readonly N_LENGTH = 10;
    static readonly N_TOTAL_QUANTITY = 20;
    static readonly N_TOTAL_AMOUNT = 20;

    readonly validators = new _SalesOrder_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.shop.SalesOrder"; }
    get icon() { return "fa-tag"; }
    get description() { return "订单"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            length: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateLength }),
            totalQuantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalQuantity }),
            totalAmount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalAmount }),

            customer: property({ type: Person_TYPE, validator: this.validators.validateCustomer }),
            customerId: property({ type: INT, precision: 10 }),

            phase: property({ type: SalesPhase_TYPE, validator: this.validators.validatePhase }),
            phaseId: property({ type: INT, precision: 10 }),

            customerOrg: property({ type: Organization_TYPE, validator: this.validators.validateCustomerOrg }),
            customerOrgId: property({ type: INT, precision: 10 }),

            previousOrder: property({ type: this, validator: this.validators.validatePreviousOrder }),
            previousOrderId: property({ type: LONG, precision: 19 }),

            plan: property({ type: Plan_TYPE, validator: this.validators.validatePlan }),
            planId: property({ type: LONG, precision: 19 }),

            category: property({ type: SalesCategory_TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _SalesOrder_stuff_TypeInfo();

}

export default _SalesOrder_stuff_TypeInfo;

export const _SalesOrder_stuff_TYPE = _SalesOrder_stuff_TypeInfo.INSTANCE;
