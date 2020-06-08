# java-repo
- Running one thread after another

- I was studying on threads in Java, then I thought sometimes we may want a thread to run after another one is finished.
- Sometimes, you don't want your variable to decrease if it has not been incremented yet.
- So, this code uses 2 threads. First one increases the variable, and once it is increased by one, the other thread decreases its value by one.
