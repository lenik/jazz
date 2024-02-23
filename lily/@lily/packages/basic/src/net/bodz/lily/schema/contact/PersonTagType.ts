import _PersonTagType_stuff from "./_PersonTagType_stuff";
import { _PersonTagType_stuff_Type } from "./_PersonTagType_stuff_Type";

export class PersonTagType extends _PersonTagType_stuff<PersonTagType> {
    static TYPE = new _PersonTagType_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonTagType;
