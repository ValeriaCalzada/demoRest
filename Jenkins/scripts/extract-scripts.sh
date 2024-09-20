
# Loop through each file and run different xmlstarlet commands
for file in $listJobs; do
    echo "Processing $file..."

    # First extraction (e.g., extracting from <parameter>)
    xmlstarlet sel -t -m "//parameter/value" -v . -n "Jobs/$file" > "extracted_value_param_from_${file}.js"

    # Second extraction (e.g., extracting from <custom-function-script>)
    xmlstarlet sel -t -m "//custom-function-script" -v . -n "Jobs/$file" > "extracted_script_from_${file}.js"

    xmlstarlet sel -t -m "//inline-script" -v . -n "Jobs/$file" > "extracted_script_from_${file}.py"

    # Add more extractions as needed
    # For example, extracting some <generated-field> tags:
    xmlstarlet sel -t -m "//function-script" -v . -n "Jobs/$file" > "extracted_function-script_from_${file}.js"
done