import _ApiType_stuff from "./_ApiType_stuff";
import { _ApiType_stuff_Type } from "./_ApiType_stuff_Type";

export class ApiType extends _ApiType_stuff {
    static TYPE = new _ApiType_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ApiType;
