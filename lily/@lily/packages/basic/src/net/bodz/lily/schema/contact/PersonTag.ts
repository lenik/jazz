import _PersonTag_stuff from "./_PersonTag_stuff";
import { _PersonTag_stuff_Type } from "./_PersonTag_stuff_Type";

export class PersonTag extends _PersonTag_stuff {
    static TYPE = new _PersonTag_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default PersonTag;
