import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoImagedTypeInfo from "../../concrete/CoImagedTypeInfo";
import _ApiType_stuff_Validators from "./_ApiType_stuff_Validators";

export class _ApiType_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "apitype";

    static readonly FIELD_CODE = "code";
    static readonly FIELD_UOM = "uom";

    static readonly N_CODE = 30;
    static readonly N_UOM = 30;

    readonly validators = new _ApiType_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.vapp.ApiType"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            code: property({ type: STRING, precision: 30, validator: this.validators.validateCode }),
            uom: property({ type: STRING, nullable: false, precision: 30, validator: this.validators.validateUom }),
        });
    }

    static readonly INSTANCE = new _ApiType_stuff_TypeInfo();

}

export default _ApiType_stuff_TypeInfo;
