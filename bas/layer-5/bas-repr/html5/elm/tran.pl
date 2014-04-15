#!/usr/bin/perl

=head1 NAME

tran - Translate HTML element to program model

=cut
use strict;
use vars qw($LOGNAME $LOGLEVEL);
use cmt::log(2);
    $LOGNAME    = 'tran'; # $0 =~ /([^\/\\.]+)(?:\.\w+)*$/;
use cmt::vcs('parse_id');
    my %RCSID   = parse_id('$Id: - @VERSION@ @DATE@ @TIME@ - $');
use Getopt::Long;
    Getopt::Long::Configure('gnu_getopt');

sub _main; sub _version; sub _help;
sub mkdir_p;

my $opt_package = 'net.bodz.bas.io.html.tag';
my $opt_outdir;

my %keywords = (
    'for'       => 'for_',
    'default'   => 'default_',
);

sub _boot {
    GetOptions('q|quiet'        => sub { $LOGLEVEL-- },
               'v|verbose'      => sub { $LOGLEVEL++ },
               'version'        => sub { _version; exit 0 },
               'h|help'         => sub { _help; exit 0 },
               'p|package=s'    => \$opt_package,
               )
        or exit(1);

    $opt_outdir = "../../src/main/java";
    _main;
}

=head1 SYNOPSIS

B<tran>
    S<[ B<-q> | B<--quiet> ]>
    S<[ B<-v> | B<--verbose> ]>
    S<[ B<-h> | B<--help> ]>
    S<[ B<--version> ]>
    S<[ B<--> ]>
    S<[ I<...the rest of arguments...> ]>

=head1 DESCRIPTION

B<tran> is a WHAT used for WHAT. It HOW-WORKS.

BACKGROUND-PROBLEM.

HOW-tran-RESOLVES.

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

    tran --help

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
    -p, --package=FQPN      Specify the package name
    -q, --quiet             Repeat to get less info
    -v, --verbose           Repeat to get more info
    -h, --help              Show this help page
        --version           Print the version info
EOM
}

exit (_boot or 0);

sub _main {
    my $pos = 'S';
    my $pbuf = '';

    my @names;
    my $doc;
    my @attrs;
    my %map;

    while (<>) {
        if (/<h4 id="the-(.*?)-elements?">/) {
            # while (/<dfn.*?><code>(\w*)<\/code><\/dfn>/g)
            my $ns = $1;
            $ns =~ s/-/ /g;
            $ns =~ s/, and /, /;
            $ns =~ s/ //g;
            foreach (split(/,/, $ns)) {
                _log2 "name: $_.";
                push @names, $_;
            }
        }

        if (/<dt><a .*?>Content attributes/) { $pos = 'c-attr'; next }
        if (/<dt><a .*?>Content model/) { $pos = 'c-model'; next }

        if ($pos eq 'c-attr') {
            next if (/Global attributes/);
            if (/<dd><code .*?><a .*?>([a-z-]+)<\/a>/) {
                _log2 "attr: $1.";
                push @attrs, $1;
            }
        }

        if (substr($pos, 0, 2) eq 'c-') {
            $pos = 'S' if (/<dt>/);
            $pos = 'S' if (/<\/dl>/);
        }

        if ($pos eq 'S') {
            $pos = 'P' if (/<p\b/);
        }
        
        if ($pos eq 'P') {
            $pbuf .= $_;
            if (/<\/p>/) {
                $pos = 'S';
                $pbuf =~ s/<\/?\w+.*?>//g;
                $pbuf =~ s/^\s+//;
                $pbuf =~ s/\s+/ /sg;
                
                if ($pbuf =~ /The (\w+) element/) {
                    _log2 "element doc: $pbuf";
                    $doc = $pbuf;
                }

                if ($pbuf =~ /The (\w+) (content )?attribute/) {
                    _log2 "attr doc $1: $pbuf";
                    $map{$1} = $pbuf;
                }

                $pbuf = '';
            }
        }
    }

    for my $name (@names) {
        my $Name = ucfirst $name;
        my $Class = "Html${Name}Builder";
        my $fqcn = "$opt_package.$Class";

        my $file = $fqcn;
            $file =~ s/\./\//g;
            $file = "$opt_outdir/$file.java";

        _log1 "Write to file $file";
        mkdir_p $file;
        open(OUT, ">$file") or die "Can't write to file $file: $!";

        print OUT "package $opt_package;\n";
        print OUT "\n";
        print OUT "import net.bodz.bas.io.html.DecoratedHtmlTagBuilder;\n";
        print OUT "import net.bodz.bas.io.xml.IXmlTagBuilder;\n";
        print OUT "\n";

        if (defined $doc) {
            print OUT "/**\n";
            print OUT "  * $doc\n";
            print OUT "  */\n";
        }
        print OUT "public class $Class\n";
        print OUT "        extends DecoratedHtmlTagBuilder<$Class> {\n";
        print OUT "\n";
        print OUT "    public $Class(IXmlTagBuilder o) {\n";
        print OUT "        super(o);\n";
        print OUT "    }\n";
        
        for my $attrh (@attrs) {
            my $Attr = '';
            for my $w (split('-', $attrh)) {
                $Attr .= ucfirst $w;
            }
            my $attr = lcfirst $Attr;

            my $attr_ = $keywords{$attr};
                $attr_ = $attr unless defined $attr_;

            print OUT "\n";
            my $adoc = $map{$attrh};
            if (defined $adoc) {
                print OUT "    /**\n";
                print OUT "      * $adoc\n";
                print OUT "      */\n";
            }

            print OUT "    public $Class $attr_(String val) {\n";
            print OUT "        attr(\"$attrh\", val);\n";
            print OUT "        return this;\n";
            print OUT "    }\n";
        } #for attrs

        print OUT "\n}\n";

        # print "    $Class $name();\n";
        # print "    public $Class $name() { return new $Class(tag(\"$name\")); }\n";
        print "    public $Class $name() { return new $Class(IXmlTagBuilder.NULL); }\n";
    } #for names

} #main

=head1 DIAGNOSTICS

(No Information)

=cut
# (HELPER FUNCTIONS)

=head1 HACKING

(No Information)

=cut
# (MODULE FUNCTIONS)
sub mkdir_p() {
    my $file = shift;
    my $dir = $file;
        $dir =~ s/\/[^\/]*$//;

    if (! -d $dir) {
        mkdir($dir) or die "Can't create directory $dir: $!";
    }
}

=head1 HISTORY

=over

=item 0.x

The initial version.

=back

=cut
sub _version {
    print "[$LOGNAME] Translate HTML element to program model \n";
    print "Written by Lenik,  Version 0.$RCSID{rev},  Last updated at $RCSID{date}\n";
}

=head1 SEE ALSO

The L<cmt/"Perl_simple_cli_program_template">

=head1 AUTHOR

Lenik (谢继雷) <xjl@99jsj.com>

=cut
