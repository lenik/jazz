import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import SchemaDefTypeInfo from "./SchemaDefTypeInfo";
import _PhaseDef_stuff_Validators from "./_PhaseDef_stuff_Validators";

export class _PhaseDef_stuff_TypeInfo extends CoEntityTypeInfo {

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

    validators = new _PhaseDef_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),

        schema: property({ type: SchemaDefTypeInfo, nullable: false, validator: this.validators.validateSchema }),
        schemaId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _PhaseDef_stuff_TypeInfo;
