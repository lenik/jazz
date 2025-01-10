import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import type Group from "@lily/basic/src/net/bodz/lily/schema/account/Group";

import AbstractAsset from "./AbstractAsset";
import _GroupAsset_stuff_TypeInfo from "./_GroupAsset_stuff_TypeInfo";

export class _GroupAsset_stuff extends AbstractAsset {

    static _typeInfo: _GroupAsset_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _GroupAsset_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    batch?: JsonVariant;

    owner: Group;
    ownerId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _GroupAsset_stuff;
