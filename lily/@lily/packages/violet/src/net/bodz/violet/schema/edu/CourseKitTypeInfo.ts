import CourseKit from "./CourseKit";
import CourseKitValidators from "./CourseKitValidators";
import _CourseKit_stuff_TypeInfo from "./_CourseKit_stuff_TypeInfo";

export class CourseKitTypeInfo extends _CourseKit_stuff_TypeInfo {

    readonly validators = new CourseKitValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseKit"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new CourseKit();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new CourseKitTypeInfo();

}

export default CourseKitTypeInfo;

export const CourseKit_TYPE = CourseKitTypeInfo.INSTANCE;
