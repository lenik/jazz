import CourseTagValidators from "./CourseTagValidators";
import _CourseTag_stuff_TypeInfo from "./_CourseTag_stuff_TypeInfo";

export class CourseTagTypeInfo extends _CourseTag_stuff_TypeInfo {

    readonly validators = new CourseTagValidators(this);

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

    static readonly INSTANCE = new CourseTagTypeInfo();

}

export default CourseTagTypeInfo;
