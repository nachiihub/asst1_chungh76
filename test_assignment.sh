#!/usr/bin/expect

################################## INSTRUCTIONS ########################################
# This script is used to test your MIE250 assignment, ensuring correct naming convention,
# that itcompiles, and can accept inputs.
# Please place it inside the same directory as your asst1_utorid.java file and run
# according to instructions below. If your assignment works correctly, it will save the  
# output of the program in a .txt file you can review.

# Usage: ./test_assignment.sh <input_file>
# Example: ./test_assignment.sh my_test_input.txt
########################################################################################


# Get the input file from command line argument and .java in the current directory
set inputFile [lindex $argv 0]
set javaFiles [glob -nocomplain *.java]

# Check if exactly one Java file exists
if {[llength $javaFiles] == 0} {
    puts "Error: No Java file found in current directory"
    exit 1
}
if {[llength $javaFiles] > 1} {
    puts "Error: Multiple Java files found. Only one submission file should exist."
    puts "Found: $javaFiles"
    exit 1
}
set javaFile [lindex $javaFiles 0]

# Check naming convention: asst1_*.java
if {![string match "asst1_*.java" $javaFile]} {
    puts "Error: Java file must follow naming convention: asst1_utorid.java"
    puts "Found: $javaFile"
    exit 1
}

# Extract the filename without the extension and compile
set baseName [file rootname [file tail $javaFile]]
spawn javac $javaFile
expect {
    eof {
        set compileStatus [lindex [wait] 3]
        if {$compileStatus != 0} {
            puts "Compilation failed"
            exit 1
        }
    }
}

# Run the Java program with input file and save output
spawn bash -c "java $baseName < $inputFile > java_output.txt"
expect {
    eof {
        set runStatus [lindex [wait] 3]
        if {$runStatus != 0} {
            puts "Runtime error"
            exit 1
        }
    }
}
puts "Test completed. Output saved to java_output.txt" 