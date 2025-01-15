import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import type User from "lily-basic/src/net/bodz/lily/schema/account/User";

import AbstractAsset from "./AbstractAsset";
import _UserAsset_stuff_TypeInfo from "./_UserAsset_stuff_TypeInfo";

export class _UserAsset_stuff extends AbstractAsset {

    static _typeInfo: _UserAsset_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _UserAsset_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    batch?: JsonVariant;

    owner: User;
    ownerId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _UserAsset_stuff;
