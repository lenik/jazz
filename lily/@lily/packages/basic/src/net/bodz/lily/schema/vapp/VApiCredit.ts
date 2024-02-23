import _VApiCredit_stuff from "./_VApiCredit_stuff";
import { _VApiCredit_stuffTypeInfo } from "./_VApiCredit_stuffTypeInfo";

export class VApiCredit extends _VApiCredit_stuff {
    static TYPE = new _VApiCredit_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApiCredit;
