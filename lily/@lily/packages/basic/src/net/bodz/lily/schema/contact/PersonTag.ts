import PersonTagTypeInfo from "./PersonTagTypeInfo";
import _PersonTag_stuff from "./_PersonTag_stuff";

export class PersonTag extends _PersonTag_stuff {
    static TYPE = new PersonTagTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonTag;
