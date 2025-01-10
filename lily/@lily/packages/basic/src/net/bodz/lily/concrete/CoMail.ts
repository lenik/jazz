import { Moment } from "moment-timezone";
import { int, long } from 'skel01-core/src/lang/basetype';
import CoMailTypeInfo from './CoMailTypeInfo';
import CoMessage from './CoMessage';
import User from '../schema/account/User';

export abstract class CoMail extends CoMessage<long> {

    static _typeInfo: CoMailTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = CoMailTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    recipient?: User
    recipients: User[] = []
    bcc: User[] = []

    read: boolean

    constructor(o: any) {
        super(o);
    }
}

export default CoMail;