
import type { CoEntityType } from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";
import type { EntityPropertyMap } from "@skeljs/dba/src/net/bodz/lily/entity";

import type { Integer } from "../../../../../java/lang/Integer";
import { * as validators } from "./PersonValidators";

// Type Info

export class _TagGroupDef_stuff_Type extends CoEntityType {

    static const SCHEMA_NAME = "lily";
    static const TABLE_NAME = "_tagv";

    name = "net.bodz.lily.schema.meta.TagGroupDef"
    icon = "fa-tag"
    label = "Tag Group"

    static const FIELD_ID = "id";
    static const FIELD_CODE = "code";
    static const FIELD_SCHEMA_ID = "schema";
    static const FIELD_FOR_TOPIC = "topic";
    static const FIELD_FOR_REPLY = "reply";

    static const N_ID = 10;
    static const N_CODE = 30;
    static const N_SCHEMA_ID = 10;
    static const N_FOR_TOPIC = 1;
    static const N_FOR_REPLY = 1;

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: validators.validate_id }),
        code: property({ type: "string", precision: 30, validator: validators.validate_code }),
        forTopic: property({ type: "boolean", nullable: false, precision: 1, validator: validators.validate_forTopic }),
        forReply: property({ type: "boolean", nullable: false, precision: 1, validator: validators.validate_forReply }),

        schema: property({ type: "net.bodz.lily.schema.meta.SchemaDef", nullable: false, validator: validators.validate_schema }),
        schemaId: property({ type: "int", nullable: false, precision: 10, validator: validators.validate_schemaId }),
    }

    constructor() {
        super();
        this.declare(_TagGroupDef_stuff_Type.declaredProperty);
    }

}
