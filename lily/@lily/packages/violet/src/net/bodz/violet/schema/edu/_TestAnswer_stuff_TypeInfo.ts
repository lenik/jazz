import { BOOLEAN, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";

import TestQuestion from "./TestQuestion";
import _TestAnswer_stuff_Validators from "./_TestAnswer_stuff_Validators";

export class _TestAnswer_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "testans";

    static readonly FIELD_QUESTION_ID = "q";
    static readonly FIELD_VALID = "valid";

    static readonly N_QUESTION_ID = 19;
    static readonly N_VALID = 1;

    readonly validators = new _TestAnswer_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.edu.TestAnswer"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            valid: property({ type: BOOLEAN, nullable: false, precision: 1, validator: this.validators.validateValid }),

            question: property({ type: TestQuestion.TYPE, nullable: false, validator: this.validators.validateQuestion }),
            questionId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _TestAnswer_stuff_TypeInfo();

}

export default _TestAnswer_stuff_TypeInfo;
