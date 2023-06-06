#\import lily.security

column-property {
    ctprops:            contactProperties
    nbank:              bankCount
    nrole:              roleCount
}

composite contact net.bodz.lily.contact.Contact {
    
}

class-map {
    net.bodz.lily.template.CoCategory: \
        partycat
    net.bodz.lily.contact.Party: \
        org,
        person
}

table-name {
    org:                net.bodz.lily.contact.Organization
    orgunit:            net.bodz.lily.contact.OrgUnit
    partycat:           net.bodz.lily.contact.PartyCategory
    person:             net.bodz.lily.contact.Person
    personrole:         net.bodz.lily.contact.PersonRole
}
