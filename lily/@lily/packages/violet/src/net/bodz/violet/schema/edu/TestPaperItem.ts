import TestPaperItemTypeInfo from "./TestPaperItemTypeInfo";
import _TestPaperItem_stuff from "./_TestPaperItem_stuff";

export class TestPaperItem extends _TestPaperItem_stuff {

    static _typeInfo: TestPaperItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestPaperItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TestPaperItem;
