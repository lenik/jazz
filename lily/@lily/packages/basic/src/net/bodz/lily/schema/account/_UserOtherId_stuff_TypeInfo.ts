import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import UserOtherIdTypeTypeInfo from "./UserOtherIdTypeTypeInfo";
import UserTypeInfo from "./UserTypeInfo";
import _UserOtherId_stuff_Validators from "./_UserOtherId_stuff_Validators";

export class _UserOtherId_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "useroid";

    name = "net.bodz.lily.schema.account.UserOtherId"
    icon = "fa-tag"
    description = "User Open ID"

    static FIELD_ID = "id";
    static FIELD_BEGIN_TIME = "t0";
    static FIELD_END_TIME = "t1";
    static FIELD_YEAR = "year";
    static FIELD_USER_ID = "user";
    static FIELD_TYPE_ID = "type";
    static FIELD_OTHER_ID = "oid";
    static FIELD_AUTH = "auth";

    static N_ID = 10;
    static N_BEGIN_TIME = 35;
    static N_END_TIME = 35;
    static N_YEAR = 10;
    static N_USER_ID = 10;
    static N_TYPE_ID = 10;
    static N_OTHER_ID = 100;
    static N_AUTH = 2147483647;

    validators = new _UserOtherId_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        beginTime: property({ type: "ZonedDateTime", precision: 35, scale: 6, validator: this.validators.validateBeginTime }),
        endTime: property({ type: "ZonedDateTime", precision: 35, scale: 6, validator: this.validators.validateEndTime }),
        year: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateYear }),
        otherId: property({ type: "string", nullable: false, precision: 100, 
            description: "The identity data", 
            validator: this.validators.validateOtherId }),
        auth: property({ type: "any", 
            description: "The authentication data", 
            validator: this.validators.validateAuth }),

        type: property({ type: UserOtherIdTypeTypeInfo, nullable: false, 
            description: "Type of Open ID", 
            validator: this.validators.validateType }),
        typeId: property({ type: "integer", nullable: false, precision: 10, 
            description: "Type of Open ID" }),

        user: property({ type: UserTypeInfo, nullable: false, 
            description: "The declaring user", 
            validator: this.validators.validateUser }),
        userId: property({ type: "integer", nullable: false, precision: 10, 
            description: "The declaring user" }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _UserOtherId_stuff_TypeInfo;
