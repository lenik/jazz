import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BIG_DECIMAL, INT, LONG } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import OffsetDateTime from "skel01-core/src/lang/time/OffsetDateTime";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoEventTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoEventTypeInfo";
import { ArtifactModel_TYPE } from "lily-violet/src/net/bodz/violet/schema/art/ArtifactModelTypeInfo";

import { FabProcess_TYPE } from "./FabProcessTypeInfo";
import { FabStdProcess_TYPE } from "./FabStdProcessTypeInfo";
import { FabTask_TYPE } from "./FabTaskTypeInfo";
import _FabProcess_stuff_Validators from "./_FabProcess_stuff_Validators";

export class _FabProcess_stuff_TypeInfo extends CoEventTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabproc";

    static readonly FIELD_TASK_ID = "task";
    static readonly FIELD_PARENT_ID = "parent";
    static readonly FIELD_OUTPUT_ID = "output";
    static readonly FIELD_STANDARD_ID = "std";
    static readonly FIELD_QUANTITY = "qty";
    static readonly FIELD_BATCH = "batch";
    static readonly FIELD_SINCE = "since";
    static readonly FIELD_DEADLINE = "deadline";
    static readonly FIELD_TRACK_COUNT = "ntrack";

    static readonly N_TASK_ID = 19;
    static readonly N_PARENT_ID = 19;
    static readonly N_OUTPUT_ID = 10;
    static readonly N_STANDARD_ID = 10;
    static readonly N_QUANTITY = 20;
    static readonly N_BATCH = 2147483647;
    static readonly N_SINCE = 35;
    static readonly N_DEADLINE = 35;
    static readonly N_TRACK_COUNT = 10;

    readonly validators = new _FabProcess_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.fab.FabProcess"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            quantity: property({ type: BIG_DECIMAL, nullable: false, precision: 20, scale: 2, validator: this.validators.validateQuantity }),
            batch: property({ type: JSON_VARIANT, validator: this.validators.validateBatch }),
            since: property({ type: OffsetDateTime.TYPE, nullable: false, precision: 35, scale: 6, validator: this.validators.validateSince }),
            deadline: property({ type: OffsetDateTime.TYPE, nullable: false, precision: 35, scale: 6, validator: this.validators.validateDeadline }),
            trackCount: property({ type: INT, precision: 10, validator: this.validators.validateTrackCount }),

            standard: property({ type: FabStdProcess_TYPE, nullable: false, validator: this.validators.validateStandard }),
            standardId: property({ type: INT, nullable: false, precision: 10 }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: LONG, precision: 19 }),

            output: property({ type: ArtifactModel_TYPE, nullable: false, validator: this.validators.validateOutput }),
            outputId: property({ type: INT, nullable: false, precision: 10 }),

            task: property({ type: FabTask_TYPE, nullable: false, validator: this.validators.validateTask }),
            taskId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _FabProcess_stuff_TypeInfo();

}

export default _FabProcess_stuff_TypeInfo;

export const _FabProcess_stuff_TYPE = _FabProcess_stuff_TypeInfo.INSTANCE;
