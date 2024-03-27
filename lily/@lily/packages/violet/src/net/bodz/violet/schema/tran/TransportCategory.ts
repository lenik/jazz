import TransportCategoryTypeInfo from "./TransportCategoryTypeInfo";
import _TransportCategory_stuff from "./_TransportCategory_stuff";

export class TransportCategory extends _TransportCategory_stuff<TransportCategory> {

    static _typeInfo: TransportCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = TransportCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default TransportCategory;
