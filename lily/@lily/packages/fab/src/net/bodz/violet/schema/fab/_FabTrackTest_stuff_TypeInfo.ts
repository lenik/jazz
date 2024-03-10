import { BOOLEAN, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";

import FabStdTest from "./FabStdTest";
import FabTrack from "./FabTrack";
import _FabTrackTest_stuff_Validators from "./_FabTrackTest_stuff_Validators";

export class _FabTrackTest_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabtrack_test";

    static readonly FIELD_ID = "id";
    static readonly FIELD_TRACK_ID = "track";
    static readonly FIELD_STANDARD_ID = "std";
    static readonly FIELD_VALID = "valid";

    static readonly N_ID = 19;
    static readonly N_TRACK_ID = 19;
    static readonly N_STANDARD_ID = 10;
    static readonly N_VALID = 1;

    readonly validators = new _FabTrackTest_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabTrackTest"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: LONG, nullable: false, precision: 19, validator: this.validators.validateId }),
            valid: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateValid }),

            track: property({ type: FabTrack.TYPE, nullable: false, validator: this.validators.validateTrack }),
            trackId: property({ type: LONG, nullable: false, precision: 19 }),

            standard: property({ type: FabStdTest.TYPE, nullable: false, validator: this.validators.validateStandard }),
            standardId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabTrackTest_stuff_TypeInfo();

}

export default _FabTrackTest_stuff_TypeInfo;
