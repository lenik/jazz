import PersonTagTypeTypeInfo from "./PersonTagTypeTypeInfo";
import _PersonTagType_stuff from "./_PersonTagType_stuff";

export class PersonTagType extends _PersonTagType_stuff<PersonTagType> {

    static _typeInfo: PersonTagTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PersonTagTypeTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o?: any) {
        super(o);
    }
}

export default PersonTagType;
