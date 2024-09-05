

#!/bin/bash

#cambiar a env var in jenkins
TOKEN_URL=$TOKEN_URL 

bodyAuth="client_id=${CLIENT_ID}&client_secret=${CLIENT_SECRET}&grant_type=client_credentials&realm=/FicoAnalyticCloud"

responseAuth=$(curl -H "Content-Type: application/x-www-form-urlencoded" -H "Accept-Charset: UTF-8" \
        -X POST \
        -d "$bodyAuth" \
        "$TOKEN_URL")

access_token=$(echo "$responseAuth" | jq -r '.access_token')
ENCODED_TOKEN=$(echo -n "$access_token" | base64)
responseSession=$(curl -H "Content-Type: application/json" -H "Authorization: Bearer ${ENCODED_TOKEN}" \
        -X GET \
        -c - "$GATEWAY_URL/init" -w '%{http_code}')

jSessionId=$(echo "$responseSession" | grep 'JSESSIONID'| awk '{print $7}')
XSRFTOKEN=$(echo "$responseSession" | grep 'XSRFTOKEN'| awk '{print $7}')

bodyApplication='{"payload": "{\"device\":{\"deviceId\":\"3\",\"ipAddress\":\"1.127.0.1\"},\"applicationId\":\"12\"}"}'
responseCDS=$(curl -H "Content-Type: application/json" -H "Authorization: Bearer ${ENCODED_TOKEN}" -H "X-XSRF-TOKEN: ${XSRFTOKEN}" \
        -X POST \
        -d "$bodyApplication" \
        --cookie <echo "$responseSession">
        "$GATEWAY_URL/feature-store-adapter-service/v2/data/Application" -w '%{http_code}')

echo "La respuesta es: ${responseCDS}"