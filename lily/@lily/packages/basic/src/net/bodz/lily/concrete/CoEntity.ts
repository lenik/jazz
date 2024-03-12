import { int } from '@skeljs/core/src/lang/basetype';
import CoObject from './CoObject';
import CoEntityTypeInfo from './CoEntityTypeInfo';

export abstract class CoEntity<Id> extends CoObject {

    static readonly TYPE = CoEntityTypeInfo.INSTANCE;

    constructor(o: any) {
        super(o);
    }
}

export default CoEntity;