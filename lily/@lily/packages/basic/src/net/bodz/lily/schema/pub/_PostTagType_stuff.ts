import CoTag from "../../concrete/CoTag";
import _PostTagType_stuff_TypeInfo from "./_PostTagType_stuff_TypeInfo";

export class _PostTagType_stuff<this_t> extends CoTag<this_t> {
    static TYPE = new _PostTagType_stuff_TypeInfo();


    constructor(o: any) {
        super(o);
    }
}

export default _PostTagType_stuff;
