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

my $opt_package = 'net.bodz.bas.html.dom.tag';
my $opt_outdir;
my $opt_wrapper = 0;

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
               'w|wrapper'      => \$opt_wrapper,
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
    -w, --wrapper           Generate wrapper classes
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
        my $Class0 = "_Mutable${Name}";
        my $Class1 = "Mutable${Name}";
        my $fqcn0 = "$opt_package.gen.$Class0";
        my $fqcn1 = "$opt_package.$Class1";
        
        my $Class = $opt_wrapper ? $Class1 : $Class0;
        my $fqcn = $opt_wrapper ? $fqcn1 : $fqcn0;
        my $pkg = $fqcn;
            $pkg =~ s/\.\w+$//;

        my $file = $fqcn;
            $file =~ s/\./\//g;
            $file = "$opt_outdir/$file.java";

        # FOR IHtmlOut
        # print "    $Class $name();\n";
        
        # FOR NullHtmlOut
        # print "    public $Class $name() { return new $Class(IXmlTagBuilder.NULL); }\n";
        
        # FOR AbstractHtmlOut
        # print "    public $Class $name() { return new $Class(tag(\"$name\")); }\n";

        # next;

        _log1 "Write to file $file";
        mkdir_p $file;
        open(OUT, ">$file") or die "Can't write to file $file: $!";

        print OUT "package $pkg;\n";
        print OUT "\n";
        if ($opt_wrapper) {
            my $impl = $Class0;
            print OUT "import net.bodz.bas.html.dom.IHtmlTag;\n";
            print OUT "import $fqcn0;\n";
            print OUT "\n";
            print OUT "public class $Class\n";
            print OUT "        extends $Class0<$Class> {\n";
            print OUT "\n";
            print OUT "    public $Class(IHtmlTag parent, String tagName) {\n";
            print OUT "        super(parent, tagName);\n";
            print OUT "    }\n";
        } else {
            print OUT "import net.bodz.bas.html.dom.IHtmlTag;\n";
            print OUT "import net.bodz.bas.html.dom.MutableHtmlTag;\n";
            print OUT "\n";

            if (defined $doc) {
                print OUT "/**\n";
                print OUT "  * $doc\n";
                print OUT "  */\n";
            }
            print OUT "\@SuppressWarnings(\"unchecked\")\n" if scalar(@attrs);
            print OUT "public class $Class<self_t extends IHtmlTag>\n";
            print OUT "        extends MutableHtmlTag<self_t> {\n";
            print OUT "\n";
            print OUT "    public $Class(IHtmlTag parent, String tagName) {\n";
            print OUT "        super(parent, tagName);\n";
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

                print OUT "    public self_t $attr_(Object val) {\n";
                print OUT "        attr(\"$attrh\", val);\n";
                print OUT "        return (self_t) this;\n";
                print OUT "    }\n";
            } #for attrs
        }

        print OUT "\n}\n";
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
    print "[$LOGNAME] Translate HTML element to program model\n";
    print "Written by Lenik,  Version 0.$RCSID{rev},  Last updated at $RCSID{date}\n";
}

=head1 SEE ALSO

The L<cmt/"Perl_simple_cli_program_template">

=head1 AUTHOR

Lenik (谢继雷) <xjl@99jsj.com>

=cut
