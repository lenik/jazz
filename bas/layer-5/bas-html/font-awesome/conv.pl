#!/usr/bin/perl

=head1 NAME

conv - 

=cut
use strict;
use vars qw($LOGNAME $LOGLEVEL);
use cmt::log(2);
    $LOGNAME    = 'conv'; # $0 =~ /([^\/\\.]+)(?:\.\w+)*$/;
use cmt::util('readfile');
use cmt::vcs('parse_id');
    my %RCSID   = parse_id('$Id: - @VERSION@ @DATE@ @TIME@ - $');
use Data::Dumper;
use Getopt::Long;
    Getopt::Long::Configure('gnu_getopt');
use YAML;

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

B<conv>
    S<[ B<-q> | B<--quiet> ]>
    S<[ B<-v> | B<--verbose> ]>
    S<[ B<-h> | B<--help> ]>
    S<[ B<--version> ]>
    S<[ B<--> ]>
    S<[ I<...the rest of arguments...> ]>

=head1 DESCRIPTION

B<conv> is a WHAT used for WHAT. It HOW-WORKS.

BACKGROUND-PROBLEM.

HOW-conv-RESOLVES.

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

    conv --help

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
    my $yaml = readfile('icons.yml');
    my $doc = Load($yaml);
    my $icons = $doc->{'icons'};
    
    for my $icon (@$icons) {
        my $unicode = $icon->{'unicode'};
        my $id = $icon->{'id'};
        my $name = $icon->{'name'};
        my $created = $icon->{'created'};
        my $categories = $icon->{'categories'};
        
        my $jid = $id;
        $jid =~ s/-/_/g;
        $jid =~ tr/[a-z]/[A-Z]/;
        print "    /**\n";
        print "     * $_\n" for @$categories;
        print "     *\n";
        print "     * \@version $created\n" if defined $created;
        print "     */\n";
        print "    char FA_$jid = '\\u$unicode';\n";
        print "\n";
    }
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
