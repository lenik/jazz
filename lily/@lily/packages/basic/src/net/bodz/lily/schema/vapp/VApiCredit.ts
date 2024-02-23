import _VApiCredit_stuff from "./_VApiCredit_stuff";
import { _VApiCredit_stuff_Type } from "./_VApiCredit_stuff_Type";

export class VApiCredit extends _VApiCredit_stuff {
    static TYPE = new _VApiCredit_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApiCredit;
