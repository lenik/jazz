
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _Zone_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "zone";

    name = "net.bodz.lily.schema.geo.Zone"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_CATEGORY_ID = "cat";
    static const FIELD_COUNTRY = "country";
    static const FIELD_PARENT_ID = "parent";
    static const FIELD_DEPTH = "depth";
    static const FIELD_TEL_CODE = "telcode";
    static const FIELD_POST_CODE = "postcode";
    static const FIELD_DATA = "data";

    static const N_ID = 10;
    static const N_CODE = 10;
    static const N_CATEGORY_ID = 10;
    static const N_COUNTRY = 2;
    static const N_PARENT_ID = 10;
    static const N_DEPTH = 10;
    static const N_TEL_CODE = 10;
    static const N_POST_CODE = 10;
    static const N_DATA = 2147483647;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 10, validator: validators.validate_code }),
        country: property({ type: "string", precision: 2, validator: validators.validate_country }),
        depth: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_depth }),
        telCode: property({ type: "string", precision: 10, validator: validators.validate_telCode }),
        postCode: property({ type: "string", precision: 10, validator: validators.validate_postCode }),
        data: property({ type: "java.lang.Object", validator: validators.validate_data }),

        parent: property({ type: "net.bodz.lily.schema.geo.Zone", validator: validators.validate_parent }),
        parentId: property({ type: "integer", precision: 10, validator: validators.validate_parentId }),

        category: property({ type: "net.bodz.lily.schema.geo.ZoneCategory", nullable: false, validator: validators.validate_category }),
        categoryId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_categoryId }),
    }

    constructor() {
        super();
        this.declare(_Zone_stuff_Type.declaredProperty);
    }

}
