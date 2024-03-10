import { INT } from "@skeljs/core/src/lang/baseinfo";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import FavRecordTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/FavRecordTypeInfo";

import CourseKit from "./CourseKit";
import _CourseKitFav_stuff_Validators from "./_CourseKitFav_stuff_Validators";

export class _CourseKitFav_stuff_TypeInfo extends FavRecordTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "coursekit_fav";

    static readonly FIELD_COURSEKIT_ID = "coursekit";

    static readonly N_COURSEKIT_ID = 10;

    readonly validators = new _CourseKitFav_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseKitFav"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({

            coursekit: property({ type: CourseKit.TYPE, nullable: false, validator: this.validators.validateCoursekit }),
            coursekitId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _CourseKitFav_stuff_TypeInfo();

}

export default _CourseKitFav_stuff_TypeInfo;
