
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _FormDef_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "_form";

    name = "net.bodz.lily.schema.meta.FormDef"
    icon = "fa-tag"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_SCHEMA_ID = "schema";
    static const FIELD_SUBJECT = "subject";
    static const FIELD_RAW_TEXT = "text";

    static const N_ID = 10;
    static const N_CODE = 30;
    static const N_SCHEMA_ID = 10;
    static const N_SUBJECT = 200;
    static const N_RAW_TEXT = 2147483647;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 30, validator: validators.validate_code }),
        subject: property({ type: "string", precision: 200, validator: validators.validate_subject }),
        rawText: property({ type: "string", validator: validators.validate_rawText }),

        schema: property({ type: "net.bodz.lily.schema.meta.SchemaDef", nullable: false, validator: validators.validate_schema }),
        schemaId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_schemaId }),
    }

    constructor() {
        super();
        this.declare(_FormDef_stuff_Type.declaredProperty);
    }

}
