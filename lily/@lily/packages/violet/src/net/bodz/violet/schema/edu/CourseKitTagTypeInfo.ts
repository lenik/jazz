import CourseKitTag from "./CourseKitTag";
import CourseKitTagValidators from "./CourseKitTagValidators";
import _CourseKitTag_stuff_TypeInfo from "./_CourseKitTag_stuff_TypeInfo";

export class CourseKitTagTypeInfo extends _CourseKitTag_stuff_TypeInfo {

    readonly validators = new CourseKitTagValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseKitTag"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new CourseKitTag();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new CourseKitTagTypeInfo();

}

export default CourseKitTagTypeInfo;

export const CourseKitTag_TYPE = CourseKitTagTypeInfo.INSTANCE;
