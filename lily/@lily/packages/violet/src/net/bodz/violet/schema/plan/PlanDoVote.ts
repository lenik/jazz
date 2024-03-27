import PlanDoVoteTypeInfo from "./PlanDoVoteTypeInfo";
import _PlanDoVote_stuff from "./_PlanDoVote_stuff";

export class PlanDoVote extends _PlanDoVote_stuff {

    static _typeInfo: PlanDoVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanDoVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PlanDoVote;
