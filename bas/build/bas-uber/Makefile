include coolmake/m2proj.mf

check-index:
	[ -d x ] || mkdir x
	cd x; jar xf ../target/bas-uber-*-SNAPSHOT.jar
	#cat x/META-INF/programs
	cat x/META-INF/features/net.bodz.bas.meta.codegen.IndexedType

clean:
	rm target/*.jar

