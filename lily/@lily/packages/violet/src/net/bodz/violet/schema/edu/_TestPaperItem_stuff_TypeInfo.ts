import { BIG_DECIMAL, INT, LONG } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoImagedTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoImagedTypeInfo";

import { TestPaper_TYPE } from "./TestPaperTypeInfo";
import { TestQuestion_TYPE } from "./TestQuestionTypeInfo";
import _TestPaperItem_stuff_Validators from "./_TestPaperItem_stuff_Validators";

export class _TestPaperItem_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "testpaperl";

    static readonly FIELD_PAPER_ID = "paper";
    static readonly FIELD_QUESTION_ID = "q";
    static readonly FIELD_SCORE = "score";

    static readonly N_PAPER_ID = 10;
    static readonly N_QUESTION_ID = 19;
    static readonly N_SCORE = 6;

    readonly validators = new _TestPaperItem_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.edu.TestPaperItem"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            score: property({ type: BIG_DECIMAL, precision: 6, scale: 2, validator: this.validators.validateScore }),

            question: property({ type: TestQuestion_TYPE, nullable: false, validator: this.validators.validateQuestion }),
            questionId: property({ type: LONG, nullable: false, precision: 19 }),

            paper: property({ type: TestPaper_TYPE, nullable: false, validator: this.validators.validatePaper }),
            paperId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _TestPaperItem_stuff_TypeInfo();

}

export default _TestPaperItem_stuff_TypeInfo;

export const _TestPaperItem_stuff_TYPE = _TestPaperItem_stuff_TypeInfo.INSTANCE;
