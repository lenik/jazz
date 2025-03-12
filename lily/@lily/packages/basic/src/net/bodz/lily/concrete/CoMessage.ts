import type { int } from 'skel01-core/src/lang/basetype';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoEvent from './CoEvent';
import User from '../schema/account/User';
import FormDef from '../schema//meta/FormDef';
import { OffsetDateTime } from "skel01-core/src/lang/time";
// import UserClickInfo from '../schema/account/UserClickInfo';

export abstract class CoMessage<Id> extends CoEvent<Id> {

    static _typeInfo: CoMessageTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoMessageTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    op: User
    subject: string
    rawText: string
    form: FormDef
    parameters: any // map
    clickInfo: any // UserClickInfo

    sentTime?: OffsetDateTime
    receivedTime?: OffsetDateTime

    constructor(o: any) {
        super(o);
    }
}

export default CoMessage;