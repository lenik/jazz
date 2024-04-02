import DiaryVote from "./DiaryVote";
import DiaryVoteValidators from "./DiaryVoteValidators";
import _DiaryVote_stuff_TypeInfo from "./_DiaryVote_stuff_TypeInfo";

export class DiaryVoteTypeInfo extends _DiaryVote_stuff_TypeInfo {

    readonly validators = new DiaryVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryVote"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new DiaryVote();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryVoteTypeInfo();

}

export default DiaryVoteTypeInfo;

export const DiaryVote_TYPE = DiaryVoteTypeInfo.INSTANCE;
