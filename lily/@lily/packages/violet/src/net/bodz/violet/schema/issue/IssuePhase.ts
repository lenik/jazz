import IssuePhaseTypeInfo from "./IssuePhaseTypeInfo";
import _IssuePhase_stuff from "./_IssuePhase_stuff";

export class IssuePhase extends _IssuePhase_stuff {

    static _typeInfo: IssuePhaseTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = IssuePhaseTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default IssuePhase;
