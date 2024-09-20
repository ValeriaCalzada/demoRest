
# Loop through each job and run different xmlstarlet commands

    echo "Processing $shJob..."

    # First extraction (e.g., extracting from <parameter>)
    xmlstarlet sel -t -m "//*[local-name()='value']" -v . -n "${WORKSPACE}/Jobs/$shJob.xml" > "extracted_value_param_from_${shJob}.js"

    # Second extraction (e.g., extracting from <custom-function-script>)
    xmlstarlet sel -t -m "//*[local-name()='custom-function-script']" -v . -n "${WORKSPACE}/Jobs/$shJob.xml" > "extracted_script_from_${shJob}.js"

    xmlstarlet sel -t -m "//*[local-name()='inline-script']" -v . -n "${WORKSPACE}/Jobs/$shJob.xml" > "extracted_script_from_${shJob}.py"

    # Add more extractions as needed
    # For example, extracting some <generated-field> tags:
    xmlstarlet sel -t -m "//*[local-name()='function-script']" -v . -n "${WORKSPACE}/Jobs/$shJob.xml" > "extracted_function-script_from_${shJob}.js"
