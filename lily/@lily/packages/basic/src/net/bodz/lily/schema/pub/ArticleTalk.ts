import ArticleTalkTypeInfo from "./ArticleTalkTypeInfo";
import _ArticleTalk_stuff from "./_ArticleTalk_stuff";

export class ArticleTalk extends _ArticleTalk_stuff<ArticleTalk> {
    static _typeInfo: ArticleTalkTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = new ArticleTalkTypeInfo(); 
        return this._typeInfo;
    }


    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default ArticleTalk;
