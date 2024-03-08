import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import SchemaDef from "./SchemaDef";
import _ParameterDef_stuff_Validators from "./_ParameterDef_stuff_Validators";

export class _ParameterDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_parm";

    get name() { return "net.bodz.lily.schema.meta.ParameterDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Parameter"; }

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_SCHEMA_ID = "schema";

    static N_ID = 10;
    static N_CODE = 30;
    static N_SCHEMA_ID = 10;

    validators = new _ParameterDef_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),

            schema: property({ type: SchemaDef.TYPE, nullable: false, validator: this.validators.validateSchema }),
            schemaId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    constructor() {
        super();
    }

}

export default _ParameterDef_stuff_TypeInfo;
