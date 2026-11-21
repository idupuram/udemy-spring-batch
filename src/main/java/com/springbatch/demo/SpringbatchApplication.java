package com.springbatch.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableBatchProcessing
public class SpringbatchApplication {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Step firstStep() {
		return stepBuilderFactory.get("step1")
				.tasklet(new Tasklet() {
					@Override
					public RepeatStatus execute(
							StepContribution contribution,
							ChunkContext chunkContext) throws Exception {

						System.out.println("Step 1 completed");

						return RepeatStatus.FINISHED;
					}
				})
				.build();
	}
	@Bean	public Job printJob(){
		return jobBuilderFactory.get("first print job")
				.start(firstStep())
				.build();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringbatchApplication.class, args);
//		SpringApplication app =
//				new SpringApplication(SpringbatchApplication.class);
//
//		app.setWebApplicationType(WebApplicationType.NONE);
//
//		app.run(args);
	}

}
