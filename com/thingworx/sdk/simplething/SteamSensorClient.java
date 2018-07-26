
package com.thingworx.sdk.simplething;

import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.communications.client.things.VirtualThing;
import com.thingworx.communications.client.things.VirtualThingPropertyChangeEvent;
import com.thingworx.communications.client.things.VirtualThingPropertyChangeListener;
import com.thingworx.communications.common.SecurityClaims;
import com.thingworx.metadata.PropertyDefinition;

import java.util.List;

public class SteamSensorClient extends ConnectedThingClient {
    private static String url = "ws://pp-1804271345f2.portal.ptc.io:80/Thingworx/WS";
    private static String appKey = "f76e9513-0bbc-4b33-af7f-09e5ea959504";
    private static String id = "1";
    private static String name = "TestThing";

    public SteamSensorClient(ClientConfigurator config) throws Exception {
        super(config);
    }

    public static void main(String[] args) throws Exception {

    }

    public static void connect(String uri, String appKey, String id, String name, List<PropertyDefinition> property) throws Exception {
        ClientConfigurator config = new ClientConfigurator();
        config.setUri(uri);
        config.setReconnectInterval(15);
        SecurityClaims claims = SecurityClaims.fromAppKey(appKey);
        config.setSecurityClaims(claims);
        config.setName(name);
        config.setAsSDKType();
        config.ignoreSSLErrors(true);
        SteamSensorClient client = new SteamSensorClient(config);
        final SteamThing thing = new SteamThing(name + id, name + " #" + "SN000" + id, name + id, client, property);
        client.bindThing(thing);
        thing.addPropertyChangeListener(new VirtualThingPropertyChangeListener() {
            @Override
            public void propertyChangeEventReceived(VirtualThingPropertyChangeEvent evt) {
                System.out.println();
                System.out.println(String.format("%s:%s", evt.getPropertyDefinition().getName(), evt.getPrimitiveValue().getValue()));
                System.out.println();
            }
        });

        client.start();

        while (!client.isShutdown()) {
            if (client.isConnected()) {
                thing.processScanRequest();
            }
            Thread.sleep(250);
        }
    }
}
