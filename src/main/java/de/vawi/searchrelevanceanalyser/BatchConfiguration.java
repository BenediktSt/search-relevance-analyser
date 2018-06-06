package de.vawi.searchrelevanceanalyser;

import javax.sql.DataSource;

import de.vawi.model.RelevanceEntry;
import de.vawi.processor.RelevanceEntryProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<RelevanceEntry> reader() {
        return new FlatFileItemReaderBuilder<RelevanceEntry>()
                .name("relevanceEntryReader")
                .resource(new ClassPathResource("sample-data.csv"))
                .linesToSkip(1)
                .delimited()
                .names(new String[]{"searchTerm", "rank", "result"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<RelevanceEntry>() {{
                    setTargetType(RelevanceEntry.class);
                }})
                .build();
    }

    @Bean
    public RelevanceEntryProcessor processor() {
        return new RelevanceEntryProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<RelevanceEntry> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<RelevanceEntry>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO relevance (searchterm, result, rank) VALUES (:searchTerm, :result, :rank)")
                .dataSource(dataSource)
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<RelevanceEntry> writer) {
        return stepBuilderFactory.get("step1")
                .<RelevanceEntry, RelevanceEntry>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer)
                .build();
    }
}
