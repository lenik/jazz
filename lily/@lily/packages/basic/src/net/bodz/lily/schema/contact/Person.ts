import PersonTypeInfo from "./PersonTypeInfo";
import _Person_stuff from "./_Person_stuff";

export class Person extends _Person_stuff {
    static TYPE = new PersonTypeInfo();

    hello?: string
    peers?: string[]

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Person;
