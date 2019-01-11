package ammu.com.privatechat.utils.firebase;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import ammu.com.privatechat.model.user.UserDetail;

public class FireBaseHelper {
    private DatabaseReference dataReference;
    private final static String SEPARATOR = "___";
    private final static String CHATS_PATH = "chats";
    private final static String USERS_PATH = "users";
    public final static String CONTACTS_PATH = "contacts";

    public void createUser(UserDetail userDetail, OnCompleteListener completeListener) {
        dataReference.child(USERS_PATH)
                .child(getAuthUserUid())
                .setValue(userDetail)
                .addOnCompleteListener(completeListener);
    }

    public void registerUser(String email, String password, OnCompleteListener onCompleteListener, OnFailureListener onFailureListener) {
        getFireBaseAuth().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener)
                .addOnFailureListener(onFailureListener);
    }

    public void loginUser(String email, String password, OnCompleteListener onCompleteListener, OnFailureListener onFailureListener) {
        getFireBaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(onCompleteListener)
                .addOnFailureListener(onFailureListener);
    }

    private static class SingletonHolder {
        private static final FireBaseHelper INSTANCE = new FireBaseHelper();
    }

    public static FireBaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public FireBaseHelper() {
        dataReference = FirebaseDatabase.getInstance().getReference();
    }

    public DatabaseReference getDataReference() {
        return dataReference;
    }

    public String getAuthUserEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String email = null;
        if (user != null) {
            email = user.getEmail();
        }
        return email;
    }

    public String getAuthUserUid() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = null;
        if (user != null) {
            uid = user.getUid();
        }
        return uid;
    }

    public static boolean checkIsLoggedIn() {
        return FireBaseHelper.getInstance().getAuthUserEmail() != null;
    }

    public DatabaseReference getUserReference(String email) {
        DatabaseReference userReference = null;
        if (email != null) {
            String emailKey = email.replace(".", "_");
            userReference = dataReference.getRoot().child(USERS_PATH).child(emailKey);
        }
        return userReference;
    }

    public FirebaseAuth getFireBaseAuth() {
        return FirebaseAuth.getInstance();
    }

    public DatabaseReference getMyUserReference() {
        return getUserReference(getAuthUserEmail());
    }

    public DatabaseReference getContactsReference(String email) {
        return getUserReference(email).child(CONTACTS_PATH);
    }

    public DatabaseReference getMyContactsReference() {
        return getContactsReference(getAuthUserEmail());
    }

    public DatabaseReference getOneContactReference(String mainEmail, String childEmail) {
        String childKey = childEmail.replace(".", "_");
        return getUserReference(mainEmail).child(CONTACTS_PATH).child(childKey);
    }

    public DatabaseReference getChatsReference(String receiver) {
        String keySender = getAuthUserEmail().replace(".", "_");
        String keyReceiver = receiver.replace(".", "_");

        String keyChat = keySender + SEPARATOR + keyReceiver;
        if (keySender.compareTo(keyReceiver) > 0) {
            keyChat = keyReceiver + SEPARATOR + keySender;
        }
        return dataReference.getRoot().child(CHATS_PATH).child(keyChat);
    }

    public void changeUserConnectionStatus(boolean online) {
        if (getMyUserReference() != null) {
            Map<String, Object> updates = new HashMap<String, Object>();
            updates.put("online", online);
            getMyUserReference().updateChildren(updates);

            notifyContactsOfConnectionChange(online);
        }
    }

    public void notifyContactsOfConnectionChange(final boolean online, final boolean signoff) {
        final String myEmail = getAuthUserEmail();
        getMyContactsReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot child : snapshot.getChildren()) {
                    String email = child.getKey();
                    DatabaseReference reference = getOneContactReference(email, myEmail);
                    reference.setValue(online);
                }
                if (signoff) {
                    FirebaseAuth.getInstance().signOut();
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }
        });
    }

    public void notifyContactsOfConnectionChange(boolean online) {
        notifyContactsOfConnectionChange(online, false);
    }

    public void signOff() {
        // notifyContactsOfConnectionChange(User.OFFLINE, true);
    }
}