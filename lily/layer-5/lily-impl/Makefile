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

lib-updater:
	mjava lib-updater

lib-updater-O:
	mjava -O lib-updater

run:
	screen -S $(remotedir) main LilyTestd

run-daemon:
	screen -S $(remotedir) -d -m main LilyTestd

debug:
	main -d LilyTestd

lily1:
	main JavaGen \
            -c cg-lily.rst \
            -p net.bodz.lily.schema \
            _schema _cat _form _formparm _parm _parmval _phase _tagv _tag _rlock

