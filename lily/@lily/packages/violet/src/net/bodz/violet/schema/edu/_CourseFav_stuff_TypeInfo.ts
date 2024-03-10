import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import FavRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/FavRecordTypeInfo";

import Course from "./Course";
import _CourseFav_stuff_Validators from "./_CourseFav_stuff_Validators";

export class _CourseFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "course_fav";

    static readonly FIELD_COURSE_ID = "course";

    static readonly N_COURSE_ID = 10;

    readonly validators = new _CourseFav_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            course: property({ type: Course.TYPE, nullable: false, validator: this.validators.validateCourse }),
            courseId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _CourseFav_stuff_TypeInfo();

}

export default _CourseFav_stuff_TypeInfo;
