import PolicyTypeInfo from "./PolicyTypeInfo";
import _Policy_stuff from "./_Policy_stuff";

export class Policy extends _Policy_stuff {
    static TYPE = new PolicyTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Policy;
