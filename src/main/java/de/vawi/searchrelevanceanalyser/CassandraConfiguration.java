package de.vawi.searchrelevanceanalyser;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration {
    @Value("${spring.data.cassandra.contactpoints}")
    private String contactPoints;

    @Value("${spring.data.cassandra.port}")
    private int port;

    @Value("${spring.data.cassandra.keyspacename}")
    private String keySpace;

    @Override
    protected String getKeyspaceName() {
        return keySpace;
    }

    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        final CreateKeyspaceSpecification specification =
                CreateKeyspaceSpecification.createKeyspace(keySpace)
                        .ifNotExists()
                        .with(KeyspaceOption.DURABLE_WRITES, true)
                        .withSimpleReplication();
        ArrayList<CreateKeyspaceSpecification> schema = new ArrayList<>();
        schema.add(specification);
        return schema;
    }
}
