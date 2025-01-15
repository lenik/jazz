import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import type Person from "lily-basic/src/net/bodz/lily/schema/contact/Person";

import AbstractAsset from "./AbstractAsset";
import _PersonAsset_stuff_TypeInfo from "./_PersonAsset_stuff_TypeInfo";

export class _PersonAsset_stuff extends AbstractAsset {

    static _typeInfo: _PersonAsset_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _PersonAsset_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    batch?: JsonVariant;

    owner: Person;
    ownerId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _PersonAsset_stuff;
