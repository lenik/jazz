import ArticleBackrefTypeInfo from "./ArticleBackrefTypeInfo";
import _ArticleBackref_stuff from "./_ArticleBackref_stuff";

export class ArticleBackref extends _ArticleBackref_stuff {

    static _typeInfo: ArticleBackrefTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = ArticleBackrefTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleBackref;
