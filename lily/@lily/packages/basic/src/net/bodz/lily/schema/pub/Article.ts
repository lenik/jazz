import _Article_stuff from "./_Article_stuff";
import { _Article_stuffTypeInfo } from "./_Article_stuffTypeInfo";

export class Article extends _Article_stuff {
    static TYPE = new _Article_stuffTypeInfo();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Article;
