
package cpl::utils; 

use strict; 
use Exporter; 
use vars qw/@ISA @EXPORT/; 
use vars qw/$charFS $setFS $patFS/; 

sub qq_eval {
    my $qq = shift;
    $qq =~ s/\\n/\n/g;
    $qq =~ s/\\t/\t/g;
    $qq =~ s/\\(.)/$1/g;
    return $qq;
}

sub ltrim {
    local $_ = shift;
    s/^\s+//;
    return $_;
}

sub rtrim {
    local $_ = shift;
    s/\s+$//;
    return $_;
}

sub trim {
    local $_ = shift;
    s/^\s+//;
    s/\s+$//; 
    return $_;
}

sub list_parse {
    my $string = shift;
    return [] if $string eq '';
    
    my $list = [];
    my $qq; 
    foreach (split(':', $string)) {
        if ($qq ne '') {
            my $last = 0; 
            if ($_ =~ m/\"$/) {
                $_ = substr($_, 0, -1);
                $_ = rtrim $_; 
                $last = 1; 
            }
            $_ = qq_eval($_); 
            $qq .= ':' . $_;
            if ($last) {
                push @$list, $qq;
                $qq = undef;
            }
        } elsif ($_ =~ m/^\"/) {
            my $last = 0; 
            $_ = substr($_, 1);
            $_ = ltrim $_; 
            if ($_ =~ m/\"$/) {
                $_ = substr($_, 0, -1);
                $_ = rtrim $_; 
                $last = 1; 
            }
            $_ = qq_eval($_);
            if ($last) {
                push @$list, $_;
            } else {
                $qq = $_;
            }
        } else {
            $_ = trim $_; 
            push @$list, $_;
        }
    }
    return $list; 
}

sub map_parse {
    my $string = shift;

    my $map = {};
    my $qq_k; 
    my $qq; 
    foreach (split(':', $string)) {
        if ($qq_k ne '') {
            my $last = 0; 
            if ($_ =~ m/\"$/) {
                $_ = substr($_, 0, -1);
                $_ = rtrim $_; 
                $last = 1; 
            }
            $_ = qq_eval($_); 
            $qq .= ':' . $_;
            if ($last) {
                $map->{$qq_k} = $qq; 
                $qq_k = undef;
                $qq = undef; 
            }
        } else {
            my ($k, $v) = split('=', $_, 2);
            $k = trim $k;
            next if $k eq '';
            
            if ($v =~ m/^\"/) {
                my $last = 0; 
                $v = substr($v, 1);
                $v = ltrim $v;
                if ($v =~ m/\"$/) {
                    $v = substr($_, 0, -1);
                    $v = rtrim $v;
                    $last = 1;
                }
                $v = qq_eval $v;
                if ($last) {
                    $map->{$k} = $v;
                } else {
                    $qq_k = $k;
                    $qq = $v;
                }
            } else {
                $v = trim $v;
                $map->{$k} = $v; 
            }
        }
    }
    return $map;
}

@ISA = qw(Exporter); 
@EXPORT = qw(ltrim
             rtrim
             trim
             list_parse
             map_parse); 

1; 
