# Task Tracker CLI
This is a CLI application to add, update, delete, list and mark your tasks as done.

## More Details
- this was developed in Java, without the use of any external libraries
- the list of tasks is stored in a JSON file and all commands except `list` updates this file 
- regex was used to parse the JSON file since there is no nesting in the Task entry
- this was a [learning exercise](https://roadmap.sh/projects/task-tracker) to get familiar with coding in Java

## Quickstart
Environment: Java 21\
External Libraries: N/A\
`java-compile` alias: `$ javac -d out/production/cli-task-tracker src/main/java/Main.java`\
`java-run` alias: `$ java -cp out/production/cli-task-tracker main.java.Main`

- Add a new task\
`$ java-run add "Hello World"`


- Update a Task\
`$ java-run update 1 "20 eggs"`


- Delete a Task\
`$ java-run delete 3`


- Mark a task as in progress or done\
`$ java-run mark-in-progress 1`\
`$ java-run mark-done 2`


- List all tasks\
`$ java-run list`


- List tasks by status\
`$ java-run list done`\
`$ java-run list todo`\
`$ java-run list in-progress`
