# Example Plugin
If your classification rules simply cannot be expressed using a RegEx and if you want to add 
more power to your classification rules, DCP has a beta feature that lets you register 
and manage UDTs through it’s plugins framework. Currently, it supports only the addition of UDTs 
through plugins and in the future, we plan to add more components to this model. 

# How to use this
* Modify the build.sbt to point to your local unifi installation directory to be able to fetch 
  plugin api jars.
* Build the plugin using the following command:
```
sbt clean package
```
* Deploy the plugin by copying the plugin jar to executor-service plugins:
```
cp target/scala-2.12/dataprep-plugin-example_2.12-1.0.0.jar ~/dev/unifing/services/executor/executor-service/plugins/
```
* Re-start unifi
* Verify through Manage -> Data Types that the new `OrderStatus` data type got added to the list of data types. 
* Create a dataset with the following contents and verify that the third column gets detected as OrderStatus. 
```
order_id,order_date,order_status
O_101,2020-01-20,Ordered
O_102,2020-01-21,Ordered
O-102,2020-01-21,Pending
O-102,2020-01-21,Paid
O_203,2020-01-22,Processed
O-204,2020-01-23,DeliveryException
```
