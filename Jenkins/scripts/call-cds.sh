

#!/bin/bash

#cambiar a env var in jenkins
TOKEN_URL=$TOKEN_URL 

bodyAuth="grant_type=client_credentials"

responseAuth=$(curl -H "Content-Type: application/x-www-form-urlencoded" -H "Accept-Charset: UTF-8" \
        -X POST \
        -u "${CLIENT_ID}:${CLIENT_SECRET}" \
        -d "$bodyAuth" \
        --insecure \
        "$TOKEN_URL")


access_token=$(echo "$responseAuth" | jq -r '.access_token')
echo $access_token
bearerToken="Bearer $access_token"

#url to list applications elegible for promotion
listApplication=$(curl -H 'dmip-tenant-id: 1' -H 'dmip-application-name: TestApplication' \
        -X GET \
        -H "Authorization: $bearerToken" \
        --insecure \
        "$INSTANCE_DMPS_URL:31443/dmip-gw/dmip/api/application/promotion-eligible?offset=1&limit=15")

# jSessionId=$(echo "$responseSession" | grep 'JSESSIONID'| awk '{print $7}')
# XSRFTOKEN=$(echo "$responseSession" | grep 'XSRFTOKEN'| awk '{print $7}')

# bodyApplication='{"payload": "{\"device\":{\"deviceId\":\"3\",\"ipAddress\":\"1.127.0.1\"},\"applicationId\":\"12\"}"}'
# responseCDS=$(curl -H "Content-Type: application/json" -H "Authorization: Bearer ${ENCODED_TOKEN}" -H "X-XSRF-TOKEN: ${XSRFTOKEN}" \
#         -X POST \
#         -d "$bodyApplication" \
#         --cookie "$responseSession" \
#         "$GATEWAY_URL/feature-store-adapter-service/v2/data/Application" -w '%{http_code}')

echo "La respuesta es: ${listApplication}"