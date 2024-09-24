#!/bin/bash
# Loop through each job and run different xmlstarlet commands


    # First extraction (e.g., extracting from <parameter>)
    #set -x
    #xmlstarlet sel -t -m "//*[local-name()='aggregation-field-or-function-CUSTOM" -v . -n "${WORKSPACE}/Jobs/${shJob}.xml" > "${WORKSPACE}/Jenkins/scripts/extracted_value_param_from_${shJob}.js"
    # Second extraction (e.g., extracting from <custom-function-script>)
    #xmlstarlet sel -t -m "//*[local-name()='custom-function-script']" -v . -n "${WORKSPACE}/Jobs/$shJob.xml" > "${WORKSPACE}/Jenkins/scripts/extracted_script_from_${shJob}.js"

    #xmlstarlet sel -t -m "//*[local-name()='inline-script']" -v . -n "${WORKSPACE}/Jobs/$shJob.xml" > "${WORKSPACE}/Jenkins/scripts/extracted_script_from_${shJob}.py"

    # Add more extractions as needed
    # For example, extracting some <generated-field> tags:
    #xmlstarlet sel -t -m "//*[local-name()='functionScript']" -v . -n "${WORKSPACE}/Jobs/$shJob.xml" > "${WORKSPACE}/Jenkins/scripts/extracted_function-script_from_${shJob}.js"

    #set +x


    extract_scripts(){
        local tag=$1
        local file_prefix=$2
        local file_extension=$3

        local counter=1
        set -x
        xmlstarlet sel -t -m "//*[local-name()='$tag']/text()" -v . -n "${WORKSPACE}/Jobs/$shJob.xml" |
        while IFS= read -r script_content; do

            script_content=$(echo "$script_content" | sed -e '$d' -e '/^\s*$/d' | awk '{gsub(/\n/, " "); print}')

            # Check if script_content is not empty
            if [[ -n "$script_content" ]]; then
                script_file="${WORKSPACE}/Jenkins/scripts/${file_prefix}_script_from_${shJob}_${counter}.${file_extension}"
                echo "$script_content" > "$script_file"
                counter=$((counter+1))
            fi


            #script_file="${WORKSPACE}/Jenkins/scripts/${file_prefix}_script_from_${shJob}_${counter}.${file_extension}"
            #echo "$script_content" > "$script_file"
            #counter=$((counter+1))
        done
        # < <(xmlstarlet sel -t -m "//*[local-name()='$tag']" -v . -n "${WORKSPACE}/Jobs/$shJob.xml" | tr '\0' '\n')
        set +x
    }

    echo "Processing $shJob..."
    extract_scripts "inline-script" "python_model" "py"
    extract_scripts "aggregation-field-or-function-CUSTOM" "custom-function" "js"
    extract_scripts "functionScript" "function-script" "js"
    extract_scripts "custom-function-script" "custom-function-script" "js"