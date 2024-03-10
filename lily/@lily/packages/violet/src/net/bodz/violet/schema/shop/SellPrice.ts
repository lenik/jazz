import SellPriceTypeInfo from "./SellPriceTypeInfo";
import _SellPrice_stuff from "./_SellPrice_stuff";

export class SellPrice extends _SellPrice_stuff {

    static _typeInfo: SellPriceTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = SellPriceTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default SellPrice;
