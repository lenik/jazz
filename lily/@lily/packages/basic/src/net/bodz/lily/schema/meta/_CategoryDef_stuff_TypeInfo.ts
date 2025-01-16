import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";

import CoCategoryTypeInfo from "../../concrete/CoCategoryTypeInfo";
import { SchemaDef_TYPE } from "./SchemaDefTypeInfo";
import _CategoryDef_stuff_Validators from "./_CategoryDef_stuff_Validators";

export class _CategoryDef_stuff_TypeInfo extends CoCategoryTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_cat";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_SCHEMA_ID = "schema";

    static readonly N_CODE = 30;
    static readonly N_SCHEMA_ID = 10;

    readonly validators = new _CategoryDef_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.meta.CategoryDef"; }
    get icon() { return "fa-tag"; }
    get description() { return ""; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),

            schema: property({ type: SchemaDef_TYPE, nullable: false, validator: this.validators.validateSchema }),
            schemaId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

}

export default _CategoryDef_stuff_TypeInfo;

export const _CategoryDef_stuff_TYPE = _CategoryDef_stuff_TypeInfo.INSTANCE;
