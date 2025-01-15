import type { JsonVariant } from "skel01-core/src/lang/bas-type";
import type { int } from "skel01-core/src/lang/basetype";
import type OrgUnit from "lily-basic/src/net/bodz/lily/schema/contact/OrgUnit";

import AbstractAsset from "./AbstractAsset";
import _OrgUnitAsset_stuff_TypeInfo from "./_OrgUnitAsset_stuff_TypeInfo";

export class _OrgUnitAsset_stuff extends AbstractAsset {

    static _typeInfo: _OrgUnitAsset_stuff_TypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = _OrgUnitAsset_stuff_TypeInfo.INSTANCE;
        return this._typeInfo;
    }

    batch?: JsonVariant;

    owner: OrgUnit;
    ownerId: int;

    constructor(o: any) {
        super(o);
    }
}

export default _OrgUnitAsset_stuff;
