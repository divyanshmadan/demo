Currently not the entire data set is loaded to Elastic search,I just load one element using
curl -XPUT https://search-divy-search-hk2xfp7rk4nysh5us3ddtzmoey.us-west-2.es.amazonaws.com/plans/plan/1 -d '{"director": "Burton, Tim", "genre": ["Comedy","Sci-Fi"], "year": 1996, "actor": ["Jack Nicholson","Pierce Brosnan","Sarah Jessica Parker"], "title": "Mars Attacks!"}' -H 'Content-Type: application/json'


which can be verified

curl -XGET 'https://search-divy-search-hk2xfp7rk4nysh5us3ddtzmoey.us-west-2.es.amazonaws.com/plans/_search?q=BRYAN'

Committed the code for lambda handler function.Getting an error to specify deployment stage for Lambda function,will be sending an email to AWS support to get more info on that.
