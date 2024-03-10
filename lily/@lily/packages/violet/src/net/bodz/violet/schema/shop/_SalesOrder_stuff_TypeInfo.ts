import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";
import FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import Plan from "../plan/Plan";
import SalesCategory from "./SalesCategory";
import SalesOrder from "./SalesOrder";
import SalesPhase from "./SalesPhase";
import _SalesOrder_stuff_Validators from "./_SalesOrder_stuff_Validators";

export class _SalesOrder_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "saleodr";

    static readonly FIELD_ID = "id";
    static readonly FIELD_BEGIN_TIME = "t0";
    static readonly FIELD_END_TIME = "t1";
    static readonly FIELD_YEAR = "year";
    static readonly FIELD_SUBJECT = "subject";
    static readonly FIELD_OP_ID = "op";
    static readonly FIELD_RAW_TEXT = "text";
    static readonly FIELD_FORM_ID = "form";
    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_PHASE_ID = "phase";
    static readonly FIELD_PREVIOUS_ORDER_ID = "prev";
    static readonly FIELD_PLAN_ID = "plan";
    static readonly FIELD_CUSTOMER_ORG_ID = "customer_org";
    static readonly FIELD_CUSTOMER_ID = "customer";
    static readonly FIELD_LENGTH = "length";
    static readonly FIELD_TOTAL_QUANTITY = "sum_qty";
    static readonly FIELD_TOTAL_AMOUNT = "sum_amount";

    static readonly N_ID = 19;
    static readonly N_BEGIN_TIME = 35;
    static readonly N_END_TIME = 35;
    static readonly N_YEAR = 10;
    static readonly N_SUBJECT = 200;
    static readonly N_OP_ID = 10;
    static readonly N_RAW_TEXT = 2147483647;
    static readonly N_FORM_ID = 10;
    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_PROPERTIES = 2147483647;
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
        super();
    }

    get name() { return "net.bodz.violet.schema.shop.SalesOrder"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: LONG, nullable: false, precision: 19, validator: this.validators.validateId }),
            beginTime: property({ type: ZonedDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateBeginTime }),
            endTime: property({ type: ZonedDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateEndTime }),
            year: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateYear }),
            subject: property({ type: STRING, nullable: false, precision: 200, validator: this.validators.validateSubject }),
            rawText: property({ type: STRING, validator: this.validators.validateRawText }),
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            length: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateLength }),
            totalQuantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalQuantity }),
            totalAmount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalAmount }),

            customer: property({ type: Person.TYPE, validator: this.validators.validateCustomer }),
            customerId: property({ type: INT, precision: 10 }),

            form: property({ type: FormDef.TYPE, validator: this.validators.validateForm }),
            formId: property({ type: INT, precision: 10 }),

            phase: property({ type: SalesPhase.TYPE, validator: this.validators.validatePhase }),
            phaseId: property({ type: INT, precision: 10 }),

            customerOrg: property({ type: Organization.TYPE, validator: this.validators.validateCustomerOrg }),
            customerOrgId: property({ type: INT, precision: 10 }),

            previousOrder: property({ type: this, validator: this.validators.validatePreviousOrder }),
            previousOrderId: property({ type: LONG, precision: 19 }),

            op: property({ type: User.TYPE, inheritsDoc: true, 
                description: "User Account", 
                validator: this.validators.validateOp }),
            opId: property({ type: INT, precision: 10 }),

            plan: property({ type: Plan.TYPE, validator: this.validators.validatePlan }),
            planId: property({ type: LONG, precision: 19 }),

            category: property({ type: SalesCategory.TYPE, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _SalesOrder_stuff_TypeInfo();

}

export default _SalesOrder_stuff_TypeInfo;
