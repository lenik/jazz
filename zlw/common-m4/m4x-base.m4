dnl M4 Utilities
dnl
dnl M4 Language Extension
dnl
dnl $Id: m4x-base.m4,v 1.1 2005-07-29 14:21:45 dansei Exp $
dnl $Log: not supported by cvs2svn $
dnl
ifdef(`include_once_m4_ext', ,
`define(`include_once_m4_ext', 1)'dnl
`define(`defu', 
        `ifdef(`$1', , 
            `define(`$1', `$2')')')'dnl
)dnl
