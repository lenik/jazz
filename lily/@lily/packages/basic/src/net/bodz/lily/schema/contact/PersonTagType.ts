import PersonTagTypeTypeInfo from "./PersonTagTypeTypeInfo";
import _PersonTagType_stuff from "./_PersonTagType_stuff";

export class PersonTagType extends _PersonTagType_stuff<PersonTagType> {
    static _typeInfo: PersonTagTypeTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PersonTagTypeTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonTagType;
