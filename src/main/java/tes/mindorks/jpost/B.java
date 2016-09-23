package tes.mindorks.jpost;

import com.mindorks.jpost.JPost;
import com.mindorks.jpost.annotations.SubscribeMsg;

/**
 * Created by janisharali on 23/09/16.
 */
public class B {

    public B() {
        Thread.currentThread().setName("Thread Main");
        JPost.getBroadcastCenter().addSubscriber(this);
    }

    @SubscribeMsg(channelId = 2)
    private void onMsg(String name){
        System.out.println(Thread.currentThread().getName());
        System.out.println("B onMsg " + name);
    }
}
