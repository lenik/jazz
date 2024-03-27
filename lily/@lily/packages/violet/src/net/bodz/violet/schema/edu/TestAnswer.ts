import TestAnswerTypeInfo from "./TestAnswerTypeInfo";
import _TestAnswer_stuff from "./_TestAnswer_stuff";

export class TestAnswer extends _TestAnswer_stuff {

    static _typeInfo: TestAnswerTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestAnswerTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default TestAnswer;
