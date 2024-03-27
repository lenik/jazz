import { INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEventTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEventTypeInfo";

import FabOrder from "./FabOrder";
import _FabTask_stuff_Validators from "./_FabTask_stuff_Validators";

export class _FabTask_stuff_TypeInfo extends CoEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabtask";

    static readonly FIELD_ORDER_ID = "odr";
    static readonly FIELD_SINCE = "since";
    static readonly FIELD_DEADLINE = "deadline";
    static readonly FIELD_PROCESS_COUNT = "nproc";
    static readonly FIELD_TRACK_COUNT = "ntrack";

    static readonly N_ORDER_ID = 19;
    static readonly N_SINCE = 35;
    static readonly N_DEADLINE = 35;
    static readonly N_PROCESS_COUNT = 10;
    static readonly N_TRACK_COUNT = 10;

    readonly validators = new _FabTask_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.fab.FabTask"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            since: property({ type: OffsetDateTime.TYPE, nullable: false, precision: 35, scale: 6, validator: this.validators.validateSince }),
            deadline: property({ type: OffsetDateTime.TYPE, nullable: false, precision: 35, scale: 6, validator: this.validators.validateDeadline }),
            processCount: property({ type: INT, precision: 10, validator: this.validators.validateProcessCount }),
            trackCount: property({ type: INT, precision: 10, validator: this.validators.validateTrackCount }),

            order: property({ type: FabOrder.TYPE, nullable: false, validator: this.validators.validateOrder }),
            orderId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _FabTask_stuff_TypeInfo();

}

export default _FabTask_stuff_TypeInfo;
