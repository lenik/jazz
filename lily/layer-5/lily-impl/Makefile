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

lily:
	main JavaGen \
            -p net.bodz.lily.schema \
            @sql/out/lily.lst

