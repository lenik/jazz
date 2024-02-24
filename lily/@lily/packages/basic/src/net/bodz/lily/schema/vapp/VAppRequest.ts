import VAppRequestTypeInfo from "./VAppRequestTypeInfo";
import _VAppRequest_stuff from "./_VAppRequest_stuff";

export class VAppRequest extends _VAppRequest_stuff {
    static TYPE = new VAppRequestTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VAppRequest;
