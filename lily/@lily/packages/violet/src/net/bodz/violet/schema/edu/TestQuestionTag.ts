import TestQuestionTagTypeInfo from "./TestQuestionTagTypeInfo";
import _TestQuestionTag_stuff from "./_TestQuestionTag_stuff";

export class TestQuestionTag extends _TestQuestionTag_stuff<TestQuestionTag> {

    static _typeInfo: TestQuestionTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestQuestionTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TestQuestionTag;
