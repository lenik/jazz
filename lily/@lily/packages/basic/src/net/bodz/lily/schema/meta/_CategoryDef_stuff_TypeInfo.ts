import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import CategoryDefTypeInfo from "./CategoryDefTypeInfo";
import SchemaDefTypeInfo from "./SchemaDefTypeInfo";
import _CategoryDef_stuff_Validators from "./_CategoryDef_stuff_Validators";

export class _CategoryDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_cat";

    name = "net.bodz.lily.schema.meta.CategoryDef"
    icon = "fa-tag"
    label = "Category"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_SCHEMA_ID = "schema";
    static FIELD_PARENT_ID = "parent";
    static FIELD_DEPTH = "depth";
    static FIELD_REF_COUNT = "nobj";

    static N_ID = 10;
    static N_CODE = 30;
    static N_SCHEMA_ID = 10;
    static N_PARENT_ID = 10;
    static N_DEPTH = 10;
    static N_REF_COUNT = 10;

    validators = new _CategoryDef_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        depth: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateDepth }),
        refCount: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateRefCount }),

        schema: property({ type: SchemaDefTypeInfo, nullable: false, validator: this.validators.validateSchema }),
        schemaId: property({ type: "integer", nullable: false, precision: 10 }),

        parent: property({ type: CategoryDefTypeInfo, validator: this.validators.validateParent }),
        parentId: property({ type: "integer", precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _CategoryDef_stuff_TypeInfo;
