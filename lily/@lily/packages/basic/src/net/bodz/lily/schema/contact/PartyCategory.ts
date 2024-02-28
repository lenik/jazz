import PartyCategoryTypeInfo from "./PartyCategoryTypeInfo";
import _PartyCategory_stuff from "./_PartyCategory_stuff";

export class PartyCategory extends _PartyCategory_stuff<PartyCategory> {
    static _typeInfo: PartyCategoryTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PartyCategoryTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PartyCategory;
