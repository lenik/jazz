import CourseFav from "./CourseFav";
import CourseFavValidators from "./CourseFavValidators";
import _CourseFav_stuff_TypeInfo from "./_CourseFav_stuff_TypeInfo";

export class CourseFavTypeInfo extends _CourseFav_stuff_TypeInfo {

    readonly validators = new CourseFavValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.CourseFav"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new CourseFav();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new CourseFavTypeInfo();

}

export default CourseFavTypeInfo;

export const CourseFav_TYPE = CourseFavTypeInfo.INSTANCE;
