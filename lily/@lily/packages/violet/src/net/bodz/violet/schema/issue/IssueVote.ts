import IssueVoteTypeInfo from "./IssueVoteTypeInfo";
import _IssueVote_stuff from "./_IssueVote_stuff";

export class IssueVote extends _IssueVote_stuff {

    static _typeInfo: IssueVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = IssueVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default IssueVote;
