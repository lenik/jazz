import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import PhaseDefValidators from "./PhaseDefValidators";
import { SchemaDef } from "./SchemaDef";

export class _PhaseDef_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_phase";

    name = "net.bodz.lily.schema.meta.PhaseDef"
    icon = "fa-tag"
    label = "Phase"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_SCHEMA_ID = "schema";

    static N_ID = 10;
    static N_CODE = 30;
    static N_SCHEMA_ID = 10;

    static validators = new PhaseDefValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),

        schema: property({ type: SchemaDef.TYPE, nullable: false, validator: this.validators.validateSchema }),
        schemaId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_PhaseDef_stuff_Type.declaredProperty);
    }

}

export default _PhaseDef_stuff_Type;
