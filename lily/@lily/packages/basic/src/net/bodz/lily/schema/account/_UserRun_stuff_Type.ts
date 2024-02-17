
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _UserRun_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "user_run";

    name = "net.bodz.lily.schema.account.UserRun"
    icon = "fa-tag"
    description = "User Activity Log"

    static const FIELD_USER_ID = "user";
    static const FIELD_SCORE = "score";
    static const FIELD_LAST_LOGIN_TIME = "lastlog";
    static const FIELD_LAST_LOGIN_I_P = "lastlogip";

    static const N_USER_ID = 10;
    static const N_SCORE = 10;
    static const N_LAST_LOGIN_TIME = 35;
    static const N_LAST_LOGIN_I_P = 2147483647;

    static declaredProperty: EntityPropertyMap = {
        score: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_score }),
        lastLoginTime: property({ type: "Date", precision: 35, scale: 6, 
            description: "Last time of login", 
            validator: validators.validate_lastLoginTime }),
        lastLoginIP: property({ type: "java.lang.Object", 
            description: "The source IP of last login", 
            validator: validators.validate_lastLoginIP }),

        user: property({ type: "net.bodz.lily.schema.account.User", nullable: false, 
            description: "The user", 
            validator: validators.validate_user }),
        userId: primaryKey({ type: "int", nullable: false, precision: 10, 
            description: "The user", 
            validator: validators.validate_userId }),
    }

    constructor() {
        super();
        this.declare(_UserRun_stuff_Type.declaredProperty);
    }

}
