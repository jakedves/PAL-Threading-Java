# Threading in Java

Examples of how to use Threads via 3 methods: Inner class, Anonymous functions, and Method references.

## The Thread Constructor

We can pass methods into the thread constructor, however you do not need to understand why or how at this point. Just use the syntax if you find it makes it easier to read your code.

To create a method:

```java
class Example {
    public void myFunction() {
        ...
    }
}
```

Then to make a thread, that runs ```myFunction```, we can say:

```java
class Outer {
    public Outer() {
        Example myExample = new Example();

        Thread thread = new Thread(myExample::myFunction);
        thread.start()
    }
}
```
Not that this doesn't have to be done in the constuctor of a class, and we can do it with a method inside a class itself by saying:
```java
Thread thread = new Thread(this::methodName);
thread.start()
```
## Full Example:

```java
public class UserView {
    private final int userID;

    public UserView(int userID) {
        // when we load the UI, we don't want to freeze an app while waiting for a long network call
        createUI("...", "...");

        this.userID = userID; // we need this now, as our function CANNOT take in any arguments

        // now we are saying find this.updateDetails(), and use that method in the Thread constructor
        Thread profileLoader = new Thread(this::updateDetails);

        profileLoader.start();
    }

    // This can be any method that we want to run asynchronously
    private void updateDetails() {
        String name = Network.findUserNameById(userID);
        String bio = Network.findUserBioById(userID);
        this.updateViewWithNewDetails(name, bio);
    }

    // pretend these are real functions
    private static void createUI(String name, String bio) {}
    private void updateViewWithNewDetails(String name, String bio) {}
    public static void main(String args[]) { UserView view = new UserView(5); }
}
```
*(as seen in MethodReference.java)*

