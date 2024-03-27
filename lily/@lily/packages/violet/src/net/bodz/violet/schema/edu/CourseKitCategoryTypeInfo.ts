import CourseKitCategory from "./CourseKitCategory";
import CourseKitCategoryValidators from "./CourseKitCategoryValidators";
import _CourseKitCategory_stuff_TypeInfo from "./_CourseKitCategory_stuff_TypeInfo";

export class CourseKitCategoryTypeInfo extends _CourseKitCategory_stuff_TypeInfo {

    readonly validators = new CourseKitCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseKitCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new CourseKitCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new CourseKitCategoryTypeInfo();

}

export default CourseKitCategoryTypeInfo;
