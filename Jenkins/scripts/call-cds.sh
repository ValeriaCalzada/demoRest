

#!/bin/bash

TOKEN_URL="https://iam-svc.dms.uset2.ficoanalyticcloud.com/openam/oauth2/access_token" 
echo $CLIENT_ID

body="client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRET}&grant_type=client_credentials&realm=/FicoAnalyticCloud"

response=$(curl -H "Content-Type: application/x-www-form-urlencoded" \
        -X POST \
        -d "$body" \
        "$TOKEN_URL")

echo $response