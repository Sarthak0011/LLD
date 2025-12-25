package ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

interface ISubscriber {
    void getNotified();
}

interface IChannel {
    void subscribe(ISubscriber subscriber);
    void unsubscribe(ISubscriber subscriber);
    void notifySubscribers();
}

class Channel implements IChannel {

    private List<ISubscriber> subscribers;
    private String name;
    private String latestVideo;

    Channel(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        System.out.println("Subscribed by someone.");
        this.subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        System.out.println("Unsubscribed by someone.");
        this.subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for(ISubscriber subscriber: this.subscribers) {
            subscriber.getNotified();
        }
    }

    public void uploadVideo(String title) {
        this.latestVideo = title;
        System.out.println("Uploaded a video: " + title);
        notifySubscribers();
    }

    public String getName() {
        return this.name;
    }

    public String getLatestVideo() {
        return this.latestVideo;
    }
}

class Subscriber implements ISubscriber {
    private String name;
    private Channel channel;

    public Subscriber(String name, Channel channel) {
        this.name = name;
        this.channel = channel;
    }

    @Override
    public void getNotified() {
        System.out.println("Video uploaded by " + this.channel.getName());
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        Channel channel = new Channel("CodeCodeGo");

        Subscriber s1 = new Subscriber("Virat", channel);
        Subscriber s2 = new Subscriber("Rohit", channel);

        channel.subscribe(s1);
        channel.subscribe(s2);

        channel.uploadVideo("First Tutorial");

        channel.unsubscribe(s1);

        channel.uploadVideo("Second Tutorial");
    }
}
