import moment from "moment-timezone";
import { Moment } from "moment-timezone";

import { int } from '@skeljs/core/src/lang/basetype';
import StructRowTypeInfo from './StructRowTypeInfo';
import { ZonedDateTime } from "@skeljs/core/src/lang/time";

export abstract class StructRow {

    static readonly TYPE: any = StructRowTypeInfo.INSTANCE;

    // content

    creationDate: ZonedDateTime = ZonedDateTime.now()
    lastModified: ZonedDateTime = ZonedDateTime.now()
    version: int = 0

    constructor(o: any) {
        if (o != null)
            Object.assign(this, o);
    }
}

export default StructRow;
