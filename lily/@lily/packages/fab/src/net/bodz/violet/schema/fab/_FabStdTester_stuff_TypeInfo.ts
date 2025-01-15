import { INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import _FabStdTester_stuff_Validators from "./_FabStdTester_stuff_Validators";

export class _FabStdTester_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabstdtester";

    static readonly FIELD_CMDLINE = "cmdline";

    static readonly N_CMDLINE = 200;

    readonly validators = new _FabStdTester_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTester"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            cmdline: property({ type: STRING, precision: 200, validator: this.validators.validateCmdline }),
        });
    }

    static readonly INSTANCE = new _FabStdTester_stuff_TypeInfo();

}

export default _FabStdTester_stuff_TypeInfo;

export const _FabStdTester_stuff_TYPE = _FabStdTester_stuff_TypeInfo.INSTANCE;
