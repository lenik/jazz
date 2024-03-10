import IssueTypeInfo from "./IssueTypeInfo";
import _Issue_stuff from "./_Issue_stuff";

export class Issue extends _Issue_stuff {

    static _typeInfo: IssueTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = IssueTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Issue;
