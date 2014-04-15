#!/usr/bin/perl

=head1 NAME

split-elements - 将标准文档中的各个 Element 说明文字转移到 elm/TAG.htm 下。

=cut
use strict;
use vars qw($LOGNAME $LOGLEVEL);
use cmt::log(2);
    $LOGNAME    = 'split-elements'; # $0 =~ /([^\/\\.]+)(?:\.\w+)*$/;
use cmt::vcs('parse_id');
    my %RCSID   = parse_id('$Id: - @VERSION@ @DATE@ @TIME@ - $');
use Getopt::Long;
    Getopt::Long::Configure('gnu_getopt');

sub _main; sub _version; sub _help;

sub _boot {
    GetOptions('q|quiet'        => sub { $LOGLEVEL-- },
               'v|verbose'      => sub { $LOGLEVEL++ },
               'version'        => sub { _version; exit 0 },
               'h|help'         => sub { _help; exit 0 },
               )
        or exit(1);
    _main;
}

=head1 SYNOPSIS

B<split-elements>
    S<[ B<-q> | B<--quiet> ]>
    S<[ B<-v> | B<--verbose> ]>
    S<[ B<-h> | B<--help> ]>
    S<[ B<--version> ]>
    S<[ B<--> ]>
    S<[ I<...the rest of arguments...> ]>

=head1 DESCRIPTION

Read from STDIN and save each element doc to elm/TAG.htm.

BACKGROUND-PROBLEM.

HOW-split-elements-RESOLVES.

=head1 OPTIONS

=over 8

=item B<-m> | B<--my-option>

...

=item B<-q> | B<--quiet>

Repeat this option to suppress unimportant information to display.

=item B<-v> | B<--verbose>

Repeat this option to display more detailed information.

=item B<-h> | B<--help>

Display a breif help page and exit(0).

=item B<--version>

Display a short version information and exit(0).

=back

=head1 EXAMPLES

=over 4

=item Show help

    split-elements --help

=back

=head1 ENVIRONMENT

=over 8

=item TEMP, TMP

TEMP(or TMP, if $TEMP directory isn't existed) directory used to create
temporary files.

=back

=cut
sub _help {
    &_version;
    print "\nSyntax: \n    $0 [OPTION] [--] ...\n", <<'EOM';

Common options:
    -q, --quiet             Repeat to get less info
    -v, --verbose           Repeat to get more info
    -h, --help              Show this help page
        --version           Print the version info
EOM
}

exit (_boot or 0);

sub _main {
    # Start/Search,
    my $state = 'S';
    my $name;

    while (<>) {
        if (/<h[1-4]/) {
            if ($state eq 'E') {
                close ELM;
                $state = 'S';
            }
        }

        if (/<h4 id="the-(.*?)-elements?">/) {
            if ($state eq 'S') {
                $state = 'E';
                $name = $1;
                _log1("Split element $name");
                open(ELM, ">elm/$name.htm") or die("Failed to create elm/$name.htm.");
            }
        }

        if ($state eq 'E') {
            print ELM;
        }
    }

    close ELM;
}

=head1 DIAGNOSTICS

(No Information)

=cut
# (HELPER FUNCTIONS)

=head1 HACKING

(No Information)

=cut
# (MODULE FUNCTIONS)

=head1 HISTORY

=over

=item 0.x

The initial version.

=back

=cut
sub _version {
    print "[$LOGNAME]  \n";
    print "Written by Lenik,  Version 0.$RCSID{rev},  Last updated at $RCSID{date}\n";
}

=head1 SEE ALSO

The L<cmt/"Perl_simple_cli_program_template">

=head1 AUTHOR

Lenik (谢继雷) <xjl@99jsj.com>

=cut
