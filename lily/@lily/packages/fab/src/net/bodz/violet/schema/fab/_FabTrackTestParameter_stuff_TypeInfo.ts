import { BOOLEAN, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import { FabStdTestParameter_TYPE } from "./FabStdTestParameterTypeInfo";
import { FabStdTester_TYPE } from "./FabStdTesterTypeInfo";
import { FabTrackTest_TYPE } from "./FabTrackTestTypeInfo";
import _FabTrackTestParameter_stuff_Validators from "./_FabTrackTestParameter_stuff_Validators";

export class _FabTrackTestParameter_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabtrack_test_parm";

    static readonly FIELD_TEST_ID = "test";
    static readonly FIELD_PARAMETER_ID = "parm";
    static readonly FIELD_TESTER_ID = "tester";
    static readonly FIELD_ACTUAL = "actual";
    static readonly FIELD_VALID = "valid";

    static readonly N_TEST_ID = 19;
    static readonly N_PARAMETER_ID = 10;
    static readonly N_TESTER_ID = 10;
    static readonly N_ACTUAL = 100;
    static readonly N_VALID = 1;

    readonly validators = new _FabTrackTestParameter_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.fab.FabTrackTestParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            actual: property({ type: STRING, nullable: false, precision: 100, validator: this.validators.validateActual }),
            valid: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateValid }),

            parameter: property({ type: FabStdTestParameter_TYPE, nullable: false, validator: this.validators.validateParameter }),
            parameterId: property({ type: INT, nullable: false, precision: 10 }),

            tester: property({ type: FabStdTester_TYPE, validator: this.validators.validateTester }),
            testerId: property({ type: INT, precision: 10 }),

            test: property({ type: FabTrackTest_TYPE, nullable: false, validator: this.validators.validateTest }),
            testId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _FabTrackTestParameter_stuff_TypeInfo();

}

export default _FabTrackTestParameter_stuff_TypeInfo;

export const _FabTrackTestParameter_stuff_TYPE = _FabTrackTestParameter_stuff_TypeInfo.INSTANCE;
