import _ApiType_stuff from "./_ApiType_stuff";
import { _ApiType_stuffTypeInfo } from "./_ApiType_stuffTypeInfo";

export class ApiType extends _ApiType_stuff {
    static TYPE = new _ApiType_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ApiType;
