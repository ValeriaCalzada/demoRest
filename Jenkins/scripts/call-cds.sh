

#!/bin/bash

TOKEN_URL="https://iam-svc.dms.uset2.ficoanalyticcloud.com/openam/oauth2/access_token" 

body="client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRET}&grant_type=client_credentials&realm=/FicoAnalyticCloud"
echo $body

response=$(curl -H "Content-Type: application/x-www-form-urlencoded" -H "Accept-Charset: UTF-8" \
        -X POST \
        -d "$body" \
        "$TOKEN_URL")

echo $response

ENCODED_TOKEN=$(echo -n "$response.access_token" | base64)

echo $ENCODED_TOKEN