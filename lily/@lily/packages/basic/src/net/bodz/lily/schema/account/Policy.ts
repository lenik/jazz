import PolicyTypeInfo from "./PolicyTypeInfo";
import _Policy_stuff from "./_Policy_stuff";

export class Policy extends _Policy_stuff {

    static _typeInfo: PolicyTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PolicyTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default Policy;
