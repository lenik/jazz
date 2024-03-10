import type { BigDecimal } from "@skeljs/core/src/lang/basetype";

import ShopItemTypeInfo from "./ShopItemTypeInfo";
import _ShopItem_stuff from "./_ShopItem_stuff";

export class ShopItem extends _ShopItem_stuff {

    static _typeInfo: ShopItemTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ShopItemTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    total?: BigDecimal

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ShopItem;
