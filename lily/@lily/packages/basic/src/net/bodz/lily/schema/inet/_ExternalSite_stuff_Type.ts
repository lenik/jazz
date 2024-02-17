
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _ExternalSite_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "extsite";

    name = "net.bodz.lily.schema.inet.ExternalSite"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_PARENT_ID = "parent";
    static const FIELD_DEPTH = "depth";
    static const FIELD_URLFMT = "urlfmt";
    static const FIELD_BONUS = "bonus";
    static const FIELD_COUNT = "count";

    static const N_ID = 10;
    static const N_PARENT_ID = 10;
    static const N_DEPTH = 10;
    static const N_URLFMT = 200;
    static const N_BONUS = 10;
    static const N_COUNT = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        depth: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_depth }),
        urlfmt: property({ type: "string", precision: 200, validator: validators.validate_urlfmt }),
        bonus: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_bonus }),
        count: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_count }),

        parent: property({ type: "net.bodz.lily.schema.inet.ExternalSite", validator: validators.validate_parent }),
        parentId: property({ type: "integer", precision: 10, validator: validators.validate_parentId }),
    }

    constructor() {
        super();
        this.declare(_ExternalSite_stuff_Type.declaredProperty);
    }

}
