import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";

import CoImaged from "../../concrete/CoImaged";
import type Zone from "./Zone";
import type ZoneCategory from "./ZoneCategory";
import _Zone_stuff_TypeInfo from "./_Zone_stuff_TypeInfo";

export class _Zone_stuff extends CoImaged<int> {

    static _typeInfo: _Zone_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _Zone_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    code?: string;
    country?: string;
    depth: int;
    telCode?: string;
    postCode?: string;
    data?: JsonVariant;

    parent?: Zone;
    parentId?: int;

    category: ZoneCategory;
    categoryId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _Zone_stuff;
