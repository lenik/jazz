m4_include(`m4x-base.m4')
M4X_BEGIN()

# Common-User Configuration
#
# $Id: config.m4,v 1.3 2005-08-07 13:02:46 dansei Exp $
# $Log: not supported by cvs2svn $
# Revision 1.2  2005/08/05 06:03:30  dansei
# dev pack.
#
# Revision 1.1  2005/07/30 05:20:10  dansei
# initial
#
# Revision 1.1  2005/07/29 14:21:46  dansei
# initial
#

m4_sinclude(`config-local.m4')

m4x_lazydef(`SMTP_SERVER', `localhost')
m4x_lazydef(`SMTP_LOGIN', `mlv')
m4x_lazydef(`SMTP_PASSWORD', `no')
m4x_lazydef(`SMTP_AUTH', `true')
m4x_lazydef(`MAIL_FROM', `root')
m4x_lazydef(`MAIL_NAME', `root')
m4x_lazydef(`MAIL_CHARSET', `UTF-8')
m4x_lazydef(`NUM_FORMAT', `"%1.3f"')
m4x_lazydef(`TIME_FORMAT', `"Y-m-d H:i:s"')

M4X_END()