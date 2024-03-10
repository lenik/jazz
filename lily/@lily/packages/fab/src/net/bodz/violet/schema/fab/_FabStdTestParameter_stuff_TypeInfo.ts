import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { BOOLEAN, INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";

import FabStdTest from "./FabStdTest";
import _FabStdTestParameter_stuff_Validators from "./_FabStdTestParameter_stuff_Validators";

export class _FabStdTestParameter_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "fabstdtest_parm";

    static readonly FIELD_ID = "id";
    static readonly FIELD_TEST_ID = "test";
    static readonly FIELD_REQUIRED = "required";
    static readonly FIELD_PROPERTIES = "props";
    static readonly FIELD_EXPECTED = "expected";

    static readonly N_ID = 10;
    static readonly N_TEST_ID = 10;
    static readonly N_REQUIRED = 1;
    static readonly N_PROPERTIES = 2147483647;
    static readonly N_EXPECTED = 100;

    readonly validators = new _FabStdTestParameter_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.fab.FabStdTestParameter"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: INT, nullable: false, precision: 10, validator: this.validators.validateId }),
            required: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateRequired }),
            properties: property({ type: JSON_VARIANT, validator: this.validators.validateProperties }),
            expected: property({ type: STRING, precision: 100, validator: this.validators.validateExpected }),

            test: property({ type: FabStdTest.TYPE, nullable: false, validator: this.validators.validateTest }),
            testId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _FabStdTestParameter_stuff_TypeInfo();

}

export default _FabStdTestParameter_stuff_TypeInfo;
