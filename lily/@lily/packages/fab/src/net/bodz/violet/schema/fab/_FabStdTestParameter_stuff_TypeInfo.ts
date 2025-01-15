import { JSON_VARIANT } from "skel01-core/src/lang/bas-info";
import { BOOLEAN, INT, STRING } from "skel01-core/src/lang/baseinfo";
import type { int } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "lily-basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import { FabStdTest_TYPE } from "./FabStdTestTypeInfo";
import _FabStdTestParameter_stuff_Validators from "./_FabStdTestParameter_stuff_Validators";

export class _FabStdTestParameter_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabstdtest_parm";

    static readonly FIELD_TEST_ID = "test";
    static readonly FIELD_REQUIRED = "required";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_EXPECTED = "expected";

    static readonly N_TEST_ID = 10;
    static readonly N_REQUIRED = 1;
    static readonly N_PROPERTIES = 2147483647;
    static readonly N_EXPECTED = 100;

    readonly validators = new _FabStdTestParameter_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTestParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            required: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateRequired }),
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            expected: property({ type: STRING, precision: 100, validator: this.validators.validateExpected }),

            test: property({ type: FabStdTest_TYPE, nullable: false, validator: this.validators.validateTest }),
            testId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabStdTestParameter_stuff_TypeInfo();

}

export default _FabStdTestParameter_stuff_TypeInfo;

export const _FabStdTestParameter_stuff_TYPE = _FabStdTestParameter_stuff_TypeInfo.INSTANCE;
