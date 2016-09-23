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
//            JPost.getBroadcastCenter().createPrivateChannel(this,2);
            JPost.getBroadcastCenter().createPublicChannel(2);
        }catch (AlreadyExistsException e){
            e.printStackTrace();
        }
        JPost.shutdown();
        JPost.reboot();
        JPost.getBroadcastCenter().addSubscriber(2, a, 1);
        JPost.getBroadcastCenter().addSubscriber(2, b, 2);
        JPost.getBroadcastCenter().stopChannel(2);
        JPost.getBroadcastCenter().reopenChannel(2);
        JPost.getBroadcastCenter().broadcastAsync(2, "Ali calling 2");
//        JPost.getBroadcastCenter().terminateChannel(2);
//        JPost.getBroadcastCenter().broadcastAsync(2, "Ali calling");
        JPost.shutdown();
    }

    @SubscribeMsg(channelId = 1)
    private void onMsg(String name){
        System.out.println(Thread.currentThread().getName());
        System.out.println("A onMsg " + name);
    }
}
