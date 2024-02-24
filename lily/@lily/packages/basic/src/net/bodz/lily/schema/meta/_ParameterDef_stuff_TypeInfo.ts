import type { integer } from "@skeljs/core/src/lang/type";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import SchemaDefTypeInfo from "./SchemaDefTypeInfo";
import _ParameterDef_stuff_Validators from "./_ParameterDef_stuff_Validators";

export class _ParameterDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_parm";

    name = "net.bodz.lily.schema.meta.ParameterDef"
    icon = "fa-tag"
    label = "Parameter"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_SCHEMA_ID = "schema";

    static N_ID = 10;
    static N_CODE = 30;
    static N_SCHEMA_ID = 10;

    validators = new _ParameterDef_stuff_Validators(this);

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

export default _ParameterDef_stuff_TypeInfo;
