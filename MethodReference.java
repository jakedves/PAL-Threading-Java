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

