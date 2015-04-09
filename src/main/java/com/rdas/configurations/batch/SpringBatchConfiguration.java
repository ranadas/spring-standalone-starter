package com.rdas.configurations.batch;

import com.rdas.services.batch.Contact;
import com.rdas.services.batch.ContactItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * Created by rdas on 06/04/2015.
 */
@EnableBatchProcessing
@Configuration
public class SpringBatchConfiguration {

    @Autowired
    @Qualifier("embeddedDS")
    private DataSource dataSource;

    @Bean
    public JdbcTemplate jobRepoJdbcTemplate(final DataSource dataSource) {
        System.out.println("\n\nExecute jobRepoJdbcTemplate\n");
        return new JdbcTemplate(dataSource);
    }

    @Bean
    DefaultBatchConfigurer batchConfigurer(final DataSource dataSource) {
        System.out.println("\n\nExecute batchConfigurer\n");
        return new DefaultBatchConfigurer(dataSource);
    }

    @Bean
    public Job importUserJob(JobBuilderFactory jobs, Step s1) {
        System.out.println("\n\nExecute importUserJob\n");
        //@Formatter:off
        return jobs.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .build();
        //@Formatter:oon
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Contact> reader,
                      ItemWriter<Contact> writer, ItemProcessor<Contact, Contact> processor) {
        System.out.println("\n\nStep1\n");
        return stepBuilderFactory.get("step1")
                .<Contact, Contact> chunk(10)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<Contact> reader(@Value("${reader.resource}") final String resourceName) {
        System.out.println("\n\nExecute ItemReader\n");

        FlatFileItemReader<Contact> reader = new FlatFileItemReader<Contact>();
        reader.setResource(new ClassPathResource(resourceName));
        reader.setLineMapper(new DefaultLineMapper<Contact>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[] { "name", "email", "status" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Contact>() {{
                setTargetType(Contact.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<Contact, Contact> processor() {
        System.out.println("Execute ItemProcessor");
        return new ContactItemProcessor();
    }

    @Bean
    public ItemWriter<Contact> writer(@Value("${writer.resource}") final String resourceName) {
        System.out.println("Execute ItemWriter");

        final FlatFileItemWriter<Contact> writer = new FlatFileItemWriter<Contact>();
        writer.setEncoding("UTF-8");
        writer.setResource(new ClassPathResource(resourceName));
        writer.setLineAggregator(new DelimitedLineAggregator<Contact>() {
            {
                setDelimiter("|");

                setFieldExtractor(new BeanWrapperFieldExtractor<Contact>() {
                    {
                        setNames(new String[] {"email", "name", "status"});
                    }
                });
            }
        });
        return writer;
    }
}
