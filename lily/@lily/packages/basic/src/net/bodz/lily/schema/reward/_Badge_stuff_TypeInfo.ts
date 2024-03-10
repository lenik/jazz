import { ARRAY, BOOLEAN, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoImagedTypeInfo from "../../concrete/CoImagedTypeInfo";
import _Badge_stuff_Validators from "./_Badge_stuff_Validators";

export class _Badge_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "badge";

    static readonly FIELD_EXPR = "expr";
    static readonly FIELD_VAL = "val";
    static readonly FIELD_LEVELS = "levels";
    static readonly FIELD_DESCEND = "descend";
    static readonly FIELD_TRANSIENT = "transient";
    static readonly FIELD_INDEXED = "indexed";

    static readonly N_EXPR = 255;
    static readonly N_VAL = 10;
    static readonly N_LEVELS = 10;
    static readonly N_DESCEND = 1;
    static readonly N_TRANSIENT = 1;
    static readonly N_INDEXED = 1;

    readonly validators = new _Badge_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.reward.Badge"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            expr: property({ type: STRING, precision: 255, validator: this.validators.validateExpr }),
            val: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVal }),
            levels: property({ type: ARRAY(INT), precision: 10, validator: this.validators.validateLevels }),
            descend: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateDescend }),
            transient_: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateTransient_ }),
            indexed: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateIndexed }),
        });
    }

    static readonly INSTANCE = new _Badge_stuff_TypeInfo();

}

export default _Badge_stuff_TypeInfo;
