package net.bodz.bas.lop.impl;  

import java.io.IOException;

import net.bodz.bas.lang.err.ParseException;

@SuppressWarnings("unused")
%%

%byaccj
%unicode
%line
%column
%type int
%int
// %init { ... }
// %initthrow { exception1 [, ...] }
// %scanerror FLEXError
// %funcname yylex

%public
%class SampleLexer
%extends JFlexLexer
%yylexthrow ParseException

%{
    public static final int ID             = 300;
    public static final int NUMBER         = 301;
    public static final int STRING         = 302;
%}

NewLine         = \r|\n|\r\n
SP              = {NewLine} | [ \t\f]
Space           = {SP}+

CmtBlock        = "/*" [^*] ~"*/"
CmtLine         = ("//" | "#") .* {NewLine}
CmtLarge        = "/**" {CmtLargeText} "*"+ "/"
CmtLargeText    = ( [^*] | \*+ [^*/] )*
Comment         = {CmtBlock} | {CmtLine} | {CmtLarge}

N8X             = 0 [0-7]+
N10X            = 0 | [1-9][0-9]*
N16X            = 0 [xX] [0-9A-Fa-f]+
IntegerR        = -? ( {N8X} | {N10X} | {N16X} )
IntegerL        = {IntegerR} [Ll]
Integer         = {IntegerR} | {IntegerL}

R10X1           = [0-9]* "." [0-9]+
R10X2           = [0-9]+ "." [0-9]*
R10X            = {R10X1} | {R10X2}
R10E            = ({Integer} | {R10X}) "e" [+\-][0-9]+
Real            = -? ( {R10X} | {R10E} )
RealF           = {Real} [Ff]

C_IdStart       = [:letter:] | [_]
C_IdBody        = {C_IdStart} | [:digit:]
C_Id            = {C_IdStart} {C_IdBody}*

Id              = {C_Id}

%x QQ_STRING

%%

<YYINITIAL> {
    \"                  { startRecord(); enter(QQ_STRING); }
    
    {IntegerL}          { setValue(Long.parseLong(yytext().substring(0, yytext().length() - 1)));
                          return NUMBER; 
                        }
    {IntegerR}          { setValue(Integer.parseInt(yytext())); return NUMBER; }
    {Real}              { setValue(Double.parseDouble(yytext())); return NUMBER; }
    {RealF}             { setValue(Float.parseFloat(yytext())); return NUMBER; }
    
    {Id}                { setValue(yytext()); return ID; }

    {Comment}           { /* ignore */ }
    {Space}             { /* ignore */ }

    .                   { return (int)yytext().charAt(0); }
}

<QQ_STRING> {
    [^\n\r\"\'\\]+      { record(yytext()); }
    \\t                 { record("\t"); }
    \\n                 { record("\n"); }
    \\r                 { record("\r"); }
    \\\"                { record("\""); }
    \\\\                { record("\\"); }
    \"                {    setValue(endRecord()); 
                            yybegin(YYINITIAL); 
                            return STRING; 
                        }
}
