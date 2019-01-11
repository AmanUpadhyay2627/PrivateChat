package ammu.com.privatechat.model.user;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

import ammu.com.privatechat.model.ModelBase;

public class UserDetail extends ModelBase {

    public String displayName;
    public String email;
    public String profileImage;

    public UserDetail(){

    }

    public UserDetail(String displayName, String email, String profileImage){
        this.displayName = displayName;
        this.profileImage = profileImage;
        this.email = email;

    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", displayName);
        result.put("author", displayName);
        result.put("title", displayName);
        result.put("body", displayName);
        result.put("starCount", displayName);
        result.put("stars", displayName);

        return result;
    }
}
