public class UserView {

    public UserView(int userID) {
        // when we load the UI, we don't want to freeze an app while waiting for a long network call
        createUI("...", "...");

        // we give our inner class a way to call our method once the thread has collected the data (this)
        ProfileLoader thread = new ProfileLoader(userID, this);
        thread.start();
    }

    private static class ProfileLoader extends Thread {
        private final int userID;
        private final UserView userView;

        public ProfileLoader(int userID, UserView view) { this.userID = userID; this.userView = view; }

        @Override
        public void run() {
            String name = Network.findUserNameById(userID);
            String bio = Network.findUserBioById(userID);
            userView.updateViewWithNewDetails(name, bio);
        }
    }

    // pretend these are real functions
    private static void createUI(String name, String bio) {}
    private void updateViewWithNewDetails(String name, String bio) {}
    public static void main(String args[]) { UserView view = new UserView(5); }
}
