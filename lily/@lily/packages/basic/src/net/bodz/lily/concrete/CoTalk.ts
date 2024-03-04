import { Moment } from "moment-timezone";
import { int, long } from '@skeljs/core/src/lang/basetype';
import CoTalkTypeInfo from './CoTalkTypeInfo';
import CoMessage from './CoMessage';

export abstract class CoTalk<This> extends CoMessage<long> {
    static TYPE = new CoTalkTypeInfo();

    parent?: This
    parentId?: long

    constructor(o: any) {
        super(o);
    }
}

export default CoTalk;