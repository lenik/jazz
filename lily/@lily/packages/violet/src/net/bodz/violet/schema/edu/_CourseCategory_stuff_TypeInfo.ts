import { INT, STRING } from "@skeljs/core/src/lang/baseinfo";
import type { int } from "@skeljs/core/src/lang/basetype";
import { property } from "@skeljs/dba/src/net/bodz/lily/entity/EntityType";
import CoCategoryTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoCategoryTypeInfo";

import _CourseCategory_stuff_Validators from "./_CourseCategory_stuff_Validators";

export class _CourseCategory_stuff_TypeInfo extends CoCategoryTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "coursecat";

    static readonly FIELD_NAME = "name";

    static readonly N_NAME = 30;

    readonly validators = new _CourseCategory_stuff_Validators(this);

    constructor() {
        super(INT);
    }

    get name() { return "net.bodz.violet.schema.edu.CourseCategory"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
            name: property({ type: STRING, precision: 30, validator: this.validators.validateName }),
        });
    }

}

export default _CourseCategory_stuff_TypeInfo;

export const _CourseCategory_stuff_TYPE = _CourseCategory_stuff_TypeInfo.INSTANCE;
