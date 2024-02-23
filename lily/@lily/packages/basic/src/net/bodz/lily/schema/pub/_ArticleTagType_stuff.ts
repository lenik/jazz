import CoTag from "@skeljs/dba/src/net/bodz/lily/concrete/CoTag";

import { TypeParamType } from "../../meta/TypeParamType";
import _ArticleTagType_stuff_Type from "./_ArticleTagType_stuff_Type";

export class _ArticleTagType_stuff<this_t> extends CoTag<this_t> {
    static TYPE = new _ArticleTagType_stuff_Type();


    constructor(o: any) {
        super(o);
    }
}

export default _ArticleTagType_stuff;
