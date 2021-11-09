package u21g;


public class User {
    
        static final int CUSTOMER = 0;
        static final int ADMIN = 1;
        private int uriID;
        private String userName;
        private String password;
        private String email;
        private String firstName;
        private String lastName;
        private int admin;

        public User () {
            this.uriID = 0;
            this.userName = "0";
            this.password = "0";
            this.email = "0";
            this.firstName = "0";
            this.lastName = "0";
            this.admin = -1;
        };
    
        public User (int uriID, String firstName, String lastName, String username, String password, String email, int admin){
            this.uriID = uriID;
            this.userName = username;
            this.password = password;
            this.email = email;
            this.firstName = firstName;
            this.lastName = lastName;
            this.admin = admin;
        }
    
        public int getUriID () {
            return uriID;
        }
        public void setUriID (int uriID) {
            this.uriID = uriID;
        }
        public String getUserName () {
            return userName;
        }
        public void setUserName (String userName) {
            this.userName = userName;
        }
        public String getPassword () {
            return password;
        }
        public void setPassword (String password) {
            this.password = password;
        }
    
        public String getEmail () {
            return email; 
        }
    
        public void setEmail (String email) {
            this.email = email;
        }
    
        public String getFirstName () {
            return firstName;
        }
    
        public void setFirstName (String firstName) {
            this.firstName = firstName;
        }
    
        public String getLastName () {
            return lastName;
        }
    
        public void setLastName (String lastName) {
            this.lastName = lastName;
        }
        public int getAdmin() {
            return admin;
        }
        public void setAdmin (int admin) {
            this.admin = admin;
        }
    }