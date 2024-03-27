import CourseCategory from "./CourseCategory";
import CourseCategoryValidators from "./CourseCategoryValidators";
import _CourseCategory_stuff_TypeInfo from "./_CourseCategory_stuff_TypeInfo";

export class CourseCategoryTypeInfo extends _CourseCategory_stuff_TypeInfo {

    readonly validators = new CourseCategoryValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseCategory"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new CourseCategory();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new CourseCategoryTypeInfo();

}

export default CourseCategoryTypeInfo;
