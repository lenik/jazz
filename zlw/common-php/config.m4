dnl Common-User Configuration
dnl
dnl $Id: config.m4,v 1.2 2005-08-05 06:03:30 dansei Exp $
dnl $Log: not supported by cvs2svn $
dnl Revision 1.1  2005/07/30 05:20:10  dansei
dnl initial
dnl
dnl Revision 1.1  2005/07/29 14:21:46  dansei
dnl initial
dnl
dnl
include(`m4-ext.m4')dnl
sinclude(`config-local.m4')dnl
defu(`SMTP_SERVER', `localhost')dnl
defu(`SMTP_LOGIN', `mlv')dnl
defu(`SMTP_PASSWORD', `no')dnl
defu(`SMTP_AUTH', `true')dnl
defu(`MAIL_FROM', `root')dnl
defu(`MAIL_NAME', `root')dnl
defu(`MAIL_CHARSET', `UTF-8')dnl
defu(`NUM_FORMAT', `"%1.3f"')dnl
defu(`TIME_FORMAT', `"Y-m-d H:i:s"')dnl
