import VApiTypeInfo from "./VApiTypeInfo";
import _VApi_stuff from "./_VApi_stuff";

export class VApi extends _VApi_stuff {
    static TYPE = new VApiTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApi;
