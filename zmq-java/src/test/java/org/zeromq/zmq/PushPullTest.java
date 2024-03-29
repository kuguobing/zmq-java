package org.zeromq.zmq;

import static org.junit.Assert.assertEquals;
import static org.zeromq.zmq.ZMQ.ZMQ_PULL;
import static org.zeromq.zmq.ZMQ.ZMQ_PUSH;

import org.junit.Test;

public class PushPullTest {
  @Test
  public void testSimplePushPull() {
    try (final Socket pull = new Socket(ZMQ_PULL);
         final Socket push = new Socket(ZMQ_PUSH)) {
      pull.bind("tcp://*:7210");
      push.connect("tcp://127.0.0.1:7210");

      final String expected = "hello";
      push.sendStringUtf8(expected);
      final String actual = pull.receiveStringUtf8();

      assertEquals(expected, actual);
    }
  }
}
