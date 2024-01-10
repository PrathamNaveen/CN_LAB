# Point to Point Network Simulation

#Create
set ns [new Simulator]
set tracefile [open main.tr w]
$ns trace-all $tracefile
set namfile [open main.nam w]
$ns namtrace-all $namfile

#finish procedure
proc Finish {} {
    global ns tracefile namfile
    $ns flush-trace
    close $tracefile
    close $namfile

    exec nam main.nam &
    exec echo "The number of packet drops is " &
    exec grep -c "^d" main.tr &
    exit 0
}

set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]

$n0 label "TCP Source"
$n2 label "Sink"

$ns color 1 Blue

$ns duplex-link $n0 $n1 1Mb 10ms DropTail
$ns duplex-link $n1 $n2 1Mb 10ms DropTail

$ns duplex-link-op $n0 $n1 orient right
$ns duplex-link-op $n1 $n2 orient right

$ns queue-limit $n0 $n1 10
$ns queue-limit $n1 $n2 10

set tcp [new Agent/TCP]
$ns attach-agent $n0 $tcp
$tcp set class_ 1
set sink [new Agent/TCPSink]
$ns attach-agent $n2 $sink
$ns connect $tcp $sink

set cbr [new Application/Traffic/CBR]
$cbr attach-agent $tcp
$cbr set packetSize_ 500

$ns at 0.0 "$cbr start"
$ns at 4.0 "Finish"

$ns run