package sinyj0622.mytrip.context;

import java.util.Map;

public interface ApplicationContextListener {

	void ContextInitialized(Map<String, Object> context);
	
	void ContextDestroyed(Map<String, Object> context);
}
