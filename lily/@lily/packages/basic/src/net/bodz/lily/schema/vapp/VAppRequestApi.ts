import VAppRequestApiTypeInfo from "./VAppRequestApiTypeInfo";
import _VAppRequestApi_stuff from "./_VAppRequestApi_stuff";

export class VAppRequestApi extends _VAppRequestApi_stuff {
    static TYPE = new VAppRequestApiTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppRequestApi;
