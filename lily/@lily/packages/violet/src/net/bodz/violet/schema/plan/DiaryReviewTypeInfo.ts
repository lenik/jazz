import DiaryReview from "./DiaryReview";
import DiaryReviewValidators from "./DiaryReviewValidators";
import _DiaryReview_stuff_TypeInfo from "./_DiaryReview_stuff_TypeInfo";

export class DiaryReviewTypeInfo extends _DiaryReview_stuff_TypeInfo {

    readonly validators = new DiaryReviewValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryReview"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new DiaryReview();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryReviewTypeInfo();

}

export default DiaryReviewTypeInfo;

export const DiaryReview_TYPE = DiaryReviewTypeInfo.INSTANCE;
