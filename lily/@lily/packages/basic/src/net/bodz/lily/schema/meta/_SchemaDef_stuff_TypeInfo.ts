import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import _SchemaDef_stuff_Validators from "./_SchemaDef_stuff_Validators";

export class _SchemaDef_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "_schema";

    get name() { return "net.bodz.lily.schema.meta.SchemaDef"; }
    get icon() { return "fa-tag"; }
    get label() { return "Schema"; }

    static FIELD_ID = "id";
    static FIELD_CODE = "code";
    static FIELD_DUMMY = "dummy";

    static N_ID = 10;
    static N_CODE = 30;
    static N_DUMMY = 10;

    validators = new _SchemaDef_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
            dummy: property({ type: INT, precision: 10, validator: this.validators.validateDummy }),
        });
    }

    constructor() {
        super();
    }

}

export default _SchemaDef_stuff_TypeInfo;
