#!/bin/bash

# Define the URL and the Bearer token
URL="https://pokeapi.co/api/v2/pokemon/${params.POKEMON_NAME}"  # Replace with the actual URL
BEARER_TOKEN="your_bearer_token_here"   # Replace with the actual token

# Define the data to be sent in the body (URL-encoded format)
DATA="param1=value1&param2=value2"

echo $URL