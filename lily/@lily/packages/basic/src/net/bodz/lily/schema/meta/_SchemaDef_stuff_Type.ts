import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import SchemaDefValidators from "./SchemaDefValidators";

export class _SchemaDef_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_schema";

    name = "net.bodz.lily.schema.meta.SchemaDef"
    icon = "fa-tag"
    label = "Schema"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_DUMMY = "dummy";

    static N_ID = 10;
    static N_CODE = 30;
    static N_DUMMY = 10;

    static validators = new SchemaDefValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        dummy: property({ type: "integer", precision: 10, validator: this.validators.validateDummy }),
    }

    constructor() {
        super();
        this.declare(_SchemaDef_stuff_Type.declaredProperty);
    }

}

export default _SchemaDef_stuff_Type;
