import _Person_stuff from "./_Person_stuff";
import { _Person_stuff_Type } from "./_Person_stuff_Type";

export class Person extends _Person_stuff {
    static TYPE = new _Person_stuff_Type();

    hello?: string
    peers?: string[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Person;
