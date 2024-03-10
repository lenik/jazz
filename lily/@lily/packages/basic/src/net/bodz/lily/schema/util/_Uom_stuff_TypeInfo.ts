import { DOUBLE, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoCodeTypeInfo from "../../concrete/CoCodeTypeInfo";
import Uom from "./Uom";
import _Uom_stuff_Validators from "./_Uom_stuff_Validators";

export class _Uom_stuff_TypeInfo extends CoCodeTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "uom";

    static readonly FIELD_PROPERTY = "prop";
    static readonly FIELD_STANDARD_ID = "std";
    static readonly FIELD_SCALE = "scale";

    static readonly N_PROPERTY = 30;
    static readonly N_STANDARD_ID = 10;
    static readonly N_SCALE = 17;

    readonly validators = new _Uom_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.lily.schema.util.Uom"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            property: property({ type: STRING, nullable: false, precision: 30, validator: this.validators.validateProperty }),
            scale: property({ type: DOUBLE, nullable: false, precision: 17, scale: 17, validator: this.validators.validateScale }),

            standard: property({ type: this, validator: this.validators.validateStandard }),
            standardId: property({ type: INT, precision: 10 }),
        });
    }

}

export default _Uom_stuff_TypeInfo;
