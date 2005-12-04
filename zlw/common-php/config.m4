m4_include(`m4x-base.m4')
M4X_BEGIN()

# Common-User Configuration
#
# M4X___ORIGINAL_FILE__
#

m4x_lazydef(`SMTP_SERVER', `localhost')
m4x_lazydef(`SMTP_LOGIN', `root')
m4x_lazydef(`SMTP_PASSWORD', `')
m4x_lazydef(`SMTP_AUTH', `true')
m4x_lazydef(`MAIL_FROM', `root@localhost')
m4x_lazydef(`MAIL_NAME', `Root')
m4x_lazydef(`MAIL_CHARSET', `UTF-8')
m4x_lazydef(`MAIL_REPLY', `root@localhost')
m4x_lazydef(`NUM_FORMAT', `"%1.3f"')
m4x_lazydef(`TIME_FORMAT', `"Y-m-d H:i:s"')

M4X_END()