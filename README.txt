
This exercise will cover the various forms of machine agent metric rollups.

The scenario:

An online store is tracking business data from some backend system.  The system gives only very rudimentary data: a
periodic record of the number of items purchased, and the total cost of all the items purchased in that record, summed.
The purchase records are created one or more times per minute, at irregular intervals -- in other words, you won't
know at any time how many pending purchase records exist, you just have the ability to grab what's available at
that time.

From the information given, you need to create metrics for:
- Number of items purchased per time interval
- Average cost per item in a time interval
- Number of purchases per time interval
- Total revenue summed for all items purchased per time interval

The source code in this project is constructed so that you can just copy it into your machine agent project and use
it as a data source.  To use, simply create a MonitoredDataSource object once, at startup, and periodically call
getPurchaseRecords() to get the pending purchase record objects available at that time.  That should minimize the amount
of boilerplate data gathering code you will need to write, so you can focus on the metric creation.