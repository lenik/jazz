import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import EntityPropertyMap from "@skeljs/dba/src/net/bodz/lily/entity/EntityPropertyMap";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import SchemaDef from "./SchemaDef";
import _PhaseDef_stuff_Validators from "./_PhaseDef_stuff_Validators";

export class _PhaseDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_phase";

    get name() { return "net.bodz.lily.schema.meta.PhaseDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Phase"; }

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_SCHEMA_ID = "schema";

    static N_ID = 10;
    static N_CODE = 30;
    static N_SCHEMA_ID = 10;

    validators = new _PhaseDef_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),

        schema: property({ type: SchemaDef.TYPE, nullable: false, validator: this.validators.validateSchema }),
        schemaId: property({ type: INT, nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PhaseDef_stuff_TypeInfo;
