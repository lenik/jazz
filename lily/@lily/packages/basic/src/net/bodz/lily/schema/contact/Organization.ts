import OrganizationTypeInfo from "./OrganizationTypeInfo";
import _Organization_stuff from "./_Organization_stuff";

export class Organization extends _Organization_stuff {
    static TYPE = new OrganizationTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Organization;
