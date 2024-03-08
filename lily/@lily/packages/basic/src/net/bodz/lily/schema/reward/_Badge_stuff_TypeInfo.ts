import { ARRAY, BOOLEAN, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoEntityTypeInfo from "../../concrete/CoEntityTypeInfo";
import _Badge_stuff_Validators from "./_Badge_stuff_Validators";

export class _Badge_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "badge";

    get name() { return "net.bodz.lily.schema.reward.Badge"; }
    get icon() { return "fa-tag"; }

    static FIELD_ID = "id";
    static FIELD_EXPR = "expr";
    static FIELD_VAL = "val";
    static FIELD_LEVELS = "levels";
    static FIELD_DESCEND = "descend";
    static FIELD_TRANSIENT = "transient";
    static FIELD_INDEXED = "indexed";
    static FIELD_IMAGE = "image";

    static N_ID = 10;
    static N_EXPR = 255;
    static N_VAL = 10;
    static N_LEVELS = 10;
    static N_DESCEND = 1;
    static N_TRANSIENT = 1;
    static N_INDEXED = 1;
    static N_IMAGE = 30;

    validators = new _Badge_stuff_Validators(this);

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            expr: property({ type: STRING, precision: 255, validator: this.validators.validateExpr }),
            val: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVal }),
            levels: property({ type: ARRAY(INT), precision: 10, validator: this.validators.validateLevels }),
            descend: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateDescend }),
            transient_: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateTransient_ }),
            indexed: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateIndexed }),
            image: property({ type: STRING, precision: 30, validator: this.validators.validateImage }),
        });
    }

    constructor() {
        super();
    }

}

export default _Badge_stuff_TypeInfo;
