import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type Zone from "./Zone";
import type ZoneCategory from "./ZoneCategory";
import _Zone_stuff_TypeInfo from "./_Zone_stuff_TypeInfo";

export class _Zone_stuff extends CoEntity<int> {
    static _typeInfo: _Zone_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _Zone_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;
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
