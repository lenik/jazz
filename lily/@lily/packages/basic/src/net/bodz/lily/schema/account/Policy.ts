import _Policy_stuff from "./_Policy_stuff";
import { _Policy_stuffTypeInfo } from "./_Policy_stuffTypeInfo";

export class Policy extends _Policy_stuff {
    static TYPE = new _Policy_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Policy;
