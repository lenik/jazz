
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _CategoryDef_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "_cat";

    name = "net.bodz.lily.schema.meta.CategoryDef"
    icon = "fa-tag"
    label = "Category"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_SCHEMA_ID = "schema";
    static const FIELD_PARENT_ID = "parent";
    static const FIELD_DEPTH = "depth";
    static const FIELD_REF_COUNT = "nobj";

    static const N_ID = 10;
    static const N_CODE = 30;
    static const N_SCHEMA_ID = 10;
    static const N_PARENT_ID = 10;
    static const N_DEPTH = 10;
    static const N_REF_COUNT = 10;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 30, validator: validators.validate_code }),
        depth: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_depth }),
        refCount: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_refCount }),

        schema: property({ type: "net.bodz.lily.schema.meta.SchemaDef", nullable: false, validator: validators.validate_schema }),
        schemaId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_schemaId }),

        parent: property({ type: "net.bodz.lily.schema.meta.CategoryDef", validator: validators.validate_parent }),
        parentId: property({ type: "integer", precision: 10, validator: validators.validate_parentId }),
    }

    constructor() {
        super();
        this.declare(_CategoryDef_stuff_Type.declaredProperty);
    }

}
