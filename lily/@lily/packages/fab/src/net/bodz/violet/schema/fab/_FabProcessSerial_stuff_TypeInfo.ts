import { LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import FabProcess from "./FabProcess";
import _FabProcessSerial_stuff_Validators from "./_FabProcessSerial_stuff_Validators";

export class _FabProcessSerial_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabproc_sn";

    static readonly FIELD_PROCESS_ID = "proc";
    static readonly FIELD_SERIAL = "serial";

    static readonly N_PROCESS_ID = 19;
    static readonly N_SERIAL = 40;

    readonly validators = new _FabProcessSerial_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.fab.FabProcessSerial"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            serial: property({ type: STRING, nullable: false, precision: 40, validator: this.validators.validateSerial }),

            process: property({ type: FabProcess.TYPE, nullable: false, validator: this.validators.validateProcess }),
            processId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _FabProcessSerial_stuff_TypeInfo();

}

export default _FabProcessSerial_stuff_TypeInfo;
