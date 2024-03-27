import DiaryReviewVoteValidators from "./DiaryReviewVoteValidators";
import _DiaryReviewVote_stuff_TypeInfo from "./_DiaryReviewVote_stuff_TypeInfo";

export class DiaryReviewVoteTypeInfo extends _DiaryReviewVote_stuff_TypeInfo {

    readonly validators = new DiaryReviewVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryReviewVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryReviewVoteTypeInfo();

}

export default DiaryReviewVoteTypeInfo;