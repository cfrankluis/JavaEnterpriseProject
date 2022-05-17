package application.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.waiters.WaiterResponse;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.waiters.S3Waiter;

public class S3Controller {

	private static String BUCKET = "buckylebucket";

	/**
	 * Method takes input containing a String value dictating which folder the image
	 * will be stored in in the bucket, a String value holding the uploaded file
	 * name, an InputStream object holding the image file, and a session. The file
	 * name is appended to the format: ["ProfilePics/" + {Logged in User Id} +
	 * "/ProfilePic" + {original file type}] or ["PostPics/" + {Logged in User Id} +
	 * "/" + {original file name}] Then, using AWS S3 dependencies, a
	 * PutObjectRequest object is built with the S3 bucket name, the appended file
	 * name, and public read permissions. After that, method from S3Client adds the
	 * object via the request to the bucket.
	 * 
	 * S3Waiter is then implemented, that waits until either the file is
	 * successfully uploaded or it is determined that it will not succeed. If it
	 * succeeds, a message will be printed in the console stating that the file is
	 * ready.
	 * 
	 * @Author Dillon Meier
	 * @param
	 * 
	 */
	public static String uploadPic(String type, String fileName, InputStream inputStream, HttpSession session)
			throws S3Exception, AwsServiceException, SdkClientException, IOException {
		String newFileName;
		
		if (type.contentEquals("ProfilePic")) {
			newFileName = "ProfilePics/" 
						+ session.getAttribute("Session Id").toString() 
						+ "/ProfilePic" 
						+ fileName.substring(fileName.length()-4);
		}
		else {
			newFileName = "PostPics/" + session.getAttribute("Session Id").toString() + "/" + fileName;
		}
		
		
		S3Client client = S3Client.builder().build();
		PutObjectRequest request = PutObjectRequest.builder().bucket(BUCKET)
															 .key(newFileName)
															 .acl("public-read")
															 .build();

		client.putObject(request, RequestBody.fromInputStream(inputStream, inputStream.available()));

		
		S3Waiter waiter = client.waiter();
		HeadObjectRequest waitRequest = HeadObjectRequest.builder().bucket(BUCKET)
																   .key(newFileName)
																   .build();

		WaiterResponse<HeadObjectResponse> waitResponse = waiter.waitUntilObjectExists(waitRequest);

		
		waitResponse.matched().response().ifPresent(x -> {
			System.out.println("The file " + fileName + " is now ready.");
		});
		return "Your file has been uploaded Successfully!";
	}
}
