import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEventTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEventTypeInfo";
import { OrgUnit_TYPE } from "@lily/basic/src/net/bodz/lily/schema/contact/OrgUnitTypeInfo";

import { FabProcess_TYPE } from "./FabProcessTypeInfo";
import _FabTrack_stuff_Validators from "./_FabTrack_stuff_Validators";

export class _FabTrack_stuff_TypeInfo extends CoEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabtrack";

    static readonly FIELD_PROCESS_ID = "proc";
    static readonly FIELD_SINCE = "since";
    static readonly FIELD_DEADLINE = "deadline";
    static readonly FIELD_PLANNED_QUANTITY = "qty_plan";
    static readonly FIELD_ACTUAL_QUANTITY = "qty_actual";
    static readonly FIELD_VALID_QUANTITY = "qty_valid";
    static readonly FIELD_ORG_UNIT_ID = "ou";

    static readonly N_PROCESS_ID = 19;
    static readonly N_SINCE = 35;
    static readonly N_DEADLINE = 35;
    static readonly N_PLANNED_QUANTITY = 20;
    static readonly N_ACTUAL_QUANTITY = 20;
    static readonly N_VALID_QUANTITY = 20;
    static readonly N_ORG_UNIT_ID = 10;

    readonly validators = new _FabTrack_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.fab.FabTrack"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            since: property({ type: OffsetDateTime.TYPE, nullable: false, precision: 35, scale: 6, validator: this.validators.validateSince }),
            deadline: property({ type: OffsetDateTime.TYPE, nullable: false, precision: 35, scale: 6, validator: this.validators.validateDeadline }),
            plannedQuantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validatePlannedQuantity }),
            actualQuantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateActualQuantity }),
            validQuantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateValidQuantity }),

            orgUnit: property({ type: OrgUnit_TYPE, validator: this.validators.validateOrgUnit }),
            orgUnitId: property({ type: INT, precision: 10 }),

            process: property({ type: FabProcess_TYPE, nullable: false, validator: this.validators.validateProcess }),
            processId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _FabTrack_stuff_TypeInfo();

}

export default _FabTrack_stuff_TypeInfo;

export const _FabTrack_stuff_TYPE = _FabTrack_stuff_TypeInfo.INSTANCE;
