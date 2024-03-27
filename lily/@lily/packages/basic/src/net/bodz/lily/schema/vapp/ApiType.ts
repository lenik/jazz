import ApiTypeTypeInfo from "./ApiTypeTypeInfo";
import _ApiType_stuff from "./_ApiType_stuff";

export class ApiType extends _ApiType_stuff {

    static _typeInfo: ApiTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ApiTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default ApiType;
