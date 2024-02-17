
import { * as validators } from "./PersonValidators";
import type { _PersonTagType_stuff } from "./_PersonTagType_stuff";

export class PersonTagType extends _PersonTagType_stuff<PersonTagType> {
    static TYPE = new PersonTagTypeType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
