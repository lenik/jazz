import IssueCategoryTypeInfo from "./IssueCategoryTypeInfo";
import _IssueCategory_stuff from "./_IssueCategory_stuff";

export class IssueCategory extends _IssueCategory_stuff<IssueCategory> {

    static _typeInfo: IssueCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = IssueCategoryTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default IssueCategory;
