import { JSON_VARIANT } from "@skeljs/core/src/lang/bas-info";
import { INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoImagedTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoImagedTypeInfo";

import Course from "./Course";
import CourseKitCategory from "./CourseKitCategory";
import _CourseKit_stuff_Validators from "./_CourseKit_stuff_Validators";

export class _CourseKit_stuff_TypeInfo extends CoImagedTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "coursekit";

    static readonly FIELD_CATEGORY_ID = "cat";
    static readonly FIELD_COURSE_ID = "course";
    static readonly FIELD_FAV_COUNT = "nfav";
    static readonly FIELD_VOTE_COUNT = "nvote";
    static readonly FIELD_HATE_COUNT = "nhate";
    static readonly FIELD_DUMMY = "dummy";

    static readonly N_CATEGORY_ID = 10;
    static readonly N_COURSE_ID = 10;
    static readonly N_FAV_COUNT = 10;
    static readonly N_VOTE_COUNT = 10;
    static readonly N_HATE_COUNT = 10;
    static readonly N_DUMMY = 2147483647;

    readonly validators = new _CourseKit_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.edu.CourseKit"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            favCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateFavCount }),
            voteCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateVoteCount }),
            hateCount: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateHateCount }),
            dummy: property({ type: JSON_VARIANT, validator: this.validators.validateDummy }),

            category: property({ type: CourseKitCategory.TYPE, nullable: false, validator: this.validators.validateCategory }),
            categoryId: property({ type: INT, nullable: false, precision: 10 }),

            course: property({ type: Course.TYPE, nullable: false, validator: this.validators.validateCourse }),
            courseId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _CourseKit_stuff_TypeInfo();

}

export default _CourseKit_stuff_TypeInfo;
