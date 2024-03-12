import { Moment } from 'moment-timezone';
import IdEntityValidators from "../../concrete/IdEntityValidators";
import type PartyTypeInfo from './PartyTypeInfo';
import type PartyCategory from "./PartyCategory";
import type Contact from "./Contact";
import ContactTypeInfo from './ContactTypeInfo';

export class ContactValidators extends IdEntityValidators {

    constructor(type: ContactTypeInfo) {
        super(type);
    }

    validateOrg(val: any) {

    }

    validateOrgUnit(val: any) {

    }

    validatePerson(val: any) {

    }

    validateRename(val: string) {

    }

    validateUsage(val: string) {

    }

    validateZone(val: any) {

    }

    validateCountry(val: string) {

    }

    validateR1(val: string) {

    }

    validateR2(val: string) {

    }

    validateR3(val: string) {

    }

    validateR4(val: string) {

    }

    validateAddress1(val: string) {

    }

    validateAddress2(val: string) {

    }

    validatePostalCode(val: string) {

    }

    validateTel(val: string) {

    }

    validateMobile(val: string) {

    }

    validateFax(val: string) {

    }

    validateEmail(val: string) {

    }

    validateWeb(val: string) {

    }

    validateQq(val: string) {

    }

    validateWechat(val: string) {

    }

}


export default ContactValidators;
