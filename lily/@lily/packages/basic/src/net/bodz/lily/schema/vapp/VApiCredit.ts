import VApiCreditTypeInfo from "./VApiCreditTypeInfo";
import _VApiCredit_stuff from "./_VApiCredit_stuff";

export class VApiCredit extends _VApiCredit_stuff {

    static _typeInfo: VApiCreditTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = VApiCreditTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default VApiCredit;
