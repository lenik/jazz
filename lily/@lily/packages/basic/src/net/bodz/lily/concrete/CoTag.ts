import { int } from '@skeljs/core/src/lang/basetype';
import CoCode from './CoCode';
import CoTagTypeInfo from './CoTagTypeInfo';

export abstract class CoTag<This> extends CoCode<This> {

    static readonly TYPE = CoTagTypeInfo.INSTANCE;

    constructor(o: any) {
        super(o);
    }

}

export default CoTag;