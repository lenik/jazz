import TestApplyItemTypeInfo from "./TestApplyItemTypeInfo";
import _TestApplyItem_stuff from "./_TestApplyItem_stuff";

export class TestApplyItem extends _TestApplyItem_stuff {

    static _typeInfo: TestApplyItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestApplyItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default TestApplyItem;
