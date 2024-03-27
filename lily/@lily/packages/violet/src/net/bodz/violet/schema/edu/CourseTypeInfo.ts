import Course from "./Course";
import CourseValidators from "./CourseValidators";
import _Course_stuff_TypeInfo from "./_Course_stuff_TypeInfo";

export class CourseTypeInfo extends _Course_stuff_TypeInfo {

    readonly validators = new CourseValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.Course"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new Course();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new CourseTypeInfo();

}

export default CourseTypeInfo;
