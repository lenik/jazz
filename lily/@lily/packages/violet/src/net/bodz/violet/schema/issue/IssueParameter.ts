import IssueParameterTypeInfo from "./IssueParameterTypeInfo";
import _IssueParameter_stuff from "./_IssueParameter_stuff";

export class IssueParameter extends _IssueParameter_stuff<IssueParameter> {

    static _typeInfo: IssueParameterTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = IssueParameterTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default IssueParameter;
