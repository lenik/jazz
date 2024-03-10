import IssueTagTypeInfo from "./IssueTagTypeInfo";
import _IssueTag_stuff from "./_IssueTag_stuff";

export class IssueTag extends _IssueTag_stuff<IssueTag> {

    static _typeInfo: IssueTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = IssueTagTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default IssueTag;
