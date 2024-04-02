import CourseKitFav from "./CourseKitFav";
import CourseKitFavValidators from "./CourseKitFavValidators";
import _CourseKitFav_stuff_TypeInfo from "./_CourseKitFav_stuff_TypeInfo";

export class CourseKitFavTypeInfo extends _CourseKitFav_stuff_TypeInfo {

    readonly validators = new CourseKitFavValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseKitFav"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new CourseKitFav();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new CourseKitFavTypeInfo();

}

export default CourseKitFavTypeInfo;

export const CourseKitFav_TYPE = CourseKitFavTypeInfo.INSTANCE;
