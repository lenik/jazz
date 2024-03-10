import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";

import DiaryCategory from "./DiaryCategory";
import DiaryPhase from "./DiaryPhase";
import _Diary_stuff_Validators from "./_Diary_stuff_Validators";

export class _Diary_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "diary";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_PHASE_ID = "phase";
    static readonly FIELD_VALUE = "value";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_CATEGORY_ID = 10;
    static readonly N_PHASE_ID = 10;
    static readonly N_VALUE = 10;

    readonly validators = new _Diary_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.plan.Diary"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            value: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateValue }),

            phase: property({ type: DiaryPhase.TYPE, validator: this.validators.validatePhase }),
            phaseId: property({ type: INT, precision: 10 }),

            category: property({ type: DiaryCategory.TYPE, nullable: false, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _Diary_stuff_TypeInfo();

}

export default _Diary_stuff_TypeInfo;
