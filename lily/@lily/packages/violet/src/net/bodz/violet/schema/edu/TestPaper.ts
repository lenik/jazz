import TestPaperTypeInfo from "./TestPaperTypeInfo";
import _TestPaper_stuff from "./_TestPaper_stuff";

export class TestPaper extends _TestPaper_stuff {

    static _typeInfo: TestPaperTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TestPaperTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default TestPaper;
