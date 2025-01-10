import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoEventTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEventTypeInfo";
import { ArtifactModel_TYPE } from "@lily/violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";

import { FabTask_TYPE } from "./FabTaskTypeInfo";
import _FabTaskItem_stuff_Validators from "./_FabTaskItem_stuff_Validators";

export class _FabTaskItem_stuff_TypeInfo extends CoEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabtaskl";

    static readonly FIELD_TASK_ID = "task";
    static readonly FIELD_DEADLINE = "deadline";
    static readonly FIELD_STATUS = "status";
    static readonly FIELD_MODEL_ID = "model";
    static readonly FIELD_QUANTITY = "qty";
    static readonly FIELD_BATCH = "batch";
    static readonly FIELD_TRACK_COUNT = "ntrack";

    static readonly N_TASK_ID = 19;
    static readonly N_DEADLINE = 35;
    static readonly N_STATUS = 100;
    static readonly N_MODEL_ID = 10;
    static readonly N_QUANTITY = 20;
    static readonly N_BATCH = 2147483647;
    static readonly N_TRACK_COUNT = 10;

    readonly validators = new _FabTaskItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.fab.FabTaskItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            deadline: property({ type: OffsetDateTime.TYPE, nullable: false, precision: 35, scale: 6, validator: this.validators.validateDeadline }),
            status: property({ type: STRING, precision: 100, validator: this.validators.validateStatus }),
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),
            trackCount: property({ type: INT, precision: 10, validator: this.validators.validateTrackCount }),

            task: property({ type: FabTask_TYPE, nullable: false, validator: this.validators.validateTask }),
            taskId: property({ type: LONG, nullable: false, precision: 19 }),

            model: property({ type: ArtifactModel_TYPE, nullable: false, validator: this.validators.validateModel }),
            modelId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabTaskItem_stuff_TypeInfo();

}

export default _FabTaskItem_stuff_TypeInfo;

export const _FabTaskItem_stuff_TYPE = _FabTaskItem_stuff_TypeInfo.INSTANCE;
