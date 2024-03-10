import PlanVoteTypeInfo from "./PlanVoteTypeInfo";
import _PlanVote_stuff from "./_PlanVote_stuff";

export class PlanVote extends _PlanVote_stuff {

    static _typeInfo: PlanVoteTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanVoteTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PlanVote;
