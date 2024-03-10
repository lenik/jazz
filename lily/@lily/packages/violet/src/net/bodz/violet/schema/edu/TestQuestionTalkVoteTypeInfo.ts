import TestQuestionTalkVoteValidators from "./TestQuestionTalkVoteValidators";
import _TestQuestionTalkVote_stuff_TypeInfo from "./_TestQuestionTalkVote_stuff_TypeInfo";

export class TestQuestionTalkVoteTypeInfo extends _TestQuestionTalkVote_stuff_TypeInfo {

    readonly validators = new TestQuestionTalkVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestionTalkVote"; }
    get icon() { return "fa-tag"; }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestQuestionTalkVoteTypeInfo();

}

export default TestQuestionTalkVoteTypeInfo;
