
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _UserType_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "usertype";

    name = "net.bodz.lily.schema.account.UserType"
    icon = "fa-tag"
    description = "User Type"

    static const FIELD_ID = "id";
    static const FIELD_NAME = "name";
    static const FIELD_DUMMY = "dummy";

    static const N_ID = 10;
    static const N_NAME = 20;
    static const N_DUMMY = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        name: property({ type: "string", precision: 20, 
            description: "The user type name", 
            validator: validators.validate_name }),
        dummy: property({ type: "integer", precision: 10, validator: validators.validate_dummy }),
    }

    constructor() {
        super();
        this.declare(_UserType_stuff_Type.declaredProperty);
    }

}
