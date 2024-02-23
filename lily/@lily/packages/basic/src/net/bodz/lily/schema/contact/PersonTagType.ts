import _PersonTagType_stuff from "./_PersonTagType_stuff";
import { _PersonTagType_stuffTypeInfo } from "./_PersonTagType_stuffTypeInfo";

export class PersonTagType extends _PersonTagType_stuff<PersonTagType> {
    static TYPE = new _PersonTagType_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonTagType;
