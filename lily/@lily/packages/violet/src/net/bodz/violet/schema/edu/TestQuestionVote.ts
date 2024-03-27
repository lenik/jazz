import TestQuestionVoteTypeInfo from "./TestQuestionVoteTypeInfo";
import _TestQuestionVote_stuff from "./_TestQuestionVote_stuff";

export class TestQuestionVote extends _TestQuestionVote_stuff {

    static _typeInfo: TestQuestionVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestQuestionVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default TestQuestionVote;
