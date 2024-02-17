
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _UserOtherIdType_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "useroidtype";

    name = "net.bodz.lily.schema.account.UserOtherIdType"
    icon = "fa-tag"
    description = "Type of Open ID"

    static const FIELD_ID = "id";
    static const FIELD_DUMMY = "dummy";

    static const N_ID = 10;
    static const N_DUMMY = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        dummy: property({ type: "integer", precision: 10, validator: validators.validate_dummy }),
    }

    constructor() {
        super();
        this.declare(_UserOtherIdType_stuff_Type.declaredProperty);
    }

}
