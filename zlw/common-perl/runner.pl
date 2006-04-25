#!/usr/bin/perl

# TODO: make optional
use strict; 

use Config; 
use CGI qw/:standard *table/;
use File::Temp qw/:POSIX/;
use Data::Dumper; 
use cpl::utils;

sub tempnam {
    return File::Temp::tempnam(@_);
}

sub quote_js {
    my $str = shift; 
    $str =~ s/\\/\\\\/g; 
    $str =~ s/\n/\\n/g; 
    $str =~ s/\r/\\r/g;
    $str =~ s/\t/\\t/g;
    $str =~ s/\"/\\\"/g;
    $str =~ s/\'/\\\'/g;
    return $str; 
}

our $CGI = new CGI;

sub _GET {
    my $name = shift;
    my $vals = $CGI->{$name};
    if (defined $vals) {
        if (scalar(@$vals) > 0) {
            return join('', @$vals);
        }
    }
    return undef;
}

sub _PUT {
    my ($name, $val) = @_;
    $CGI->{$name} = [$val];
}

our $pr_style = _GET '_pr_style'; 
our $pr_src = _GET '_pr_src'; 
our $pr_params = _GET '_pr_params'; 
if ($pr_params ne '') {
    my %pr_params = %{map_parse($pr_params)}; 
    foreach (keys %pr_params) {
        my ($k, $v) = ($_, $pr_params{$_});
        _PUT $k, $v; 
    }
}

if (grep {$_ eq $pr_style} qw/html text raw/) {
    #my $pr_tmpperl = tempnam($ENV{TMP} || '/tmp', 'pr_');
    #open FH, ">$pr_tmpperl"; 
    #print FH $pr_src . "; 1; \n"; 
    #close FH; 
    
    if ($pr_style eq 'text') {
        print $CGI->header('text/plain'); 
    } elsif ($pr_style eq 'html') {
        print $CGI->header('text/html'); 
    } elsif ($pr_style eq 'raw') {
    }
    
    # include will get warnings, rather that require which will die. 
    undef $!; 
    undef $@; 
    eval $pr_src; 
    if ($! || $@) {
        if ($pr_style eq 'html') {
            print "<div style='color: red; font-style: italic; '><h2>Error $!</h2>";
            print "<hr />$@"; 
            print "</div>\n"; 
        } else {
            print "Error $!: $@\n";
        }
    }
    
    #foreach (glob(dirname($pr_tmpperl) . '/PHP*') as $tmp) {
    #    unlink($tmp); 
    #}
} elsif ($pr_style eq 'source') {
    # perl_parse_and_colorize()...?
    print $CGI->header('text/html'); 
    #$pr_src = htmlspecialchars($pr_src); 
    $pr_src =~ s/\n/<br>/g; 
    print $pr_src; 
} else {
    print $CGI->header('text/html');

    my $name = $ENV{'SERVER_NAME'}; 
    my $addr = $ENV{'SERVER_ADDR'}; 
    my $port = $ENV{'SERVER_PORT'}; 
    my $server = "$name/$addr:$port"; 
    my $remote = $ENV{'REMOTE_ADDR'}; 
    
    my $sample_empty    = "use strict; \nuse Data::Dumper; \n\n"; 
    my $sample_cgi      = "use strict; \nuse Data::Dumper; \n\n"
                        . "print \"<pre>\\n\" . Dumper(\$CGI) . \"</pre>\\n\"; \n\n"; 
    
    $sample_empty       = '"' . quote_js($sample_empty) . '"'; 
    $sample_cgi         = '"' . quote_js($sample_cgi) . '"'; 
    
    print <<"EOM"; 
<html id="html" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Perl Runner</title>
    <style type="text/css">
    	.autosize { width: 100%; height: 100%; overflow: visible }
    	.error { color: red }
    	.duration { color: green }
    	.address { font-size: medium; color: green }
    </style>
<!-- Event Impl. -->
    <script language="javascript">
        function run() {
            var form = document.getElementById("frmScript"); 
            var src = form["_pr_src"]; 
            // ...?
        }
        
        function done() {
            var form = document.getElementById("frmScript"); 
            var src = form["_pr_src"]; 
            src.focus(); 
        }
        
        function set_style() {
            var form = document.getElementById("frmScript"); 
            var src = form["_pr_src"]; 
            var style = form["_pr_style"]; 
            src.focus(); 
        }
        
        function set_sample() {
            var form = document.getElementById("frmScript"); 
            var src = form["_pr_src"]; 
            var tmpl = form["_pr_sample"]; 
            switch (tmpl.value) {
                case "empty": 
                    src.value = $sample_empty; 
                    break; 
                case "cgi": 
                    src.value = $sample_cgi; 
                    break; 
            }
            src.focus(); 
        }
    </script>
</head>
<body>
<form id="frmScript" method="get" action="?" target="result" onsubmit="javascript: run(); ">
<table border="0" width="100%" height="100%">
    <tr><td colspan="2">
        <h1 title="Your address: $remote" style="line-height: 10px; margin: 5px;">
            Perl Runner $Config{version}
            <span class="address">($server)</span>
        </h1>
        </td></tr>
    <tr><td width="50%">
        <textarea name="_pr_src" cols="40" rows="10" class="autosize">use strict; 
use Data::Dumper; 

</textarea>
        </td><td width="50%">
            <iframe name="result" class="autosize" onload="javascript: done();">
            </iframe>
        </td></tr>
    <tr><td colspan="2" align="center">
        <table width="100%" border="0"><tr>
            <td width="300px">
                <select name="_pr_sample" title="Sample Codes" onchange="javascript: set_sample(); ">
                    <option value="empty">Empty
                    <option value="cgi">Sample CGI
                </select>
                <select name="_pr_style" title="Output Method" onchange="javascript: set_style(); ">
                    <option value="html">As HTML
                    <option value="text" selected>As Plain-Text
                    <option value="raw">CGI Output
                    <option value="source">Code Format
                </select>
                <input type="submit" value="(R)un" accesskey="R"/>
            </td><td width="*">
                <input name="_pr_params" type="text" 
                    value="foo=bar:test=Hello, World!"
                    title="URL Parameters, will be parsed by map_parse" style="width: 100%">
            </td></tr></table>
        </td></tr>
</table>
</form>
</body>
</html>
EOM
}
