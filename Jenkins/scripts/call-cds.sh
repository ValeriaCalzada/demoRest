

#!/bin/bash

#cambiar a env var in jenkins
TOKEN_URL=$TOKEN_URL 

body="client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRET}&grant_type=client_credentials&realm=/FicoAnalyticCloud"

responseAuth=$(curl -H "Content-Type: application/x-www-form-urlencoded" -H "Accept-Charset: UTF-8" \
        -X POST \
        -d "$body" \
        "$TOKEN_URL")

access_token=$(echo "$responseAuth" | jq -r '.access_token')
ENCODED_TOKEN=$(echo -n "$access_token" | base64)
responseSession=$(curl -H "Content-Type: application/json" -H "Authorization: Bearer ${ENCODED_TOKEN}" \
        -X GET \
        -c - "$CDS_INIT_URL" -w '%{http_code}')

jSessionId=$(echo "responseSession" | grep 'JSESSIONID'| awk '{print $7}')


echo "La respuesta es: ${jSessionId}"