package emil.stupiec;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.TerminateInstancesRequest;
import com.amazonaws.services.ec2.model.TerminateInstancesResult;

public class Instance_terminator {
	private static AmazonEC2 ec2;
	private static TerminateInstancesResult terminate_instances_result;
	private static TerminateInstancesRequest terminate_instances_request;
	public String terminate_instance(String instance_id) throws Exception{
		initialize_aws_variables(instance_id);
		return terminate();
	}
	private static void initialize_aws_variables(String instance_id_to_terminate) throws Exception {
		AWSCredentials credentials = new PropertiesCredentials(
				Instance_runner.class.getResourceAsStream("../../AwsCredentials.properties"));
        ec2 = new AmazonEC2Client(credentials);
        ec2.setEndpoint("ec2.eu-west-1.amazonaws.com");
		List<String> instance_ids=new ArrayList<String>();
		instance_ids.add(instance_id_to_terminate);
		terminate_instances_request=new TerminateInstancesRequest(instance_ids);
	}
	private String terminate(){
        terminate_instances_result=ec2.terminateInstances(terminate_instances_request);
		return terminate_instances_result.toString();
	}	
}
