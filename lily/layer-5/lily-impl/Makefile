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


DAOGEN = daogen \
	-H ../../layer-2/lily-model \
    -c sql/out/lily.rst
#    -c sql/out/violet.rst

dao-base:
	$(DAOGEN) -p net.bodz.lily.schema \
            _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock

dao-account:
	$(DAOGEN) -p net.bodz.lily.security \
            user usercat useroid usersec user_run group

dao-contact:
	$(DAOGEN) -p net.bodz.lily.contact \
            org orgunit partycat person personrole

dao-geo:
	$(DAOGEN) -p net.bodz.lily.geo \
            zone zonecat

dao-inet:
	$(DAOGEN) -p net.bodz.lily.inet \
            extsite

dao-io:
	$(DAOGEN) -p net.bodz.lily.io \
            storage

dao-pub:
	$(DAOGEN) -p net.bodz.lily.pub \
            article* post*

dao-reward:
	$(DAOGEN) -p net.bodz.lily.reward \
            badge user_badge

dao-util:
	$(DAOGEN) -p net.bodz.lily.util \
            uom

dao-security:
	$(DAOGEN) -p net.bodz.lily.util \
            user usercat useroid usersec user_run group policy

dao-vapp:
	$(DAOGEN) -p net.bodz.lily.vapp \
            apitype vapi vapi_credit vapi_log vapp vappcat vappreq vappreq_api

dao-lily-glob:
	$(DAOGEN) -p net.bodz.lily.other \
            -T lily.*

dao-violet:
	$(DAOGEN) -p net.bodz.violet.other \
            -T violet.*

dao-lily:
	$(DAOGEN) -p net.bodz.lily.schema \
            _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock \
            user usercat useroid usersec user_run group \
            org orgunit partycat person personrole \
            zone zonecat \
            extsite \
            storage \
            'article*' 'post*' \
            badge user_badge \
            uom \
            user usercat useroid usersec user_run group policy \
            apitype vapi vapi_credit vapi_log vapp vappcat vappreq vappreq_api

