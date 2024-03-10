import type { JsonVariant } from "@skeljs/core/src/lang/bas-type";
import type { int } from "@skeljs/core/src/lang/basetype";

import CoEntity from "../../concrete/CoEntity";
import type User from "./User";
import _UserSecret_stuff_TypeInfo from "./_UserSecret_stuff_TypeInfo";

export class _UserSecret_stuff extends CoEntity<int> {

    static _typeInfo: _UserSecret_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _UserSecret_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    id: int;
    properties?: JsonVariant;
    password: string;
    question?: string;
    answer?: string;

    user: User;
    userId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserSecret_stuff;
