import { INT, LONG, STRING } from "skel01-core/src/lang/baseinfo";
import type { long } from "skel01-core/src/lang/basetype";
import { property } from "skel01-dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";

import { Course_TYPE } from "./CourseTypeInfo";
import _TestQuestion_stuff_Validators from "./_TestQuestion_stuff_Validators";

export class _TestQuestion_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "testq";

    static readonly FIELD_FORM_ARGUMENTS = "formargs";
    static readonly FIELD_COURSE_ID = "course";
    static readonly FIELD_FAV_COUNT = "nfav";
    static readonly FIELD_VOTE_COUNT = "nvote";
    static readonly FIELD_HATE_COUNT = "nhate";
    static readonly FIELD_POS = "pos";
    static readonly FIELD_ANSWER = "answer";

    static readonly N_FORM_ARGUMENTS = 2147483647;
    static readonly N_COURSE_ID = 10;
    static readonly N_FAV_COUNT = 10;
    static readonly N_VOTE_COUNT = 10;
    static readonly N_HATE_COUNT = 10;
    static readonly N_POS = 10;
    static readonly N_ANSWER = 100;

    readonly validators = new _TestQuestion_stuff_Validators(this);

    constructor() {
        super(LONG);
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestion"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            formArguments: property({ type: STRING, validator: this.validators.validateFormArguments }),
            favCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateFavCount }),
            voteCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteCount }),
            hateCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateHateCount }),
            pos: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validatePos }),
            answer: property({ type: STRING, nullable: false, precision: 100, validator: this.validators.validateAnswer }),

            course: property({ type: Course_TYPE, nullable: false, validator: this.validators.validateCourse }),
            courseId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _TestQuestion_stuff_TypeInfo();

}

export default _TestQuestion_stuff_TypeInfo;

export const _TestQuestion_stuff_TYPE = _TestQuestion_stuff_TypeInfo.INSTANCE;
