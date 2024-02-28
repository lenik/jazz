import { DATE, INET_ADDRESS, INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import User from "./User";
import _UserRun_stuff_Validators from "./_UserRun_stuff_Validators";

export class _UserRun_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "user_run";

    get name() { return "net.bodz.lily.schema.account.UserRun"; }
    get icon() { return "fa-tag"; }
    get description() { return "User Activity Log"; }

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
        score: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateScore }),
        lastLoginTime: property({ type: DATE, precision: 35, scale: 6, 
            description: "Last time of login", 
            validator: this.validators.validateLastLoginTime }),
        lastLoginIP: property({ type: INET_ADDRESS, 
            description: "The source IP of last login", 
            validator: this.validators.validateLastLoginIP }),

        user: property({ type: User.TYPE, nullable: false, 
            description: "The user", 
            validator: this.validators.validateUser }),
        userId: primaryKey({ type: INT, nullable: false, precision: 10, 
            description: "The user" }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _UserRun_stuff_TypeInfo;
