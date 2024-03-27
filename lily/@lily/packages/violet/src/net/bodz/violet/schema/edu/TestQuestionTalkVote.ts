import TestQuestionTalkVoteTypeInfo from "./TestQuestionTalkVoteTypeInfo";
import _TestQuestionTalkVote_stuff from "./_TestQuestionTalkVote_stuff";

export class TestQuestionTalkVote extends _TestQuestionTalkVote_stuff {

    static _typeInfo: TestQuestionTalkVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestQuestionTalkVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default TestQuestionTalkVote;
