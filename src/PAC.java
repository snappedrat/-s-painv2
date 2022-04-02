public class PAC {
    // PublicAutenticatorClass!

    String username;
    String password;


    public int authenticate(String user, String pass){
        if(this.username.equals(user) && this.password.equals(pass)){
            return 1;
        }
        else{
            return 0;
        }
    }

}
