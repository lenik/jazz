import TestQuestionVote from "./TestQuestionVote";
import TestQuestionVoteValidators from "./TestQuestionVoteValidators";
import _TestQuestionVote_stuff_TypeInfo from "./_TestQuestionVote_stuff_TypeInfo";

export class TestQuestionVoteTypeInfo extends _TestQuestionVote_stuff_TypeInfo {

    readonly validators = new TestQuestionVoteValidators(this);

    constructor() {
        super();
    }

    get name() { return "net.bodz.violet.schema.edu.TestQuestionVote"; }
    get icon() { return "fa-tag"; }

    override create() {
        return new TestQuestionVote();
    }

    override preamble() {
        super.preamble();
        this.declare({
        });
    }

    static readonly INSTANCE = new TestQuestionVoteTypeInfo();

}

export default TestQuestionVoteTypeInfo;
