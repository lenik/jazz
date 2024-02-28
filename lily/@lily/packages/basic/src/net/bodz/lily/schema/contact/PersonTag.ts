import PersonTagTypeInfo from "./PersonTagTypeInfo";
import _PersonTag_stuff from "./_PersonTag_stuff";

export class PersonTag extends _PersonTag_stuff {
    static _typeInfo: PersonTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new PersonTagTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonTag;
