import CoTag from "../../concrete/CoTag";
import _PersonTagType_stuff_TypeInfo from "./_PersonTagType_stuff_TypeInfo";

export class _PersonTagType_stuff<this_t> extends CoTag<this_t> {
    static TYPE = new _PersonTagType_stuff_TypeInfo();


    constructor(o: any) {
        super(o);
    }
}

export default _PersonTagType_stuff;
