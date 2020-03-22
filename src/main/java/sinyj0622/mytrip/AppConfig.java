package sinyj0622.mytrip;

import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "sinyj0622.mytrip")
public class AppConfig {

	  public AppConfig() {
	    System.out.println("AppConfig 객체 생성!");
	  }

	 
}
