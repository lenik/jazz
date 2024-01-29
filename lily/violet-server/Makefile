include coolmake/m2proj.mf

remotehost = localhost
remotedir = violet-server

bin_files += \
    src/

src_excludes += \
    Makefile \
    backup \
    doc \
    home

run:
	screen -S $(remotedir) main VioletServer

run-daemon:
	screen -S $(remotedir) -d -m main VioletServer

debug:
	main -d VioletServer

