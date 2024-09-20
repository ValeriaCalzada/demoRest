
# Loop through each job and run different xmlstarlet commands

    echo "Processing $job..."

    # First extraction (e.g., extracting from <parameter>)
    xmlstarlet sel -t -m "//parameter/value" -v . -n "Jobs/$job" > "extracted_value_param_from_${job}.js"

    # Second extraction (e.g., extracting from <custom-function-script>)
    xmlstarlet sel -t -m "//custom-function-script" -v . -n "Jobs/$job" > "extracted_script_from_${job}.js"

    xmlstarlet sel -t -m "//inline-script" -v . -n "Jobs/$job" > "extracted_script_from_${job}.py"

    # Add more extractions as needed
    # For example, extracting some <generated-field> tags:
    xmlstarlet sel -t -m "//function-script" -v . -n "Jobs/$job" > "extracted_function-script_from_${job}.js"
