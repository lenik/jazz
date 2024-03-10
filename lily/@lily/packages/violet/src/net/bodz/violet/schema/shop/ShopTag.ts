import ShopTagTypeInfo from "./ShopTagTypeInfo";
import _ShopTag_stuff from "./_ShopTag_stuff";

export class ShopTag extends _ShopTag_stuff<ShopTag> {

    static _typeInfo: ShopTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ShopTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ShopTag;
