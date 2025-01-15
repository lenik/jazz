import { BOOLEAN, LONG } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";

import { TestQuestion_TYPE } from "./TestQuestionTypeInfo";
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

            question: property({ type: TestQuestion_TYPE, nullable: false, validator: this.validators.validateQuestion }),
            questionId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _TestAnswer_stuff_TypeInfo();

}

export default _TestAnswer_stuff_TypeInfo;

export const _TestAnswer_stuff_TYPE = _TestAnswer_stuff_TypeInfo.INSTANCE;
