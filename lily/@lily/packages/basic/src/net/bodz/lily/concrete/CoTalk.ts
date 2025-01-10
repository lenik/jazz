import { Moment } from "moment-timezone";
import { int, long } from 'skel01-core/src/lang/basetype';
import CoTalkTypeInfo from './CoTalkTypeInfo';
import CoMessage from './CoMessage';

export abstract class CoTalk<This> extends CoMessage<long> {

    static _typeInfo: CoTalkTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoTalkTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    parent?: This
    parentId?: long

    constructor(o: any) {
        super(o);
    }
}

export default CoTalk;