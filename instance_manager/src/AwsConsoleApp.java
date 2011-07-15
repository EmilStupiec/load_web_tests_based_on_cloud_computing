import emil.stupiec.Instance_runner;
import emil.stupiec.Instance_terminator;

public class AwsConsoleApp {

    public static void main(String[] args) throws Exception {
    	Instance_runner instance_runner=new Instance_runner();
    	String instance_id=instance_runner.run_instance();
    	System.out.print(instance_id);
    		Thread.currentThread();
			Thread.sleep(30000);
    	Instance_terminator instance_terminator=new Instance_terminator();
    	instance_terminator.terminate_instance(instance_id);
    }
}
