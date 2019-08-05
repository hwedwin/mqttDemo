package com.example.mqtt.netty.handler;

import com.example.mqtt.netty.behavior.Processor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import org.springframework.stereotype.Component;

@Component
public class BrokerHandler extends SimpleChannelInboundHandler<MqttMessage> {

    private Processor mqttProcessor;

    public BrokerHandler(Processor processor) {
        mqttProcessor = processor;
    }

    protected void channelRead0(ChannelHandlerContext ctx, MqttMessage msg) {
         switch (msg.fixedHeader().messageType()) {
             case CONNECT:
                 mqttProcessor.connect().processConnect(ctx.channel(), (MqttConnectMessage) msg);
                 break;
             case CONNACK:
                 break;
             case PUBLISH:
                 mqttProcessor.publish().processPublish(ctx.channel(), (MqttPublishMessage) msg);
                 break;
             case PUBACK:
                 break;
             case PUBREC:
                 break;
             case PUBREL:
                 break;
             case PUBCOMP:
                 break;
             case SUBSCRIBE:
                 break;
             case SUBACK:
                 break;
             case UNSUBSCRIBE:
                 break;
             case UNSUBACK:
                 break;
             case PINGREQ:
                 break;
             case PINGRESP:
                 break;
             case DISCONNECT:
                 break;
             default:
                 break;
         }
    }


}