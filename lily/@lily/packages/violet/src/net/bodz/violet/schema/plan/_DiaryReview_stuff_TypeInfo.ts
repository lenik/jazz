import { LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";

import { DiaryReview_TYPE } from "./DiaryReviewTypeInfo";
import { Diary_TYPE } from "./DiaryTypeInfo";
import _DiaryReview_stuff_Validators from "./_DiaryReview_stuff_Validators";

export class _DiaryReview_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "diaryrev";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_DIARY_ID = "diary";
    static readonly FIELD_PARENT_ID = "parent";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_DIARY_ID = 19;
    static readonly N_PARENT_ID = 19;

    readonly validators = new _DiaryReview_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryReview"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),

            diary: property({ type: Diary_TYPE, nullable: false, validator: this.validators.validateDiary }),
            diaryId: property({ type: LONG, nullable: false, precision: 19 }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: LONG, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _DiaryReview_stuff_TypeInfo();

}

export default _DiaryReview_stuff_TypeInfo;

export const _DiaryReview_stuff_TYPE = _DiaryReview_stuff_TypeInfo.INSTANCE;
