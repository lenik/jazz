import { Moment } from "moment-timezone";
import { int } from '@skeljs/core/src/lang/basetype';
import CoMessageTypeInfo from './CoMessageTypeInfo';
import CoEvent from './CoEvent';
import User from '../schema/account/User';
import FormDef from '../schema//meta/FormDef';
// import UserClickInfo from '../schema/account/UserClickInfo';

export abstract class CoMessage<Id> extends CoEvent<Id> {

    static readonly TYPE = CoMessageTypeInfo.INSTANCE;

    op: User
    subject: string
    rawText: string
    form: FormDef
    parameters: any // map
    clickInfo: any // UserClickInfo

    sentTime?: Moment
    receivedTime?: Moment

    constructor(o: any) {
        super(o);
    }
}

export default CoMessage;