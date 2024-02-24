import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import UserTypeInfo from "./UserTypeInfo";
import _UserRun_stuff_Validators from "./_UserRun_stuff_Validators";

export class _UserRun_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "user_run";

    name = "net.bodz.lily.schema.account.UserRun"
    icon = "fa-tag"
    description = "User Activity Log"

    static FIELD_USER_ID = "user";
    static FIELD_SCORE = "score";
    static FIELD_LAST_LOGIN_TIME = "lastlog";
    static FIELD_LAST_LOGIN_I_P = "lastlogip";

    static N_USER_ID = 10;
    static N_SCORE = 10;
    static N_LAST_LOGIN_TIME = 35;
    static N_LAST_LOGIN_I_P = 2147483647;

    validators = new _UserRun_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        score: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateScore }),
        lastLoginTime: property({ type: "Date", precision: 35, scale: 6, 
            description: "Last time of login", 
            validator: this.validators.validateLastLoginTime }),
        lastLoginIP: property({ type: "string", 
            description: "The source IP of last login", 
            validator: this.validators.validateLastLoginIP }),

        user: property({ type: UserTypeInfo, nullable: false, 
            description: "The user", 
            validator: this.validators.validateUser }),
        userId: primaryKey({ type: "integer", nullable: false, precision: 10, 
            description: "The user" }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _UserRun_stuff_TypeInfo;
