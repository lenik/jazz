
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _Storage_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "storage";

    name = "net.bodz.lily.schema.io.Storage"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_NAME = "name";

    static const N_ID = 10;
    static const N_NAME = 30;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        name: property({ type: "string", nullable: false, precision: 30, validator: validators.validate_name }),
    }

    constructor() {
        super();
        this.declare(_Storage_stuff_Type.declaredProperty);
    }

}
