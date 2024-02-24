import PersonTagTypeTypeInfo from "./PersonTagTypeTypeInfo";
import _PersonTagType_stuff from "./_PersonTagType_stuff";

export class PersonTagType extends _PersonTagType_stuff<PersonTagType> {
    static TYPE = new PersonTagTypeTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonTagType;
