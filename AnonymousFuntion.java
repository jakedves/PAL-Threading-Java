public class UserView {

    public UserView(int userID) {
        // when we load the UI, we don't want to freeze an app while waiting for a long network call
        createUI("...", "...");

        // we create a function with no name (anonymous), and pass that in directly
        Thread profileLoader = new Thread(() -> {
            String name = Network.findUserNameById(userID);
            String bio = Network.findUserBioById(userID);
            this.updateViewWithNewDetails(name, bio);
        });

        profileLoader.start();
    }

    // pretend these are real functions
    private static void createUI(String name, String bio) {}
    private void updateViewWithNewDetails(String name, String bio) {}
    public static void main(String args[]) { UserView view = new UserView(5); }
}
