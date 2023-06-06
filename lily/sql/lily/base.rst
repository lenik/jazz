column-property {
    creation:           creationDate
    lastmod:            lastModifiedDate
    props:              properties
    t0:                 beginTime
    t1:                 endTime
    code:               codeName
    imagealt:           imageAlt
    nref:               refCount
    formargs:           formArguments
    text:               rawText
    nobj:               objCount
    votes:              voteScore
    nvote:              voteCount
    nfav:               favCount
    nhate:              hateCount
}

key-columns {
    format _id {
        pattern: ^(.*)_(id)$
        alias: $1
        component: $2
    }
    format uid {
        pattern: ^uid$
        alias: ownerUser
        component: id
    }
    format gid {
        pattern: ^gid$
        alias: ownerGroup
        component: id
    }
    format cat {
        pattern: ^cat$
        alias: category
        component: id
    }
}

table-name {
    _cat:               net.bodz.lily.schema.CategoryDef
    _form:              net.bodz.lily.schema.FormDef
    _formparm:          net.bodz.lily.schema.FormParameter
    _parm:              net.bodz.lily.schema.ParameterDef
    _parmval:           net.bodz.lily.schema.ParameterValue
    _phase:             net.bodz.lily.schema.PhaseDef
    _priority:          net.bodz.lily.schema.PriorityDef
    _schema:            net.bodz.lily.schema.SchemaDef
    _tag:               net.bodz.lily.schema.TagDef
    _tagv:              net.bodz.lily.schema.TagGroupDef
}

column-level {
    label: 1
    description: 1
}

join-level {
    parent: 1
}
