import CoTag from "@skeljs/dba/src/net/bodz/lily/concrete/CoTag";

import { TypeParamType } from "../../meta/TypeParamType";
import _ArticleTagType_stuff_TypeInfo from "./_ArticleTagType_stuff_TypeInfo";

export class _ArticleTagType_stuff<this_t> extends CoTag<this_t> {
    static TYPE = new _ArticleTagType_stuff_TypeInfo();


    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTagType_stuff;
