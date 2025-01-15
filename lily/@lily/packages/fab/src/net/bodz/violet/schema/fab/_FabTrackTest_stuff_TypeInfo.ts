import { BOOLEAN, INT, LONG } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import { FabStdTest_TYPE } from "./FabStdTestTypeInfo";
import { FabTrack_TYPE } from "./FabTrackTypeInfo";
import _FabTrackTest_stuff_Validators from "./_FabTrackTest_stuff_Validators";

export class _FabTrackTest_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabtrack_test";

    static readonly FIELD_TRACK_ID = "track";
    static readonly FIELD_STANDARD_ID = "std";
    static readonly FIELD_VALID = "valid";

    static readonly N_TRACK_ID = 19;
    static readonly N_STANDARD_ID = 10;
    static readonly N_VALID = 1;

    readonly validators = new _FabTrackTest_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.fab.FabTrackTest"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            valid: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateValid }),

            track: property({ type: FabTrack_TYPE, nullable: false, validator: this.validators.validateTrack }),
            trackId: property({ type: LONG, nullable: false, precision: 19 }),

            standard: property({ type: FabStdTest_TYPE, nullable: false, validator: this.validators.validateStandard }),
            standardId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabTrackTest_stuff_TypeInfo();

}

export default _FabTrackTest_stuff_TypeInfo;

export const _FabTrackTest_stuff_TYPE = _FabTrackTest_stuff_TypeInfo.INSTANCE;
