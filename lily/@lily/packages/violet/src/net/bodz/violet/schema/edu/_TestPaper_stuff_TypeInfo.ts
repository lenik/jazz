import { INT } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoMessageTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoMessageTypeInfo";

import Course from "./Course";
import _TestPaper_stuff_Validators from "./_TestPaper_stuff_Validators";

export class _TestPaper_stuff_TypeInfo extends CoMessageTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "testpaper";

    static readonly FIELD_COURSE_ID = "course";
    static readonly FIELD_TIMEOUT = "timeout";
    static readonly FIELD_TOTALSCORE = "totalscore";

    static readonly N_COURSE_ID = 10;
    static readonly N_TIMEOUT = 10;
    static readonly N_TOTALSCORE = 10;

    readonly validators = new _TestPaper_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.edu.TestPaper"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            timeout: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateTimeout }),
            totalscore: property({ type: INT, nullable: false, precision: 10, validator: this.validators.validateTotalscore }),

            course: property({ type: Course.TYPE, nullable: false, validator: this.validators.validateCourse }),
            courseId: property({ type: INT, nullable: false, precision: 10 }),
        });
    }

    static readonly INSTANCE = new _TestPaper_stuff_TypeInfo();

}

export default _TestPaper_stuff_TypeInfo;
