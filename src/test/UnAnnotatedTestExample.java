package test;

import org.testng.annotations.Test;

public class UnAnnotatedTestExample {

	@Test
	public void testWithAnnotation() 
	{
		System.out.println("This test is annotated");
	}

	public void testWithoutAnnotation() 
	{
		System.out.println("This test is not annotated");
	}

}
