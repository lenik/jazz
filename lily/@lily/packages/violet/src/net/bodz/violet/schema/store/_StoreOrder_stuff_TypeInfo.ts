import { BIG_DECIMAL, INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import OrgUnit from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnit";
import Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";
import Person from "@lily/basic/src/net/bodz/lily/schema/contact/Person";
import FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import Plan from "../plan/Plan";
import StoreCategory from "./StoreCategory";
import StoreOrder from "./StoreOrder";
import StorePhase from "./StorePhase";
import _StoreOrder_stuff_Validators from "./_StoreOrder_stuff_Validators";

export class _StoreOrder_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "storeodr";

    static readonly FIELD_ID = "id";
    static readonly FIELD_BEGIN_TIME = "t0";
    static readonly FIELD_END_TIME = "t1";
    static readonly FIELD_YEAR = "year";
    static readonly FIELD_SUBJECT = "subject";
    static readonly FIELD_OP_ID = "op";
    static readonly FIELD_RAW_TEXT = "text";
    static readonly FIELD_FORM_ID = "form";
    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_PHASE_ID = "phase";
    static readonly FIELD_PREV_ID = "prev";
    static readonly FIELD_PLAN_ID = "plan";
    static readonly FIELD_ORG_ID = "org";
    static readonly FIELD_ORG_UNIT_ID = "ou";
    static readonly FIELD_PERSON_ID = "person";
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
    static readonly N_CATEGORY_ID = 10;
    static readonly N_PHASE_ID = 10;
    static readonly N_PREV_ID = 19;
    static readonly N_PLAN_ID = 19;
    static readonly N_ORG_ID = 10;
    static readonly N_ORG_UNIT_ID = 10;
    static readonly N_PERSON_ID = 10;
    static readonly N_LENGTH = 10;
    static readonly N_TOTAL_QUANTITY = 20;
    static readonly N_TOTAL_AMOUNT = 20;

    readonly validators = new _StoreOrder_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.store.StoreOrder"; }
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
            length: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateLength }),
            totalQuantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalQuantity }),
            totalAmount: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateTotalAmount }),

            person: property({ type: Person.TYPE, validator: this.validators.validatePerson }),
            personId: property({ type: INT, precision: 10 }),

            plan: property({ type: Plan.TYPE, validator: this.validators.validatePlan }),
            planId: property({ type: LONG, precision: 19 }),

            phase: property({ type: StorePhase.TYPE, nullable: false, validator: this.validators.validatePhase }),
            phaseId: property({ type: INT, nullable: false, precision: 10 }),

            category: property({ type: StoreCategory.TYPE, nullable: false, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, nullable: false, precision: 10 }),

            prev: property({ type: this, validator: this.validators.validatePrev }),
            prevId: property({ type: LONG, precision: 19 }),

            op: property({ type: User.TYPE, inheritsDoc: true, 
                description: "User Account", 
                validator: this.validators.validateOp }),
            opId: property({ type: INT, precision: 10 }),

            form: property({ type: FormDef.TYPE, validator: this.validators.validateForm }),
            formId: property({ type: INT, precision: 10 }),

            org: property({ type: Organization.TYPE, validator: this.validators.validateOrg }),
            orgId: property({ type: INT, precision: 10 }),

            orgUnit: property({ type: OrgUnit.TYPE, validator: this.validators.validateOrgUnit }),
            orgUnitId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _StoreOrder_stuff_TypeInfo();

}

export default _StoreOrder_stuff_TypeInfo;
