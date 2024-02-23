import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import FormDefValidators from "./FormDefValidators";
import { SchemaDef } from "./SchemaDef";

export class _FormDef_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_form";

    name = "net.bodz.lily.schema.meta.FormDef"
    icon = "fa-tag"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_SCHEMA_ID = "schema";
    static FIELD_SUBJECT = "subject";
    static FIELD_RAW_TEXT = "text";

    static N_ID = 10;
    static N_CODE = 30;
    static N_SCHEMA_ID = 10;
    static N_SUBJECT = 200;
    static N_RAW_TEXT = 2147483647;

    static validators = new FormDefValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        subject: property({ type: "string", precision: 200, validator: this.validators.validateSubject }),
        rawText: property({ type: "string", validator: this.validators.validateRawText }),

        schema: property({ type: SchemaDef.TYPE, nullable: false, validator: this.validators.validateSchema }),
        schemaId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_FormDef_stuff_Type.declaredProperty);
    }

}

export default _FormDef_stuff_Type;
