JAVA_LIB   := $(shell cygpath -m "$(JAVA_LIB)")
XSLT2       = java -jar ${JAVA_LIB}/saxon8.jar

test: td2pdb

td2pdb: blob/base.td2pdb.xml blob/human.td2pdb.xml blob/house.td2pdb.xml

%.td2pdb.xml: %.td.xml td2pdb.xsl
	$(XSLT2) -o $@ $*.td.xml td2pdb.xsl
