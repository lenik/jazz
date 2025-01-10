import type { int } from "skel01-core/src/lang/basetype";

import IdEntity from "../../concrete/IdEntity";
import type Person from "./Person";
import type PersonTagType from "./PersonTagType";
import _PersonTag_stuff_TypeInfo from "./_PersonTag_stuff_TypeInfo";

export class _PersonTag_stuff extends IdEntity<int> {

    static _typeInfo: _PersonTag_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PersonTag_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    tag: PersonTagType;
    tagId: int;

    person: Person;
    personId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PersonTag_stuff;
