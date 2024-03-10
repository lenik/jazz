import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import SchemaDef from "./SchemaDef";
import _PhaseDef_stuff_Validators from "./_PhaseDef_stuff_Validators";

export class _PhaseDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_phase";

    static readonly FIELD_ID = "id";
    static readonly FIELD_CODE = "code";
    static readonly FIELD_SCHEMA_ID = "schema";

    static readonly N_ID = 10;
    static readonly N_CODE = 30;
    static readonly N_SCHEMA_ID = 10;

    readonly validators = new _PhaseDef_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.meta.PhaseDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Phase"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),

            schema: property({ type: SchemaDef.TYPE, nullable: false, validator: this.validators.validateSchema }),
            schemaId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _PhaseDef_stuff_TypeInfo();

}

export default _PhaseDef_stuff_TypeInfo;
