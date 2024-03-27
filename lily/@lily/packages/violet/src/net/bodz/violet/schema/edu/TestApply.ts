import TestApplyTypeInfo from "./TestApplyTypeInfo";
import _TestApply_stuff from "./_TestApply_stuff";

export class TestApply extends _TestApply_stuff {

    static _typeInfo: TestApplyTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestApplyTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default TestApply;
