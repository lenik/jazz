import TestQuestionTalkTypeInfo from "./TestQuestionTalkTypeInfo";
import _TestQuestionTalk_stuff from "./_TestQuestionTalk_stuff";

export class TestQuestionTalk extends _TestQuestionTalk_stuff {

    static _typeInfo: TestQuestionTalkTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestQuestionTalkTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default TestQuestionTalk;
