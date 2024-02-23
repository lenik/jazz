import _Article_stuff from "./_Article_stuff";
import { _Article_stuff_Type } from "./_Article_stuff_Type";

export class Article extends _Article_stuff {
    static TYPE = new _Article_stuff_Type();

    constructor(o: any) {
        super(o);
        if (o != null) Object.assign(this, o);
    }
}

export default Article;
