
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import type { User } from "../account/User";
import { * as validators } from "./PersonValidators";

// Type Info

export class _VAppRequest_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "vappreq";

    name = "net.bodz.lily.schema.vapp.VAppRequest"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_BEGIN_TIME = "t0";
    static const FIELD_END_TIME = "t1";
    static const FIELD_YEAR = "year";
    static const FIELD_SUBJECT = "subject";
    static const FIELD_OP_ID = "op";
    static const FIELD_RAW_TEXT = "text";
    static const FIELD_FORM_ID = "form";
    static const FIELD_FORM_ARGUMENTS = "formargs";
    static const FIELD_DUMMY = "dummy";

    static const N_ID = 10;
    static const N_CODE = 30;
    static const N_BEGIN_TIME = 35;
    static const N_END_TIME = 35;
    static const N_YEAR = 10;
    static const N_SUBJECT = 200;
    static const N_OP_ID = 10;
    static const N_RAW_TEXT = 2147483647;
    static const N_FORM_ID = 10;
    static const N_FORM_ARGUMENTS = 2147483647;
    static const N_DUMMY = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 30, validator: validators.validate_code }),
        beginTime: property({ type: "Moment", precision: 35, scale: 6, validator: validators.validate_beginTime }),
        endTime: property({ type: "Moment", precision: 35, scale: 6, validator: validators.validate_endTime }),
        year: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_year }),
        subject: property({ type: "string", nullable: false, precision: 200, validator: validators.validate_subject }),
        rawText: property({ type: "string", validator: validators.validate_rawText }),
        formArguments: property({ type: "string", validator: validators.validate_formArguments }),
        dummy: property({ type: "integer", precision: 10, validator: validators.validate_dummy }),

        op: property({ type: "net.bodz.lily.schema.account.User", 
            description: "User Account", 
            validator: validators.validate_op }),
        opId: property({ type: "integer", precision: 10, validator: validators.validate_opId }),

        form: property({ type: "net.bodz.lily.schema.meta.FormDef", validator: validators.validate_form }),
        formId: property({ type: "integer", precision: 10, validator: validators.validate_formId }),
    }

    constructor() {
        super();
        this.declare(_VAppRequest_stuff_Type.declaredProperty);
    }

}
