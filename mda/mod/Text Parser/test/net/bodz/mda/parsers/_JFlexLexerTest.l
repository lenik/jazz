package net.bodz.mda.javalava.initial;  

import java.io.IOException;

import net.bodz.bas.lang.err.ParseException;
import net.bodz.mda.parsers.JFlexLexer;

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
%class JavalavaLexer
%extends JFlexLexer
%{
    protected JavalavaLexer(JavalavaParser parser) {
        super(parser);
    }
%}

%yylexthrow ParseException

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

QUOT            = "\""
EQ_SLASH        = "=" {SP}* "/"

%x QQ_STRING
%x REGEX_CTOR

%%

<YYINITIAL> {
    \"                  { startRecord(); enter(QQ_STRING); }
    {EQ_SLASH}          { startRecord(); enter(REGEX_CTOR); }
    
    "=>"	                { return BARROW; }

    {IntegerL}          { setValue(Long.parseLong(yytext().substring(0, yytext().length() - 1)));
                          return NUMBER; 
                        }
    {IntegerR}          { setValue(Integer.parseInt(yytext())); return NUMBER; }
    {RealD}             { setValue(Double.parseDouble(yytext())); return NUMBER; }
    {RealF}             { setValue(Float.parseFloat(yytext())); return NUMBER; }
    
    {Id}                { setValue(yytext()); return ID; }

    {Comment}           { /* ignore */ }
    {Space}             { /* ignore */ }

    .                   { return (int)yytext().charAt(0); }
}

<QQ_STRING> {
    [\"]              { if (qterm == yytext().charAt(0)) {
                            setValue(endRecord()); 
                            yybegin(YYINITIAL); 
                            return STRING; 
                          } else {
                            record(yytext()); 
                          }
                        }
    [^\n\r\"\'\\]+      { record(yytext()); }
    \\t                 { record("\t"); }
    \\n                 { record("\n"); }
    \\r                 { record("\r"); }
    \\\"                { record("\""); }
    \\\\                { record("\\"); }
}

<REGEX_CTOR> {
	/					{ leave(); return endRecord(); }
	(\\.|[^\"])*?\"		{ return PSTRING; }
}
