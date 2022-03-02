#!/usr/bin/perl -w
##################################################
#This script is responsible for making a secure  #
#connection via ssh to server1 and executing the #
#command.                                        #
##################################################

#import required modules
use strict;
use Net::SSH qw(ssh);

#declare local variables
my $ssh;
my $host = $ARGV[0];
my $user = $ARGV[1];
my $cmd = $ARGV[2];

#print "Connecting to $host as $user and running $cmd ...\n";
ssh("$user\@$host", $cmd);
#print "Command executed successfully!\n"