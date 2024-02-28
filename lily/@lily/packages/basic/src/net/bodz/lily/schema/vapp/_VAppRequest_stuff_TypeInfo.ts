import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import type User from "../account/User";
import FormDef from "../meta/FormDef";
import _VAppRequest_stuff_Validators from "./_VAppRequest_stuff_Validators";

export class _VAppRequest_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vappreq";

    get name() { return "net.bodz.lily.schema.vapp.VAppRequest"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_BEGIN_TIME = "t0";
    static FIELD_END_TIME = "t1";
    static FIELD_YEAR = "year";
    static FIELD_SUBJECT = "subject";
    static FIELD_OP_ID = "op";
    static FIELD_RAW_TEXT = "text";
    static FIELD_FORM_ID = "form";
    static FIELD_FORM_ARGUMENTS = "formargs";
    static FIELD_DUMMY = "dummy";

    static N_ID = 10;
    static N_CODE = 30;
    static N_BEGIN_TIME = 35;
    static N_END_TIME = 35;
    static N_YEAR = 10;
    static N_SUBJECT = 200;
    static N_OP_ID = 10;
    static N_RAW_TEXT = 2147483647;
    static N_FORM_ID = 10;
    static N_FORM_ARGUMENTS = 2147483647;
    static N_DUMMY = 10;

    validators = new _VAppRequest_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
        beginTime: property({ type: ZonedDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateBeginTime }),
        endTime: property({ type: ZonedDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateEndTime }),
        year: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateYear }),
        subject: property({ type: STRING, nullable: false, precision: 200, validator: this.validators.validateSubject }),
        rawText: property({ type: STRING, validator: this.validators.validateRawText }),
        formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
        dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),

        op: property({ type: User.TYPE, inheritsDoc: true, 
            description: "User Account", 
            validator: this.validators.validateOp }),
        opId: property({ type: INT, precision: 10 }),

        form: property({ type: FormDef.TYPE, validator: this.validators.validateForm }),
        formId: property({ type: INT, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _VAppRequest_stuff_TypeInfo;
