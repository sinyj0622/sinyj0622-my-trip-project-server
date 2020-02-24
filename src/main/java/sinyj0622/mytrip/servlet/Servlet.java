package sinyj0622.mytrip.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public interface Servlet {

  default void service(ObjectInputStream in, ObjectOutputStream out) throws Exception{};
  
  default void service(Scanner in, PrintStream out) throws Exception{};
}
