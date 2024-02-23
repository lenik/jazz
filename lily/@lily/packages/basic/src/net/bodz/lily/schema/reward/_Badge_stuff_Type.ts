import type { integer } from "@skeljs/core/src/lang/type";
import CoEntityType from "@skeljs/dba/src/net/bodz/lily/concrete/CoEntityType";
import { EntityPropertyMap, primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity";

import BadgeValidators from "./BadgeValidators";

export class _Badge_stuff_Type extends CoEntityType {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "badge";

    name = "net.bodz.lily.schema.reward.Badge"
    icon = "fa-tag"

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

    static validators = new BadgeValidators();

    static declaredProperty: EntityPropertyMap = {
        id: primaryKey({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateId }),
        expr: property({ type: "string", precision: 255, validator: this.validators.validateExpr }),
        val: property({ type: "integer", nullable: false, precision: 10, validator: this.validators.validateVal }),
        levels: property({ type: "int[]", precision: 10, validator: this.validators.validateLevels }),
        descend: property({ type: "boolean", nullable: false, precision: 1, validator: this.validators.validateDescend }),
        transient_: property({ type: "boolean", nullable: false, precision: 1, validator: this.validators.validateTransient_ }),
        indexed: property({ type: "boolean", nullable: false, precision: 1, validator: this.validators.validateIndexed }),
        image: property({ type: "string", precision: 30, validator: this.validators.validateImage }),
    }

    constructor() {
        super();
        this.declare(_Badge_stuff_Type.declaredProperty);
    }

}

export default _Badge_stuff_Type;
