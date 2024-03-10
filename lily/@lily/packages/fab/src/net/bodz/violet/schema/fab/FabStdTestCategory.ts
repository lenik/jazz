import FabStdTestCategoryTypeInfo from "./FabStdTestCategoryTypeInfo";
import _FabStdTestCategory_stuff from "./_FabStdTestCategory_stuff";

export class FabStdTestCategory extends _FabStdTestCategory_stuff<FabStdTestCategory> {

    static _typeInfo: FabStdTestCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = FabStdTestCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default FabStdTestCategory;
