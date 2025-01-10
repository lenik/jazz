import { int } from 'skel01-core/src/lang/basetype';
import CoImaged from '../../concrete/CoImaged';
import PartyTypeInfo from "./PartyTypeInfo";
import Contact from "./Contact";
import { LocalDate } from 'skel01-core/src/lang/time';

export abstract class Party extends CoImaged<int> {

    static _typeInfo: PartyTypeInfo;
    static get TYPE() {
        if (this._typeInfo == null)
            this._typeInfo = PartyTypeInfo.INSTANCE;
        return this._typeInfo;
    }

    category?: any
    private _categoryId?: int
    get categoryId() {
        return this.category != null ? this.category.id : this._categoryId;
    }
    set categoryId(val: int | undefined) {
        this._categoryId = val;
    }

    birthday?: LocalDate

    locale: string
    timeZoneId: string

    peer: boolean = false
    customer: boolean = false
    supplier: boolean = false

    tags: string[]

    subject?: string
    contacts: Contact[]

    bank?: string
    account?: string

    constructor(o: any) {
        super(o);
    }
}

export default Party;