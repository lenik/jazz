import { LONG } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import FavRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/FavRecordTypeInfo";

import { TestQuestion_TYPE } from "./TestQuestionTypeInfo";
import _TestQuestionFav_stuff_Validators from "./_TestQuestionFav_stuff_Validators";

export class _TestQuestionFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "testq_fav";

    static readonly FIELD_TESTQ_ID = "testq";

    static readonly N_TESTQ_ID = 19;

    readonly validators = new _TestQuestionFav_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestionFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            testq: property({ type: TestQuestion_TYPE, nullable: false, validator: this.validators.validateTestq }),
            testqId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _TestQuestionFav_stuff_TypeInfo();

}

export default _TestQuestionFav_stuff_TypeInfo;

export const _TestQuestionFav_stuff_TYPE = _TestQuestionFav_stuff_TypeInfo.INSTANCE;
