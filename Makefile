default: all

compile:
	javac asst1_chungh76.java

run:
	java asst1_chungh76

all: compile run

clean:
	rm -f asst1_chungh76.class

