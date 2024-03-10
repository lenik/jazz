import PlanPartyTypeInfo from "./PlanPartyTypeInfo";
import _PlanParty_stuff from "./_PlanParty_stuff";

export class PlanParty extends _PlanParty_stuff {

    static _typeInfo: PlanPartyTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PlanPartyTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PlanParty;
