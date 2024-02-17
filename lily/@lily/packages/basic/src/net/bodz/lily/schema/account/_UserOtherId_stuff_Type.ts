
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _UserOtherId_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "useroid";

    name = "net.bodz.lily.schema.account.UserOtherId"
    icon = "fa-tag"
    description = "User Open ID"

    static const FIELD_ID = "id";
    static const FIELD_BEGIN_TIME = "t0";
    static const FIELD_END_TIME = "t1";
    static const FIELD_YEAR = "year";
    static const FIELD_USER_ID = "user";
    static const FIELD_TYPE_ID = "type";
    static const FIELD_OTHER_ID = "oid";
    static const FIELD_AUTH = "auth";

    static const N_ID = 10;
    static const N_BEGIN_TIME = 35;
    static const N_END_TIME = 35;
    static const N_YEAR = 10;
    static const N_USER_ID = 10;
    static const N_TYPE_ID = 10;
    static const N_OTHER_ID = 100;
    static const N_AUTH = 2147483647;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        beginTime: property({ type: "Moment", precision: 35, scale: 6, validator: validators.validate_beginTime }),
        endTime: property({ type: "Moment", precision: 35, scale: 6, validator: validators.validate_endTime }),
        year: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_year }),
        otherId: property({ type: "string", nullable: false, precision: 100, 
            description: "The identity data", 
            validator: validators.validate_otherId }),
        auth: property({ type: "java.lang.Object", 
            description: "The authentication data", 
            validator: validators.validate_auth }),

        type: property({ type: "net.bodz.lily.schema.account.UserOtherIdType", nullable: false, 
            description: "Type of Open ID", 
            validator: validators.validate_type }),
        typeId: property({ type: "int", nullable: false, precision: 10, 
            description: "Type of Open ID", 
            validator: validators.validate_typeId }),

        user: property({ type: "net.bodz.lily.schema.account.User", nullable: false, 
            description: "The declaring user", 
            validator: validators.validate_user }),
        userId: property({ type: "int", nullable: false, precision: 10, 
            description: "The declaring user", 
            validator: validators.validate_userId }),
    }

    constructor() {
        super();
        this.declare(_UserOtherId_stuff_Type.declaredProperty);
    }

}
