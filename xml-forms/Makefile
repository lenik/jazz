
all: jaxb-models

jaxb-models:
	#xjc -no-header -dtd -d mod/jaxb/src -p net.bodz.xml.forms.apache.jaxb    http://httpd.apache.org/docs/2.2/style/modulesynopsis.dtd
	# http://www.w3.org/2001/XMLSchema.xsd (or dtd)
	xjc -no-header      -d mod/jaxb/src -p net.bodz.xml.forms.xs.jaxb        extern/w3/XMLSchema.xsd
    