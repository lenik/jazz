import CoTagTypeInfo from "@lily/basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _CourseTag_stuff_Validators from "./_CourseTag_stuff_Validators";

export class _CourseTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "coursetag";

    readonly validators = new _CourseTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _CourseTag_stuff_TypeInfo;
