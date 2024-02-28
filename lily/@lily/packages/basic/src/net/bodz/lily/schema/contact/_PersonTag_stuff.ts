import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type Person from "./Person";
import type PersonTagType from "./PersonTagType";
import _PersonTag_stuff_TypeInfo from "./_PersonTag_stuff_TypeInfo";

export class _PersonTag_stuff extends CoEntity<int> {
    static _typeInfo: _PersonTag_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new _PersonTag_stuff_TypeInfo();
        return this._typeInfo;
    }

    id: int;

    tag: PersonTagType;
    tagId: int;

    person: Person;
    personId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PersonTag_stuff;
