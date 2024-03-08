import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import CategoryDef from "./CategoryDef";
import SchemaDef from "./SchemaDef";
import _CategoryDef_stuff_Validators from "./_CategoryDef_stuff_Validators";

export class _CategoryDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_cat";

    get name() { return "net.bodz.lily.schema.meta.CategoryDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Category"; }

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

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
            depth: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDepth }),
            refCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateRefCount }),

            schema: property({ type: SchemaDef.TYPE, nullable: false, validator: this.validators.validateSchema }),
            schemaId: property({ type: INT, nullable: false, precision: 10 }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: INT, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _CategoryDef_stuff_TypeInfo;
