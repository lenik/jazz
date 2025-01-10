import { DOUBLE, INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import IdEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/IdEntityTypeInfo";

import { TestApply_TYPE } from "./TestApplyTypeInfo";
import { TestQuestion_TYPE } from "./TestQuestionTypeInfo";
import _TestApplyItem_stuff_Validators from "./_TestApplyItem_stuff_Validators";

export class _TestApplyItem_stuff_TypeInfo extends IdEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "testapplyl";

    static readonly FIELD_APPLY_ID = "apply";
    static readonly FIELD_QUESTION_ID = "q";
    static readonly FIELD_ANSWER = "ans";
    static readonly FIELD_ANSTEXT = "anstext";
    static readonly FIELD_SCORE = "score";
    static readonly FIELD_WAITTIME = "waittime";

    static readonly N_APPLY_ID = 19;
    static readonly N_QUESTION_ID = 19;
    static readonly N_ANSWER = 10;
    static readonly N_ANSTEXT = 200;
    static readonly N_SCORE = 17;
    static readonly N_WAITTIME = 17;

    readonly validators = new _TestApplyItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.edu.TestApplyItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            answer: property({ type: INT, precision: 10, validator: this.validators.validateAnswer }),
            anstext: property({ type: STRING, precision: 200, validator: this.validators.validateAnstext }),
            score: property({ type: DOUBLE, precision: 17, scale: 17, validator: this.validators.validateScore }),
            waittime: property({ type: DOUBLE, precision: 17, scale: 17, validator: this.validators.validateWaittime }),

            question: property({ type: TestQuestion_TYPE, nullable: false, validator: this.validators.validateQuestion }),
            questionId: property({ type: LONG, nullable: false, precision: 19 }),

            apply: property({ type: TestApply_TYPE, nullable: false, validator: this.validators.validateApply }),
            applyId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _TestApplyItem_stuff_TypeInfo();

}

export default _TestApplyItem_stuff_TypeInfo;

export const _TestApplyItem_stuff_TYPE = _TestApplyItem_stuff_TypeInfo.INSTANCE;
