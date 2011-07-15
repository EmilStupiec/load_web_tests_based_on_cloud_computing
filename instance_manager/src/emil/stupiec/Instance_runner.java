package emil.stupiec;

import java.util.ArrayList;
import java.util.List;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeImagesRequest;
import com.amazonaws.services.ec2.model.DescribeImagesResult;
import com.amazonaws.services.ec2.model.Image;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.RunInstancesResult;

public class Instance_runner {
	private static AmazonEC2 ec2;
	private static DescribeImagesRequest describe_images_request;
	private static RunInstancesRequest run_instances_request;
	private static List<Image> images;
	private static List<String> image_ids;
	private static String image_id;
	private static DescribeImagesResult describe_images_result;
	public String run_instance() throws Exception{
		initialize_aws_variables();
		return run();
	}
	private static void initialize_aws_variables() throws Exception {
		AWSCredentials credentials = new PropertiesCredentials(
				Instance_runner.class.getResourceAsStream("../../AwsCredentials.properties"));
        ec2 = new AmazonEC2Client(credentials);
        ec2.setEndpoint("ec2.eu-west-1.amazonaws.com");
        image_id="ami-4090a634";
        image_ids=new ArrayList<String>();
        image_ids.add(image_id);
        describe_images_request=new DescribeImagesRequest();
        describe_images_request.setImageIds(image_ids);
        describe_images_result=ec2.describeImages(describe_images_request);
        images=describe_images_result.getImages();
        run_instances_request=new RunInstancesRequest(image_id,1,1);
	}
	private String run(){
		try{
			if(images.size()==1){
        		if(images.get(0).getImageId().equals(image_id)){
        			RunInstancesResult run_instances_result=ec2.runInstances(run_instances_request);
        			return run_instances_result.getReservation().getInstances().get(0).getInstanceId();
        		}
        	}
        }catch(AmazonServiceException amazon_service_exception){
                System.out.print("Caught Exception: " + amazon_service_exception.getMessage()+"\nReponse Status Code: " + amazon_service_exception.getStatusCode()+"\nError Code: " + amazon_service_exception.getErrorCode()+"\nRequest ID: " + amazon_service_exception.getRequestId());
        }
        return null;
	}
}
