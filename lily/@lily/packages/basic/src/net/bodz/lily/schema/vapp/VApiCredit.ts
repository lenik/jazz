import VApiCreditTypeInfo from "./VApiCreditTypeInfo";
import _VApiCredit_stuff from "./_VApiCredit_stuff";

export class VApiCredit extends _VApiCredit_stuff {
    static TYPE = new VApiCreditTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default VApiCredit;
