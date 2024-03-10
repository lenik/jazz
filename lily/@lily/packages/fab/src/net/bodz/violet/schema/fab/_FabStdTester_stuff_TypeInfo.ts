import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";

import _FabStdTester_stuff_Validators from "./_FabStdTester_stuff_Validators";

export class _FabStdTester_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabstdtester";

    static readonly FIELD_ID = "id";
    static readonly FIELD_CMDLINE = "cmdline";

    static readonly N_ID = 10;
    static readonly N_CMDLINE = 200;

    readonly validators = new _FabStdTester_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTester"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            cmdline: property({ type: STRING, precision: 200, validator: this.validators.validateCmdline }),
        });
    }

    static readonly INSTANCE = new _FabStdTester_stuff_TypeInfo();

}

export default _FabStdTester_stuff_TypeInfo;
