import TestQuestionTypeInfo from "./TestQuestionTypeInfo";
import _TestQuestion_stuff from "./_TestQuestion_stuff";

export class TestQuestion extends _TestQuestion_stuff {

    static _typeInfo: TestQuestionTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestQuestionTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TestQuestion;
