import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import { SchemaDef } from "./SchemaDef";
import TagGroupDefValidators from "./TagGroupDefValidators";

export class _TagGroupDef_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_tagv";

    name = "net.bodz.lily.schema.meta.TagGroupDef"
    icon = "fa-tag"
    label = "Tag Group"

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_SCHEMA_ID = "schema";
    static FIELD_FOR_TOPIC = "topic";
    static FIELD_FOR_REPLY = "reply";

    static N_ID = 10;
    static N_CODE = 30;
    static N_SCHEMA_ID = 10;
    static N_FOR_TOPIC = 1;
    static N_FOR_REPLY = 1;

    static validators = new TagGroupDefValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        forTopic: property({ type: "boolean", nullable: false, precision: 1, validator: this.validators.validateForTopic }),
        forReply: property({ type: "boolean", nullable: false, precision: 1, validator: this.validators.validateForReply }),

        schema: property({ type: SchemaDef.TYPE, nullable: false, validator: this.validators.validateSchema }),
        schemaId: property({ type: "integer", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(_TagGroupDef_stuff_Type.declaredProperty);
    }

}

export default _TagGroupDef_stuff_Type;
