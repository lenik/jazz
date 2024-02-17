
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _SchemaDef_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "_schema";

    name = "net.bodz.lily.schema.meta.SchemaDef"
    icon = "fa-tag"
    label = "Schema"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_DUMMY = "dummy";

    static const N_ID = 10;
    static const N_CODE = 30;
    static const N_DUMMY = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 30, validator: validators.validate_code }),
        dummy: property({ type: "integer", precision: 10, validator: validators.validate_dummy }),
    }

    constructor() {
        super();
        this.declare(_SchemaDef_stuff_Type.declaredProperty);
    }

}
