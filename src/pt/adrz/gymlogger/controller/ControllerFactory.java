package pt.adrz.gymlogger.controller;

public class ControllerFactory {

	public static final Controller getControllerByClass(Class actionClass) {
		
		try {
			Controller controller = (Controller)actionClass.newInstance();
			return controller;
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final Controller getControllerByFullClassName(String className) {

		try {
			String name = "com.adrz.gymlogger.controller" + className + "Controller";
			Class actionClass = Class.forName(name);
			return ControllerFactory.getControllerByClass(actionClass);
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
}
