import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import type Organization from "@lily/basic/src/net/bodz/lily/schema/contact/Organization";

import AbstractAsset from "./AbstractAsset";
import _OrgAsset_stuff_TypeInfo from "./_OrgAsset_stuff_TypeInfo";

export class _OrgAsset_stuff extends AbstractAsset {

    static _typeInfo: _OrgAsset_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _OrgAsset_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    batch?: JsonVariant;

    owner: Organization;
    ownerId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _OrgAsset_stuff;
