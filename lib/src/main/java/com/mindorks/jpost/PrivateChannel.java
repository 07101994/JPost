package com.mindorks.jpost;

import com.mindorks.jpost.annotations.SubscribeMsg;
import com.mindorks.jpost.core.*;
import com.mindorks.jpost.exceptions.AlreadyExistsException;
import com.mindorks.jpost.core.ChannelPost;
import com.mindorks.jpost.exceptions.IllegalStateException;
import com.mindorks.jpost.exceptions.InvalidPropertyException;
import com.mindorks.jpost.exceptions.NullObjectException;

import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by janisharali on 22/09/16.
 */
public class PrivateChannel extends PublicChannel{

    private WeakReference<Object> channelOwnerRef;

    public PrivateChannel(WeakReference<Object> channelOwnerRef, Integer channelId, ChannelType type, ChannelState state) {
        super(channelId, type, state);
        this.channelOwnerRef = channelOwnerRef;
    }

    public WeakReference<Object> getChannelOwnerRef() {
        return channelOwnerRef;
    }

    public synchronized void removeSubscriber(Integer subscriberId) throws NullObjectException,InvalidPropertyException {
        if(subscriberId == null){
            throw new NullObjectException("subscriberId is null");
        }
        if(getSubscriberMap().containsKey(subscriberId)){
            getSubscriberMap().remove(subscriberId);
        }else{
            throw new InvalidPropertyException("Subscriber with subscriberId " + subscriberId + " do not exists");
        }
    }
}
