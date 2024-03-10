import DiaryVoteValidators from "./DiaryVoteValidators";
import _DiaryVote_stuff_TypeInfo from "./_DiaryVote_stuff_TypeInfo";

export class DiaryVoteTypeInfo extends _DiaryVote_stuff_TypeInfo {

    readonly validators = new DiaryVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.plan.DiaryVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new DiaryVoteTypeInfo();

}

export default DiaryVoteTypeInfo;
