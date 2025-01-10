import type { int } from "skel01-core/src/lang/basetype";
import CoImaged from "@lily/basic/src/net/bodz/lily/concrete/CoImaged";

import type FabStdTest from "./FabStdTest";
import type FabStdTestCategory from "./FabStdTestCategory";
import _FabStdTest_stuff_TypeInfo from "./_FabStdTest_stuff_TypeInfo";

export class _FabStdTest_stuff extends CoImaged<int> {

    static _typeInfo: _FabStdTest_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _FabStdTest_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    code?: string;
    depth: int;
    refCount: int;

    category: FabStdTestCategory;
    categoryId: int;

    parent?: FabStdTest;
    parentId?: int;

    constructor(o: any) {
        super(o);
    }
}

export default _FabStdTest_stuff;
