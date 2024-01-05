package afonso.h.santos.core;

public class Properties {
	
	public static boolean CLOSE_BROWSER = true;
	
	public static Browsers BROWSER = Browsers.CHROME;

	public static ExecutionType EXECUTION_TYPE = ExecutionType.CLOUD;
	
	public enum Browsers {
		CHROME,
		FIREFOX
	}
	
	public enum ExecutionType {
		LOCAL,
		GRID,
		CLOUD
	}
	
}
