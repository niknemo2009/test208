package lr2;
import javax.naming.AuthenticationException;
import java.util.HashMap;
class AutorizationException{
    public AutorizationException(String error){
        System.out.println(error);
    }
}
public class User {
    static HashMap<String,String> map = new HashMap<>();
    static{map.put("Pet9", "111");}
    static{map.put("Jeekyll", "222");}
    static{map.put("mario", "333");}

    public void CheckUser(String login, String password) throws AuthenticationException {
        for(String item: map.keySet()){
           if(item == login ){
               if(map.get(item).equals(password)){
                   System.out.println("success");
               }
               else {
                   throw new AuthenticationException("Invalid password");
               }
           }
        }
    }


}
