
import { * as validators } from "./PersonValidators";
import type { _VApiCredit_stuff } from "./_VApiCredit_stuff";

export class VApiCredit extends _VApiCredit_stuff {
    static TYPE = new VApiCreditType();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}
