import VAppTypeInfo from "./VAppTypeInfo";
import _VApp_stuff from "./_VApp_stuff";

export class VApp extends _VApp_stuff {
    static TYPE = new VAppTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApp;
