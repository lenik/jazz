import { int } from '@skeljs/core/src/lang/basetype';
import CoObject from './CoObject';
import IdEntityTypeInfo from './IdEntityTypeInfo';

export abstract class IdEntity<Id> extends CoObject {

    id?: Id

    constructor(o: any) {
        super(o);
    }
}

export default IdEntity;