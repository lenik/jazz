import { INT, LONG, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { long } from "@skeljs/core/src/lang/basetype";
import ZonedDateTime from "@skeljs/core/src/lang/time/ZonedDateTime";
import { primaryKey, property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoEntityTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoEntityTypeInfo";
import type User from "@lily/basic/src/net/bodz/lily/schema/account/User";
import FormDef from "@lily/basic/src/net/bodz/lily/schema/meta/FormDef";

import TestQuestion from "./TestQuestion";
import TestQuestionTalk from "./TestQuestionTalk";
import _TestQuestionTalk_stuff_Validators from "./_TestQuestionTalk_stuff_Validators";

export class _TestQuestionTalk_stuff_TypeInfo extends CoEntityTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "testq_msg";

    static readonly FIELD_ID = "id";
    static readonly FIELD_BEGIN_TIME = "t0";
    static readonly FIELD_END_TIME = "t1";
    static readonly FIELD_YEAR = "year";
    static readonly FIELD_SUBJECT = "subject";
    static readonly FIELD_OP_ID = "op";
    static readonly FIELD_RAW_TEXT = "text";
    static readonly FIELD_FORM_ID = "form";
    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_QUESTION_ID = "q";
    static readonly FIELD_PARENT_ID = "parent";

    static readonly N_ID = 19;
    static readonly N_BEGIN_TIME = 35;
    static readonly N_END_TIME = 35;
    static readonly N_YEAR = 10;
    static readonly N_SUBJECT = 200;
    static readonly N_OP_ID = 10;
    static readonly N_RAW_TEXT = 2147483647;
    static readonly N_FORM_ID = 10;
    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_QUESTION_ID = 19;
    static readonly N_PARENT_ID = 19;

    readonly validators = new _TestQuestionTalk_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestionTalk"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            id: primaryKey({ type: LONG, nullable: false, precision: 19, validator: this.validators.validateId }),
            beginTime: property({ type: ZonedDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateBeginTime }),
            endTime: property({ type: ZonedDateTime.TYPE, precision: 35, scale: 6, validator: this.validators.validateEndTime }),
            year: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateYear }),
            subject: property({ type: STRING, nullable: false, precision: 200, validator: this.validators.validateSubject }),
            rawText: property({ type: STRING, validator: this.validators.validateRawText }),
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),

            form: property({ type: FormDef.TYPE, validator: this.validators.validateForm }),
            formId: property({ type: INT, precision: 10 }),

            op: property({ type: User.TYPE, inheritsDoc: true, 
                description: "User Account", 
                validator: this.validators.validateOp }),
            opId: property({ type: INT, precision: 10 }),

            parent: property({ type: this, validator: this.validators.validateParent }),
            parentId: property({ type: LONG, precision: 19 }),

            question: property({ type: TestQuestion.TYPE, nullable: false, validator: this.validators.validateQuestion }),
            questionId: property({ type: LONG, nullable: false, precision: 19 }),
        });
    }

    static readonly INSTANCE = new _TestQuestionTalk_stuff_TypeInfo();

}

export default _TestQuestionTalk_stuff_TypeInfo;
