import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";

import CoImagedTypeInfo from "../../concrete/CoImagedTypeInfo";
import _Storage_stuff_Validators from "./_Storage_stuff_Validators";

export class _Storage_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "lily";
    static TABLE_NAME = "storage";

    static readonly FIELD_NAME = "name";

    static readonly N_NAME = 30;

    readonly validators = new _Storage_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.lily.schema.io.Storage"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, nullable: false, precision: 30, validator: this.validators.validateName }),
        });
    }

    static readonly INSTANCE = new _Storage_stuff_TypeInfo();

}

export default _Storage_stuff_TypeInfo;
