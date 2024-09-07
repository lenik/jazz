include coolmake/m2proj.mf

DBNAME=lily
SHELL=/bin/bash

remotehost = localhost
remotedir = lily-impl

bin_files += \
    src/

src_excludes += \
    Makefile \
    backup \
    doc \
    home

run:
	screen -S $(remotedir) main LilyTestd

run-daemon:
	screen -S $(remotedir) -d -m main LilyTestd

debug:
	main -d LilyTestd

typesdir=../basic-types

DAOGEN = daogen $(DAOFLAGS) \
    -H $(typesdir) \
    -p net.bodz.other.schema \
    -c ../sql/out/lily.rst

predao:
	cd ../sql; make -B

dao-base: predao
	$(DAOGEN) _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock

dao-account: predao
	$(DAOGEN) usertype grouptype user usercat useroid useroidtype usersec user_run group policy

dao-contact: predao
	$(DAOGEN) org orgunit partycat person personrole persontag person_tag

dao-person: predao
	$(DAOGEN) person

dao-geo: predao
	$(DAOGEN) zone zonecat

dao-inet: predao
	$(DAOGEN) extsite

dao-io: predao
	$(DAOGEN) storage

dao-pub: predao
	$(DAOGEN) article* post*

dao-reward: predao
	$(DAOGEN) badge user_badge

dao-util: predao
	$(DAOGEN) uom

dao-vapp: predao
	$(DAOGEN) apitype vapi vapi_credit vapi_log vapp vappcat vappreq vappreq_api

dao-lily-glob: predao
	$(DAOGEN) -T lily.*

dao: predao
	$(DAOGEN) \
            _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock \
	    usertype grouptype user usercat useroid useroidtype usersec user_run group policy \
	    org orgunit partycat person personrole persontag person_tag \
            zone zonecat \
            extsite \
            storage \
            'article*' 'post*' \
            badge user_badge \
            uom \
            apitype vapi vapi_credit vapi_log vapp vappcat vappreq vappreq_api

del-exporters-samples: del-samples del-exporters

del-samples: predao
	find -name *Samples.java -print -delete
	find -name *MapperTest.java -print -delete

del-exporters: predao
	find -name *Exporter.java -print -delete

code-web:
	d=$$(readlink -f .); p=$${d%/*}; code -n "$$p/@lily"

inserts:
	. pgcmds; \
	   psql -d $(DBNAME) -1 < <( for ((i=0; i < 100; i++)); do cat test/person.ddl; done )

predefs:
	tstool predefs -Oweb/src -p net.bodz.lily

