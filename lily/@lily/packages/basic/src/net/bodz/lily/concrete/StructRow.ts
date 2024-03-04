import moment from "moment-timezone";
import { Moment } from "moment-timezone";

import { int } from '@skeljs/core/src/lang/basetype';
import StructRowTypeInfo from './StructRowTypeInfo';

export abstract class StructRow {
    static TYPE: any = new StructRowTypeInfo();

    // content

    creationDate: Moment = moment()
    lastModifiedDate: Moment = moment()
    version: int = 0

    constructor(o: any) {
        if (o != null)
            Object.assign(this, o);
    }
}

export default StructRow;
