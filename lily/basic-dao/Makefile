include coolmake/m2proj.mf

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

apidir=../lily-api
typesdir=../basic-types

DAOGEN = daogen $(DAOFLAGS)

DAOGEN_LILY0 = $(DAOGEN) \
    -H $(apidir) \
    -c sql/out/lily.rst

DAOGEN_LILY = $(DAOGEN) \
    -H $(typesdir) \
    -c sql/out/lily.rst

#    -H ../../layer-2/lily-model 

predao:
	cd sql; make -B

dao-base: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.schema \
            _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock

dao-account: predao
	$(DAOGEN_LILY0) \
            -p net.bodz.lily.security \
            usertype grouptype user usercat useroid useroidtype usersec user_run group policy

dao-contact: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.contact \
            org orgunit partycat person personrole

dao-geo: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.geo \
            zone zonecat

dao-inet: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.inet \
            extsite

dao-io: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.io \
            storage

dao-pub: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.pub \
            article* post*

dao-reward: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.reward \
            badge user_badge

dao-util: predao
	$(DAOGEN_LILY) \
            -O $(typesdir) \
            -p net.bodz.lily.util \
            uom

dao-vapp: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.vapp \
            apitype vapi vapi_credit vapi_log vapp vappcat vappreq vappreq_api

dao-lily-glob: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.other \
            -T lily.*

dao-lily: predao
	$(DAOGEN_LILY) \
            -p net.bodz.lily.schema \
            _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock \
            org orgunit partycat person personrole \
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

