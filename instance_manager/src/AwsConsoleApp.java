import emil.stupiec.Instance_runner;
import emil.stupiec.Instance_state;
import emil.stupiec.Instance_terminator;

public class AwsConsoleApp {
	private static Instance_runner instance_runner=new Instance_runner();
	private static Instance_state instance_state=new Instance_state();
	private static Instance_terminator instance_terminator;
	private static String instance_id;
	private static String state;
    public static void main(String[] args) throws Exception {
    	initialize_variables();
    	instance_id=instance_runner.run_instance();
    	state=instance_state.return_state(instance_id);
    	while(state.equals("running")){
    		Thread.currentThread();
    		Thread.sleep(5000);
    		state=instance_state.return_state(instance_id);
    	}
    	instance_terminator.terminate_instance(instance_id);
    }
    private static void initialize_variables(){
    	instance_runner=new Instance_runner();
    	instance_state=new Instance_state();
    	instance_terminator=new Instance_terminator();
    }
}
