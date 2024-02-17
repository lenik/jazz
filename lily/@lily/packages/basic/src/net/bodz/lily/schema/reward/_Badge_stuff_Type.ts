
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _Badge_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "badge";

    name = "net.bodz.lily.schema.reward.Badge"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_EXPR = "expr";
    static const FIELD_VAL = "val";
    static const FIELD_LEVELS = "levels";
    static const FIELD_DESCEND = "descend";
    static const FIELD_TRANSIENT = "transient";
    static const FIELD_INDEXED = "indexed";

    static const N_ID = 10;
    static const N_EXPR = 255;
    static const N_VAL = 10;
    static const N_LEVELS = 10;
    static const N_DESCEND = 1;
    static const N_TRANSIENT = 1;
    static const N_INDEXED = 1;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        expr: property({ type: "string", precision: 255, validator: validators.validate_expr }),
        val: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_val }),
        levels: property({ type: "int[]", precision: 10, validator: validators.validate_levels }),
        descend: property({ type: "boolean", nullable: false, precision: 1, validator: validators.validate_descend }),
        transient_: property({ type: "boolean", nullable: false, precision: 1, validator: validators.validate_transient_ }),
        indexed: property({ type: "boolean", nullable: false, precision: 1, validator: validators.validate_indexed }),
    }

    constructor() {
        super();
        this.declare(_Badge_stuff_Type.declaredProperty);
    }

}
