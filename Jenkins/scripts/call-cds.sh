

#!/bin/bash

#cambiar a env var in jenkins
TOKEN_URL=$TOKEN_URL 

body="client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRET}&grant_type=client_credentials&realm=/FicoAnalyticCloud"

response=$(curl -H "Content-Type: application/x-www-form-urlencoded" -H "Accept-Charset: UTF-8" \
        -X POST \
        -d "$body" \
        "$TOKEN_URL")

ENCODED_TOKEN=$(echo -n "$response.access_token" | base64)

curl  -v --http1.1 -H "Content-Type: application/json" -H "Authorization: Bearer ${ENCODED_TOKEN}"\
        -X POST \
        "$CDS_INIT_URL"