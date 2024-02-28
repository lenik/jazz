import ArticleTagTypeInfo from "./ArticleTagTypeInfo";
import _ArticleTag_stuff from "./_ArticleTag_stuff";

export class ArticleTag extends _ArticleTag_stuff {
    static _typeInfo: ArticleTagTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new ArticleTagTypeInfo();
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTag;
