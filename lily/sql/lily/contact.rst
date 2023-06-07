#\import lily.security

column-property {
    nbank:              bankCount
    nrole:              roleCount
    timezone:           timeZone
    taxid:              taxId
}

class-map {
    net.bodz.lily.template.CoCategory: \
        partycat
    net.bodz.lily.contact.Party: \
        org, \
        orgunit, \
        person
}

table-name {
    org:                net.bodz.lily.contact.Organization
    orgunit:            net.bodz.lily.contact.OrgUnit
    partycat:           net.bodz.lily.contact.PartyCategory
    person:             net.bodz.lily.contact.Person
    personrole:         net.bodz.lily.contact.PersonRole
}

table person {
    column alias {
        javaName: contact.rename
    }
    column ctprops {
        javaName: contact.properties
    }
    column address1  {
        javaName: contact.address1
    }
    column address2  {
        javaName: contact.address2
    }
    column zone {
        javaName: contact.zone
    }
    column tel {
        javaName: contact.tel
    }
    column telok {
        javaName: contact.telValidated
    }
    column email {
        javaName: contact.email
    }
    column emailok {
        javaName: contact.emailValidated
    }
}

table org {
    column alias {
        javaName: contact.rename
    }
    column ctprops {
        javaName: contact.properties
    }
    column address1  {
        javaName: contact.address1
    }
    column address2  {
        javaName: contact.address2
    }
    column zone {
        javaName: contact.zone
    }
    column tel {
        javaName: contact.tel
    }
    column telok {
        javaName: contact.telValidated
    }
    column email {
        javaName: contact.email
    }
    column emailok {
        javaName: contact.emailValidated
    }
}

table orgunit {
    column alias {
        javaName: contact.rename
    }
    column ctprops {
        javaName: contact.properties
    }
    column address1  {
        javaName: contact.address1
    }
    column address2  {
        javaName: contact.address2
    }
    column zone {
        javaName: contact.zone
    }
    column tel {
        javaName: contact.tel
    }
    column telok {
        javaName: contact.telValidated
    }
    column email {
        javaName: contact.email
    }
    column emailok {
        javaName: contact.emailValidated
    }
}

table personrole {
    column ou {
        javaName: orgUnit
    }
}
