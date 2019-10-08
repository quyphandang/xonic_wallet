package io.contentos.android.sdk;

import java.util.Random;

public class Network {
    private final String host;
    private final int port;
    private final String name;

    private Network(String host, int port, String name) {
    //public Network(String host, int port, String name) {
        this.host = host;
        this.port = port;
        this.name = name;
    }

    public Wallet wallet() {
        return new Wallet(this.host, this.port, this.name);
    }

    private static final String[] mainNodes = {
            "34.207.44.234",
            "34.206.192.70",
            //"34.195.63.116",
    };
    public static final Network Main = new Network(
            mainNodes[new Random().nextInt(mainNodes.length)],
           // "34.195.63.116",
            8888,
            "main"
    );
}
