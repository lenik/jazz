import _Person_stuff from "./_Person_stuff";
import { _Person_stuffTypeInfo } from "./_Person_stuffTypeInfo";

export class Person extends _Person_stuff {
    static TYPE = new _Person_stuffTypeInfo();

    hello?: string
    peers?: string[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Person;
