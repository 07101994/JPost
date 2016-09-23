package tes.mindorks.jpost;

import com.mindorks.jpost.JPost;
import com.mindorks.jpost.annotations.SubscribeMsg;
import com.mindorks.jpost.exceptions.AlreadyExistsException;

/**
 * Created by janisharali on 23/09/16.
 */
public class C {

    public C() {
        Thread.currentThread().setName("Thread Main");
        A a = new A();
        B b = new B();
        try {
            JPost.getBroadcastCenter().createPrivateChannel(this, 2);
//            JPost.getBroadcastCenter().createPublicChannel(2);
        }catch (AlreadyExistsException e){
            e.printStackTrace();
        }
        JPost.getBroadcastCenter().addSubscriberAsync(2, a, 2);
        JPost.getBroadcastCenter().addSubscriberAsync(this, 2, b, 3);
        JPost.getBroadcastCenter().broadcastAsync(this, 2, "Ali calling 2", this.hashCode());

//        JPost.getBroadcastCenter().terminateChannel(2);
//        JPost.getBroadcastCenter().broadcastAsync(2, "Ali calling");
        JPost.shutdown();
    }

    @SubscribeMsg(channelId = 2)
    private void onMsg(String name){
        System.out.println(Thread.currentThread().getName());
        System.out.println("C onMsg " + name);
    }
}
