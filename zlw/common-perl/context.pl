#!/usr/bin/perl

use strict;
use Config; 
use CGI; 

print <<"EOB";
Content-type: text/html

<html>
    <head>
    <title>Perl Context</title>
    </head>
    <body>
    <ul>
    <li><a href="#cgi.q">CGI Request Parameters</a></li>
    <li><a href="#env">Environment Variables</a></li>
    <li><a href="#inc">Include Paths</a></li>
    <li><a href="#config">Perl Config</a></li>
    </ul>
EOB

print "<a name='cgi.q' /><h1>CGI Request Parameters (class CGI)</h1>\n"; 
my $query = new CGI; 
foreach (keys %$query) {
	my $v = $query->{$_}; 
	if (ref($v) eq 'ARRAY') {
		my $res = ''; 
		foreach (@$v) {
			if ($res ne '') { $res .= " <em><font color='#FFAAAA'>and</font></em> "; }
			$res .= $_; 
		}
		$v = $res; 
	}
	print "$_ = $v <BR>\n"; 
}

print "<a name='env' /><h1>Environemnt Variables (\%ENV)</h1>\n";
foreach (keys %ENV) {
        print "$_ = $ENV{$_} <BR>\n";
}

print "<a name='inc' /><h1>Include Paths (\@INC)</h1>\n";
foreach (@INC) {
        print "$_ <BR>\n";
}

print "<a name='config' /><h1>Perl Config (\%Config)</h1>\n"; 
foreach (keys %Config) {
	print "$_ = $Config{$_} <BR>\n"; 
}

print <<"EOB"; 
	</body>
</html>
EOB

