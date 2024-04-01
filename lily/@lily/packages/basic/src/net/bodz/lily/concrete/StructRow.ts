import moment from "moment-timezone";
import { Moment } from "moment-timezone";

import { int } from '@skeljs/core/src/lang/basetype';
import StructRowTypeInfo from './StructRowTypeInfo';
import { OffsetDateTime } from "@skeljs/core/src/lang/time";

export class StructRow {

    static readonly TYPE: any = StructRowTypeInfo.INSTANCE;

    // content

    creationDate: OffsetDateTime = OffsetDateTime.now()
    lastModified: OffsetDateTime = OffsetDateTime.now()
    version: int = 0

    constructor(o?: any) {
        if (o != null)
            Object.assign(this, o);
    }
}

export default StructRow;
