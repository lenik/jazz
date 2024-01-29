column-property {
    creation:           creationDate
    lastmod:            lastModifiedDate
    props:              properties
    t0:                 beginTime
    t1:                 endTime
    imagealt:           imageAlt
    nref:               refCount
    formargs:           formArguments
    text:               rawText
    nobj:               refCount
    votes:              voteScore
    nvote:              voteCount
    nfav:               favCount
    nhate:              hateCount
    nmsg:               messageCount
    parm:               parameterId
    tagv:               tagGroupId
    locale:             langTag
}

column-type {
    t0:                 java.time.ZonedDateTime
    t1:                 java.time.ZonedDateTime
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
    _cat:               net.bodz.lily.schema.meta.CategoryDef
    _form:              net.bodz.lily.schema.meta.FormDef
    _formparm:          net.bodz.lily.schema.meta.FormParameter
    _parm:              net.bodz.lily.schema.meta.ParameterDef
    _parmval:           net.bodz.lily.schema.meta.ParameterValue
    _phase:             net.bodz.lily.schema.meta.PhaseDef
    _priority:          net.bodz.lily.schema.meta.PriorityDef
    _schema:            net.bodz.lily.schema.meta.SchemaDef
    _tag:               net.bodz.lily.schema.meta.TagDef
    _tagv:              net.bodz.lily.schema.meta.TagGroupDef
}

table _tagv {
    column topic {
        javaName: forTopic
    }
    column reply {
        javaName: forReply
    }
}

column-level {
    label: 1
    description: 1
}

join-level {
    parent: 1
}
