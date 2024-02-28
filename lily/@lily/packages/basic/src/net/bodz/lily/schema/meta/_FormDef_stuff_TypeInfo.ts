import type { int } from "@skeljs/core/src/lang/basetype";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import SchemaDefTypeInfo from "./SchemaDefTypeInfo";
import _FormDef_stuff_Validators from "./_FormDef_stuff_Validators";

export class _FormDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_form";

    get name() { return "net.bodz.lily.schema.meta.FormDef"; }
    get icon() { return "fa-tag"; }

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

    validators = new _FormDef_stuff_Validators(this);

    declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "int", nullable: false, precision: 10, validator: this.validators.validateId }),
        code: property({ type: "string", precision: 30, validator: this.validators.validateCode }),
        subject: property({ type: "string", precision: 200, validator: this.validators.validateSubject }),
        rawText: property({ type: "string", validator: this.validators.validateRawText }),

        schema: property({ type: SchemaDefTypeInfo, nullable: false, validator: this.validators.validateSchema }),
        schemaId: property({ type: "int", nullable: false, precision: 10 }),
    }

    constructor() {
        super();
        this.declare(this.declaredProperty);
    }

}

export default _FormDef_stuff_TypeInfo;
