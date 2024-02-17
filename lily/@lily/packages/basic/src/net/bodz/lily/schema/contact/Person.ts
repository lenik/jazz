
import { * as validators } from "./PersonValidators";
import type { _Person_stuff } from "./_Person_stuff";

export class Person extends _Person_stuff {
    static TYPE = new PersonType();

    hello?: string

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
