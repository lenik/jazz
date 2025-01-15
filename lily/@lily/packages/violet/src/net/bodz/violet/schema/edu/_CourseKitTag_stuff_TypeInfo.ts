import CoTagTypeInfo from "lily-basic/src/net/bodz/lily/concrete/CoTagTypeInfo";

import _CourseKitTag_stuff_Validators from "./_CourseKitTag_stuff_Validators";

export class _CourseKitTag_stuff_TypeInfo extends CoTagTypeInfo {

    static SCHEMA_NAME = "violet";
    static TABLE_NAME = "coursekittag";

    readonly validators = new _CourseKitTag_stuff_Validators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseKitTag"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

}

export default _CourseKitTag_stuff_TypeInfo;

export const _CourseKitTag_stuff_TYPE = _CourseKitTag_stuff_TypeInfo.INSTANCE;
