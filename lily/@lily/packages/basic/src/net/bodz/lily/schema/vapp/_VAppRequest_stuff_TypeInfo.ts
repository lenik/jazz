import { Moment } from "moment";

import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityTypeInfo from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import User from "../account/User";
import _VAppRequest_stuff_Validators from "./_VAppRequest_stuff_Validators";

export class _VAppRequest_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "vappreq";

    name = "net.bodz.lily.schema.vapp.VAppRequest"
    icon = "fa-tag"

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

    static validators = new _VAppRequest_stuff_Validators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        beginTime: property({ type: "Moment", precision: 35, scale: 6, validator: this.validators.validateBeginTime }),
        endTime: property({ type: "Moment", precision: 35, scale: 6, validator: this.validators.validateEndTime }),
        year: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateYear }),
        subject: property({ type: "string", nullable: false, precision: 200, validator: this.validators.validateSubject }),
        rawText: property({ type: "string", validator: this.validators.validateRawText }),
        formArguments: property({ type: "string", validator: this.validators.validateFormArguments }),
        dummy: property({ type: "integer", precision: 10, validator: this.validators.validateDummy }),

        op: property({ type: net.bodz.lily.schema.account.UserTypeInfo, inheritsDoc: true, 
            description: "User Account", 
            validator: this.validators.validateOp }),
        opId: property({ type: "integer", precision: 10 }),

        form: property({ type: net.bodz.lily.schema.meta.FormDefTypeInfo, validator: this.validators.validateForm }),
        formId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_VAppRequest_stuff_TypeInfo.declaredProperty);
    }

}

export default _VAppRequest_stuff_TypeInfo;
