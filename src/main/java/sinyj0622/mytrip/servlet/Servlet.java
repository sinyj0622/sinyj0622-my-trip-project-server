package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface Servlet {

  void service(ObjectInputStream in, ObjectOutputStream out) throws Exception;
}
