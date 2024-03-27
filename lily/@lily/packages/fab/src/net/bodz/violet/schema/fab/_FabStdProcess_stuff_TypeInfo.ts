import { BOOLEAN, INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import OffsetDateTime from "@skeljs/core/src/lang/time/OffsetDateTime";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoImagedTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoImagedTypeInfo";

import ArtifactModel from "../art/ArtifactModel";
import FabFn from "./FabFn";
import FabStdTest from "./FabStdTest";
import _FabStdProcess_stuff_Validators from "./_FabStdProcess_stuff_Validators";

export class _FabStdProcess_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabstdproc";

    static readonly FIELD_VALID = "valid";
    static readonly FIELD_VALID_SINCE = "validsince";
    static readonly FIELD_VALID_UNTIL = "validuntil";
    static readonly FIELD_OUTPUT_ID = "output";
    static readonly FIELD_FUNCTION_ID = "fn";
    static readonly FIELD_DURATION = "duration";
    static readonly FIELD_STRICT = "strict";
    static readonly FIELD_TEST_ID = "test";

    static readonly N_VALID = 1;
    static readonly N_VALID_SINCE = 35;
    static readonly N_VALID_UNTIL = 35;
    static readonly N_OUTPUT_ID = 10;
    static readonly N_FUNCTION_ID = 10;
    static readonly N_DURATION = 10;
    static readonly N_STRICT = 1;
    static readonly N_TEST_ID = 10;

    readonly validators = new _FabStdProcess_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdProcess"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            valid: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateValid }),
            validSince: property({ type: OffsetDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateValidSince }),
            validUntil: property({ type: OffsetDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateValidUntil }),
            duration: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateDuration }),
            strict: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateStrict }),

            output: property({ type: ArtifactModel.TYPE, nullable: false, validator: this.validators.validateOutput }),
            outputId: property({ type: INT, nullable: false, precision: 10 }),

            test: property({ type: FabStdTest.TYPE, validator: this.validators.validateTest }),
            testId: property({ type: INT, precision: 10 }),

            function: property({ type: FabFn.TYPE, validator: this.validators.validateFunction }),
            functionId: property({ type: INT, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabStdProcess_stuff_TypeInfo();

}

export default _FabStdProcess_stuff_TypeInfo;
