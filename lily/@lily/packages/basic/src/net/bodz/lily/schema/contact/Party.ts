import { int } from '@skeljs/core/src/lang/basetype';
import CoImaged from '../../concrete/CoImaged';
import PartyTypeInfo from "./PartyTypeInfo";
import Contact from "./Contact";
import { LocalDate } from '@skeljs/core/src/lang/time';

export abstract class Party extends CoImaged<int> {

    static readonly TYPE = PartyTypeInfo.INSTANCE;

    category?: any
    private _categoryId?: int
    get categoryId() {
        return this.category != null ? this.category.id : this.categoryId;
    }
    set categoryId(val: int | undefined) {
        this.categoryId = val;
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
        if (o != null) Object.assign(this, o);
    }
}

export default Party;